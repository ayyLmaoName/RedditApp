package projekt.redditapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;

import projekt.redditapp.API.SubredditService;
import projekt.redditapp.Baza.Baza;
import projekt.redditapp.Baza.TblPost;
import projekt.redditapp.Baza.TblSubreddit;
import projekt.redditapp.models.Post;
import projekt.redditapp.adapters.FavPostAdapter;
import projekt.redditapp.adapters.FavSubAdapter;
import projekt.redditapp.adapters.PostAdapter;
import projekt.redditapp.fragments.PregledFavPost;
import projekt.redditapp.fragments.PregledFavSub;
import projekt.redditapp.fragments.PregledRezultati;
import projekt.redditapp.models.PostData;
import projekt.redditapp.models.RedditJSON;
import projekt.redditapp.models.RedditPost;
import projekt.redditapp.models.Subreddit;
import projekt.redditapp.models.SubredditPosts;
import projekt.redditapp.network.RetrofitClientInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements PostProvider {

    Button btnFavPost;
    Button btnFavSub;
    EditText etPretrazi;
    Button btnPretrazi;

    FavPostAdapter favPostAdapter;
    FavSubAdapter favSubAdapter;
    PostAdapter rezultatiAdapter;
    SubredditService service;

    ArrayList<Post> results;
    Subreddit subreddit;

    Context context;

    TblSubreddit tblSubreddit;
    TblPost tblPost;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnFavPost = this.findViewById(R.id.btnFavPost);
        this.btnFavSub = this.findViewById(R.id.btnFavSub);
        this.etPretrazi = this.findViewById(R.id.etSearch);
        this.btnPretrazi = this.findViewById(R.id.btnPretrazi);

        Baza baza = new Baza(this);
        db = baza.vratiBazu();

        this.context = this;
        this.tblSubreddit = new TblSubreddit(db);
        this.tblPost = new TblPost(db);

        this.service = RetrofitClientInstance.getRetrofitInstance().create(SubredditService.class);

        this.btnFavPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragA = getSupportFragmentManager().findFragmentByTag("PregledFavSub");
                Fragment fragB = getSupportFragmentManager().findFragmentByTag("PregledRezultati");

                FragmentManager fm = getSupportFragmentManager();

                if (fragA == null || fragB == null) {

                    FragmentTransaction transakcija = fm.beginTransaction();
                    transakcija.add(R.id.flFragmenti, new PregledFavPost(), PregledFavPost.TAG);
                    transakcija.commit();
                } else {

                    FragmentTransaction transakcija = fm.beginTransaction();

                    transakcija.replace(R.id.flFragmenti, new PregledFavPost(), PregledFavPost.TAG);
                    transakcija.addToBackStack("");
                    transakcija.commit();
                }
            }
        });

        this.btnFavSub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragA = getSupportFragmentManager().findFragmentByTag("PregledFavPost");
                Fragment fragB = getSupportFragmentManager().findFragmentByTag("PregledRezultati");

                FragmentManager fm = getSupportFragmentManager();

                if (fragA == null || fragB == null) {

                    FragmentTransaction transakcija = fm.beginTransaction();
                    transakcija.add(R.id.flFragmenti, new PregledFavSub(), PregledFavSub.TAG);
                    transakcija.commit();
                } else {

                    FragmentTransaction transakcija = fm.beginTransaction();

                    transakcija.replace(R.id.flFragmenti, new PregledFavSub(), PregledFavSub.TAG);
                    transakcija.addToBackStack("");
                    transakcija.commit();
                }
            }
        });

        this.etPretrazi.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode ==
                        KeyEvent.KEYCODE_ENTER) {
                    searchSubredditForPosts();
                }
                return false;
            }
        });

        this.btnPretrazi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                searchSubredditForPosts();
            }
        });

    }

    @Override
    public ArrayList<Post> getResults() {
        return results;
    }

    public Subreddit getSubreddit() {
        return subreddit;
    }

    public ArrayList<Post> getFavPosts() {
        ArrayList<Post> favPosts = tblPost.SelectAll();
        return favPosts;
    }

    public ArrayList<Subreddit> getFavSubreddits() {
        ArrayList<Subreddit> favSubs = tblSubreddit.SelectAll();
        return favSubs;
    }

    public void searchSubredditForPosts() {
        String pretraga = etPretrazi.getText() + "";
        subreddit = new Subreddit(pretraga, "https://reddit.com/r/" + pretraga, "1");
        Subreddit subFromDB = tblSubreddit.search(pretraga);

        if (subFromDB != null) {
            subreddit = subFromDB;
        }
        Call<RedditJSON> call = this.service.getPosts(pretraga + "/hot.json");

        call.enqueue(new Callback<RedditJSON>() {
            @Override
            public void onResponse(Call<RedditJSON> call, Response<RedditJSON> response) {

                SubredditPosts postsResponse = response.body().getData();
                ArrayList<RedditPost> posts = postsResponse.getChildren();

                results = new ArrayList<>();

                ArrayList<Post> favPosts = tblPost.SelectAll();

                for (RedditPost post : posts) {
                    PostData data = post.getData();

                    Date created = new Date((long) data.getCreated() * 1000);

                    Post rezultat = new Post(data.getTitle(),
                            data.getUrl(), data.getAuthor(), data.getUps(), created, data.getId(), data.getThumbnail());


                    int postFromDBIndex = favPosts.indexOf(rezultat);

                    if (postFromDBIndex != -1) {
                        rezultat.setID(favPosts.get(postFromDBIndex).getID());
                    }

                    results.add(rezultat);
                }

                Fragment fragA = getSupportFragmentManager().findFragmentByTag("PregledFavPost");
                Fragment fragB = getSupportFragmentManager().findFragmentByTag("PregledFavSub");

                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction transakcija = fm.beginTransaction();

                PregledRezultati rez = new PregledRezultati();

                if (fragA == null || fragB == null) {

                    transakcija.add(R.id.flFragmenti, rez, PregledRezultati.TAG);
                } else {

                    transakcija.replace(R.id.flFragmenti, rez, PregledRezultati.TAG);
                    transakcija.addToBackStack("");
                }

                transakcija.commit();

            }

            @Override
            public void onFailure(Call<RedditJSON> call, Throwable t) {

            }
        });


    }
}
