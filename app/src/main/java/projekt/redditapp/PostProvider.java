package projekt.redditapp;

import java.util.ArrayList;

import projekt.redditapp.models.Post;
import projekt.redditapp.models.Subreddit;

public interface PostProvider {
    ArrayList<Post> getResults();

    Subreddit getSubreddit();

    ArrayList<Subreddit> getFavSubreddits();

    ArrayList<Post> getFavPosts();
}
