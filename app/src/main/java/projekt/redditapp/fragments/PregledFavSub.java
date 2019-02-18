package projekt.redditapp.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import projekt.redditapp.PostProvider;
import projekt.redditapp.adapters.FavSubAdapter;
import projekt.redditapp.R;

public class PregledFavSub extends Fragment {

    public static final String TAG = "PregledFavSub";
    ListView lvFavSubovi;
    FavSubAdapter adapter;
    PostProvider provider;

    public PregledFavSub() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.provider = (PostProvider) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_fav_subovi, container, false);

        this.lvFavSubovi = view.findViewById(R.id.lvFavSubovi);
        this.adapter = new FavSubAdapter(getContext(), R.layout.fav_sub, provider.getFavSubreddits());
        this.lvFavSubovi.setAdapter(this.adapter);

        return view;
    }


}
