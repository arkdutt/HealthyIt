// --== CS400 Project One File Header ==--
// Name: Ark Dutt
// CSL Username: ark
// Email: dutt3@wisc.edu
// Lecture #: 004 15:30-16:20
// Notes to Grader: <any optional extra notes to your grader>

import java.io.FileNotFoundException;
import java.util.List;

public interface CHSearchBackendInterface {
    // public CHSearchBackendInterface(HashtableWithDuplicateKeysInterface<String,PostInterface> hashtable, PostReaderInterface postReader);
    public void loadData(String filename) throws FileNotFoundException;
    public List<String> findPostsByTitleWords(String words);
    public List<String> findPostsByBodyWords(String words);
    public List<String> findPostsByTitleOrBodyWords(String words);
    public String getStatisticsString();
}
