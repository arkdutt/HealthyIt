// --== CS400 Project One File Header ==--
// Name: Ark Dutt
// CSL Username: ark
// Email: dutt3@wisc.edu
// Lecture #: 004 15:30-16:20
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PostReaderBD implements PostReaderInterface {
    // public PostReaderInterface();


    public List<PostInterface> readPostsFromFile(String filename) throws FileNotFoundException {

        List<PostInterface> list = new ArrayList<>() ;

        PostBD post1 = new PostBD("Protein overload",
                "https://www.reddit.com/r/EatCheapAndHealthy/comments/zpgx94/protein_overload/",
                "Forget takeout! This homemade version of Panda Express’s Orange Chicken will blow you away with its");

        PostBD post2 = new PostBD("ORANGE CHICKEN",
                "https://www.reddit.com/r/EatCheapAndHealthy/comments/zpfsvx/orange_chicken/",
                "Forget takeout! This homemade version of Panda Express’s Orange Chicken will blow you away with its");

        list.add(post1);
        list.add(post2);
        return list;
    }
}
