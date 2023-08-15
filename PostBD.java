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
