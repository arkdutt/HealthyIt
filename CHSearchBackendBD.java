// --== CS400 Project One File Header ==--
// Name: Ark Dutt
// CSL Username: ark
// Email: dutt3@wisc.edu
// Lecture #: 004 15:30-16:20
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Backend Class
 */

public class CHSearchBackendBD implements CHSearchBackendInterface {

    private HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable;
    private PostReaderInterface postReader;
    private int noPosts;

    public CHSearchBackendBD(HashtableWithDuplicateKeysInterface<String, PostInterface> hashtable, PostReaderInterface postReader) {
        this.hashtable = hashtable;
        this.postReader = postReader;
        this.noPosts = 0;
    }

    public void size(int numbers) {
        this.noPosts = numbers;
    }

    /**
     * Loads the list of posts from the PostInterface,
     * which includes the titles of the posts and the bodies of the posts.
     *
     * @param filename
     * @throws FileNotFoundException if the file does not exist
     */
    public void loadData(String filename) throws FileNotFoundException {

        List<PostInterface> list = postReader.readPostsFromFile(filename);
        this.size(list.size());

        //checks if the file exists or now
        if(filename == null || filename.equals("")) {
            throw new FileNotFoundException("No such file was found!");
        }

        for(PostInterface post : list) {

            //title of the post
            String title = post.getTitle().toLowerCase();

            //url of the post
            String url = post.getUrl().toLowerCase();

            //body of the post
            String body = post.getBody().toLowerCase();

            //Takes the punctuation off the given sentence and stores the words in an array
            String [] titleWords = pickWords(title).split(" ");

            //Loads the title of the posts using hashtable implementation
            for(String word : titleWords) {
                hashtable.putOne("TITLE: "+word, post);
            }

            //Loads the body of the posts using hashtable implementation
            String [] bodyWords = pickWords(body).split((" "));
            for(String word : bodyWords) {
                hashtable.putOne("BODY: "+word, post);
            }
        }
    }

    /**
     *pickWords() scrapes the punctuation marks off the texts, by extracting the words and storing
     * them in an array of strings in the loadData method
     *
     * @param texts the input
     * @return the input without any punctuation marks
     */
    public String pickWords(String texts) {
        String newtext = "";
        for(char i : texts.toCharArray()) {
            if(Character.isLetterOrDigit(i) || i == ' ') {
                newtext += i;
            }
        }
        return newtext;
    }

    /**
     *eliminateDuplicate method eliminates any duplicate titles to zero down the exact unique output.
     * This method is used as a helper method in findPostsByTitleWords method,
     * findPostsByBodyWords method, and findPostsByTitleOrBodyWords method.
     *
     * @param text duplicates of either the title or the body of the posts
     * @return the unique title of the desired post
     */
    private List<String> eliminateDuplicate(List<String> text) {

        ArrayList<String> result = new ArrayList<>();
        for(String post : text) {
            if(!result.contains(post)) {
                result.add(post);
            }
        }
        return result;
    }

    /**
     *Filters the title of the desired posts using the title entered.
     * In order to indicate the desired post, the method returns the title of the desired post.
     * The method also incorporates the helper method eliminateDuplicate method to eliminate the duplicate titles
     * corresponding to the given title words, and zero down the unique title of the desired post.
     *
     * @param words
     * @return the title of the desired post
     */
    public List<String> findPostsByTitleWords(String words) {

        ArrayList<String> titles = new ArrayList<>();

        String [] titleLists = pickWords(words.toLowerCase()).split(" ");
        for(String text : titleLists) {
            if(hashtable.containsKey("TITLE: " + text)) {
                titles.addAll(retrieveTitles(hashtable.get("TITLE: " + text)));
            }
        }
        List<String> desire = eliminateDuplicate(titles);
        return desire;
    }

    /**
     *Filters the titles of the desired posts, using either the body or title of the desired post.
     * It also includes duplicates or titles which may appear to be similar
     *
     * @param list list of either the body or the title of the posts
     * @return the list of the titles
     */
    private List<String> retrieveTitles(List<PostInterface> list) {
        ArrayList<String> titles = new ArrayList<>();
        for(PostInterface post : list) {
            titles.add(post.getTitle());
        }
        return titles;
    }

    /**
     *This method finds the desired post using the body text. In order to indicate the desired post, the method
     * returns the title of the desired post.
     * The method also incorporates the helper method eliminateDuplicate method to eliminate the duplicate titles
     * corresponding to the given body words, and zero down the unique title of the desired post.
     *
     * @param words the body words
     * @return the title of the desired post
     */
    public List<String> findPostsByBodyWords(String words) {

        ArrayList<String> titles = new ArrayList<>();
        String [] titleLists = pickWords(words.toLowerCase()).split(" ");
        for(String text : titleLists) {
            String key = "BODY: " + text;
            if(hashtable.containsKey(key)) {
                titles.addAll(retrieveTitles(hashtable.get(key)));
            }
        }

        List<String> desire = eliminateDuplicate(titles);
        return desire;
    }

    /**
     * This method finds the desired post using either the body words or the title words.
     * In order to indicate the desired post, the method returns the title of the desired post.
     * The method also incorporates both findPostsByBodyWords method and findPostsByTitleWords method.
     *
     * @param words title words or body words
     * @return the title of the desired post
     */
    public List<String> findPostsByTitleOrBodyWords(String words) {

        ArrayList<String> titles = new ArrayList<>();
        String [] titleLists = pickWords(words.toLowerCase()).split(" ");
        for(String text : titleLists) {
            titles.addAll(findPostsByBodyWords(text));
            titles.addAll(findPostsByTitleWords(text));
        }
        List<String> desire = eliminateDuplicate(titles);
        return desire;
    }

    /**
     * Shows the statistics of the number of posts
     * @return the number of posts
     */
    public String getStatisticsString() {
        String stat =  "Number of Posts: " + noPosts + ".";
        return stat;
    }
}
