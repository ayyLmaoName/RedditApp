package projekt.redditapp.models;

public class RedditPost {

    private String kind;
    private PostData data;

    public RedditPost(String kind, PostData data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public PostData getData() {
        return data;
    }

    public void setData(PostData data) {
        this.data = data;
    }
}
