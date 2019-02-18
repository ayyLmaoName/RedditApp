package projekt.redditapp.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import projekt.redditapp.Baza.Baza;
import projekt.redditapp.Baza.TblSubreddit;
import projekt.redditapp.PostProvider;
import projekt.redditapp.R;
import projekt.redditapp.adapters.PostAdapter;
import projekt.redditapp.models.Subreddit;

public class PregledRezultati extends Fragment {
    public static final String TAG = "PregledRezultati";

    ListView lvRezultati;
    PostAdapter adapter;
    TextView textView;
    ImageButton imageButton;
    PostProvider provider;
    Drawable punoSrce;
    Drawable praznoSrce;

    public PregledRezultati() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.provider = (PostProvider) getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View view = inflater.inflate(R.layout.lista_rezultati, container, false);

        Subreddit subreddit = provider.getSubreddit();
        this.adapter = new PostAdapter(getContext(), R.layout.rezultat, provider.getResults());

        this.punoSrce = ContextCompat.getDrawable(getContext(), R.drawable.puno);
        this.praznoSrce = ContextCompat.getDrawable(getContext(), R.drawable.prazno);

        this.textView = view.findViewById(R.id.tvOrezul);
        this.textView.setText(subreddit.getNaziv());

        this.lvRezultati = view.findViewById(R.id.lvRezultati);
        this.lvRezultati.setAdapter(this.adapter);

        this.imageButton = view.findViewById(R.id.ibFavoriteRez);

        int subID = subreddit.getID();
        if (subID != 0) {
            imageButton.setBackground(punoSrce);
        }

        this.imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TblSubreddit tblSubreddit = new TblSubreddit(new Baza(getActivity()).vratiBazu());

                if (subreddit.getID() != 0) {
                    imageButton.setBackground(praznoSrce);

                    tblSubreddit.Delete(subreddit);
                    subreddit.setID(0);
                }
                else{
                    subreddit.setID(tblSubreddit.Insert(subreddit));
                    imageButton.setBackground(punoSrce);
                }

            }
        });

        return view;
    }

}
