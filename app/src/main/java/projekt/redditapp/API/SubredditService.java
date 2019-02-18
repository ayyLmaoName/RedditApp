package projekt.redditapp.API;

import projekt.redditapp.models.RedditJSON;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface SubredditService {
    @GET
    Call<RedditJSON> getPosts(@Url String url);
}
