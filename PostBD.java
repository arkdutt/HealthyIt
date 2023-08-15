// --== CS400 Project One File Header ==--
// Name: Ark Dutt
// CSL Username: ark
// Email: dutt3@wisc.edu
// Lecture #: 004 15:30-16:20
// Notes to Grader: <any optional extra notes to your grader>

public class PostBD implements PostInterface {

    private String title;
    private String url;
    private String body;

    public PostBD(String title, String url, String body) {
        this.title = title;
        this.url = url;
        this.body = body;
    }

    public String getTitle() {
        return this.title;
    }

    public String getUrl() {
        return this.url;
    }

    public String getBody() {
        return this.body;
    }
}
