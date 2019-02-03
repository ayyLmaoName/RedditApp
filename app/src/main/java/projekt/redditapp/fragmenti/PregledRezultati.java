package projekt.redditapp.fragmenti;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import projekt.redditapp.Baza.Baza;
import projekt.redditapp.Baza.favSub;
import projekt.redditapp.Baza.tblFavSub;
import projekt.redditapp.R;
import projekt.redditapp.RezultatiAdapter;

import static projekt.redditapp.Baza.tblFavSub.*;

public class PregledRezultati extends Fragment {
    public static final String TAG = "PregledRezultati";
    ListView lvRezultati;
    RezultatiAdapter adapter;
    TextView textView;
    ImageButton imageButton;

    SQLiteDatabase db;


    public PregledRezultati (RezultatiAdapter adapter){
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
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.lista_rezultati, container, false);

        //stavljanje o kojem je sub rijec
        Bundle args = getArguments();
        String subreddit = args.getString("subreddit");
        final String kojiSub = args.getString("subreddit");

        Log.w("myApp", subreddit);

        //final String subLink = getArguments().getString("subLink");
        this.textView = (TextView) view.findViewById(R.id.tvOrezul);
        this.textView.setText(subreddit);

        this.lvRezultati = (ListView) view.findViewById(R.id.lvRezultati);

        this.lvRezultati.setAdapter(this.adapter);

        //ako je sub favorite, stavlja se crveno srce
        final Boolean favoriteSub = subFav(kojiSub);
        if (favoriteSub){
            Drawable draw = getResources().getDrawable(R.drawable.puno);
            imageButton.setBackground(draw);
        }

        //stavljanje suba u fav ili micanje
        this.imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (favoriteSub){

                    String[] columns = { ID, NAZIV, Link, ZadnjiViden };
                    String selection = NAZIV + " =?";
                    String[] selectionArgs = { kojiSub };
                    String limit = "1";

                    Cursor cursor = db.query(NAZIV_TABLICE, columns, selection, selectionArgs, null, null, null, limit);

                    favSub favSub = new favSub();
                    favSub.setID(cursor.getInt(cursor.getColumnIndex(ID)));
                    favSub.setNaziv(cursor.getString(cursor.getColumnIndex(NAZIV)));
                    favSub.setLink(cursor.getString(cursor.getColumnIndex(Link)));
                    favSub.setZadnjiViden(cursor.getString(cursor.getColumnIndex(ZadnjiViden)));

                    cursor.close();

                    tblFavSub tblFavSub = new tblFavSub(new Baza(getActivity()).vratiBazu());
                    tblFavSub.Delete(favSub);

                    Drawable draw = getResources().getDrawable(R.drawable.prazno);
                    imageButton.setBackground(draw);
                }
                else{

                    //stavljanje u fav

                    /*String[] columns = { NAZIV, ZadnjiViden };
                    String selection = NAZIV + " =?";
                    String[] selectionArgs = { searchItem };
                    String limit = "1";

                    Cursor cursor = db.query(NAZIV_TABLICE, columns, selection, selectionArgs, null, null, null, limit);
                    //boolean exists = (cursor.getCount() > 0);
                    String idPosta;
                    if(cursor.getString(cursor.getColumnIndex(ZadnjiViden)) != null)
                        idPosta = cursor.getString(cursor.getColumnIndex(ZadnjiViden));

                    else idPosta = "";

                    cursor.close();


                    return idPosta;*/
                }

            }
        });

        return view;
    }

    public boolean subFav(String searchItem){
        String[] columns = { NAZIV };
        String selection = NAZIV + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";

        Cursor cursor = db.query(NAZIV_TABLICE, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);

        cursor.close();

        return exists;
    }
}
