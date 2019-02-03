package projekt.redditapp.fragmenti;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import projekt.redditapp.FavPostAdapter;
import projekt.redditapp.R;

public class PregledFavPost extends Fragment {

    public static final String TAG = "PregledFavPost";
    ListView lvFavPostovi;
    FavPostAdapter adapter;


    public PregledFavPost(FavPostAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_fav_postovi, container, false);

        this.lvFavPostovi = (ListView) view.findViewById(R.id.lvFavPostovi);

        this.lvFavPostovi.setAdapter(this.adapter);

        return view;
    }
}
