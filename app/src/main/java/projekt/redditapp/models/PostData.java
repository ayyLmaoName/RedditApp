package projekt.redditapp.models;

public class PostData {

    private String title;
    private String url;
    private String thumbnail;
    private String author;
    private String ups;
    private Long created;
    private String subreddit;
    private String selftext;
    private String id;

    public PostData(String title, String url, String thumbnail, String author, String ups, Long created, String subreddit, String selftext, String id) {
        this.title = title;
        this.url = url;
        this.thumbnail = thumbnail;
        this.author = author;
        this.ups = ups;
        this.created = created;
        this.subreddit = subreddit;
        this.selftext = selftext;
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUps() {
        return ups;
    }

    public void setUps(String ups) {
        this.ups = ups;
    }

    public Long getCreated() {
        return created;
    }

    public void setCreated(Long created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubreddit() {
        return subreddit;
    }

    public void setSubreddit(String subreddit) {
        this.subreddit = subreddit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSelftext() {
        return selftext;
    }

    public void setSelftext(String selftext) {
        this.selftext = selftext;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }


}
