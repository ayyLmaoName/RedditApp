package projekt.redditapp.models;

import java.util.ArrayList;

public class SubredditPosts {
    private ArrayList<RedditPost> children;

    public SubredditPosts(ArrayList<RedditPost> children) {
        this.children = children;
    }

    public ArrayList<RedditPost> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<RedditPost> children) {
        this.children = children;
    }
}
