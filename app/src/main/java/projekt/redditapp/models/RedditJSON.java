package projekt.redditapp.models;

public class RedditJSON {

    private String kind;
    private SubredditPosts data;

    public RedditJSON(String kind, SubredditPosts data) {
        this.kind = kind;
        this.data = data;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public SubredditPosts getData() {
        return data;
    }

    public void setData(SubredditPosts data) {
        this.data = data;
    }

}
