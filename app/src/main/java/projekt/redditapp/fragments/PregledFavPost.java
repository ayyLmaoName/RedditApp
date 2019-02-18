package projekt.redditapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import projekt.redditapp.PostProvider;
import projekt.redditapp.adapters.FavPostAdapter;
import projekt.redditapp.R;

public class PregledFavPost extends Fragment {

    public static final String TAG = "PregledFavPost";
    ListView lvFavPostovi;
    FavPostAdapter adapter;
    PostProvider provider;

    public PregledFavPost() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.provider = (PostProvider) getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_fav_postovi, container, false);

        this.lvFavPostovi = view.findViewById(R.id.lvFavPostovi);
        this.adapter = new FavPostAdapter(getContext(), R.layout.fav_post, provider.getFavPosts());


        this.lvFavPostovi.setAdapter(this.adapter);

        return view;
    }
}
