package projekt.redditapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import projekt.redditapp.fragmenti.PregledFavPost;
import projekt.redditapp.fragmenti.PregledFavSub;
import projekt.redditapp.fragmenti.PregledRezultati;

public class MainActivity extends AppCompatActivity {

    Button btnFavPost;
    Button btnFavSub;
    EditText etPretrazi;
    Button btnPretrazi;

    FavPostAdapter favPostAdapter;
    FavSubAdapter favSubAdapter;
    RezultatiAdapter rezultatiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.btnFavPost = (Button) this.findViewById(R.id.btnFavPost);
        this.btnFavSub = (Button) this.findViewById(R.id.btnFavSub);
        this.etPretrazi = (EditText) this.findViewById(R.id.etSearch);
        this.btnPretrazi = (Button) this.findViewById(R.id.btnPretrazi);


        this.btnFavPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Fragment fragA = getSupportFragmentManager().findFragmentByTag("PregledFavSub");
                Fragment fragB = getSupportFragmentManager().findFragmentByTag("PregledRezultati");

                FragmentManager fm = getSupportFragmentManager();

                if (fragA == null || fragB == null){

                    FragmentTransaction transakcija = fm.beginTransaction();
                    transakcija.add(R.id.flFragmenti, new PregledFavPost(favPostAdapter), PregledFavPost.TAG);
                    transakcija.commit();
                }

                else {

                    FragmentTransaction transakcija = fm.beginTransaction();

                    transakcija.replace(R.id.flFragmenti, new PregledFavPost(favPostAdapter), PregledFavPost.TAG);
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

                if (fragA == null || fragB == null){

                    FragmentTransaction transakcija = fm.beginTransaction();
                    transakcija.add(R.id.flFragmenti, new PregledFavSub(favSubAdapter), PregledFavSub.TAG);
                    transakcija.commit();
                }

                else {

                    FragmentTransaction transakcija = fm.beginTransaction();

                    transakcija.replace(R.id.flFragmenti, new PregledFavSub(favSubAdapter), PregledFavSub.TAG);
                    transakcija.addToBackStack("");
                    transakcija.commit();
                }
            }
        });

        this.etPretrazi.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode ==
                        KeyEvent.KEYCODE_ENTER)
                {
                    klikPretraga();
                }
                return false;
            }
        });

        this.btnPretrazi.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                klikPretraga();
            }
        });



    }

    public void klikPretraga(){
        Fragment fragA = getSupportFragmentManager().findFragmentByTag("PregledFavPost");
        Fragment fragB = getSupportFragmentManager().findFragmentByTag("PregledFavSub");

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transakcija = fm.beginTransaction();

        Bundle bundle= new Bundle();
        String pretraga = etPretrazi.getText()+"";
        bundle.putString("subreddit", pretraga);

        PregledRezultati rez = new PregledRezultati(rezultatiAdapter);
        rez.setArguments(bundle);

        if (fragA == null || fragB == null){

            transakcija.add(R.id.flFragmenti, rez, PregledRezultati.TAG);
        }

        else {



            transakcija.replace(R.id.flFragmenti, rez, PregledRezultati.TAG);
            transakcija.addToBackStack("");
        }


        transakcija.commit();
    }
}
