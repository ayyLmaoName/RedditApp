package projekt.redditapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import projekt.redditapp.Baza.Baza;
import projekt.redditapp.Baza.DbBitmapUtility;
import projekt.redditapp.Baza.favPost;
import projekt.redditapp.Baza.favSub;
import projekt.redditapp.Baza.tblFavPost;

import static projekt.redditapp.Baza.tblFavPost.*;

public class RezultatiAdapter extends ArrayAdapter<Rezultati> {
    LayoutInflater inflater;
    DbBitmapUtility dbBit = new DbBitmapUtility();
    SQLiteDatabase db;

    public RezultatiAdapter(Context context, int textViewResourceId,
                       ListRezultati objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Rezultati rezultati = this.getItem(position);

        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.rezultati, null);

        final Context context = this.inflater.getContext();

        ImageView ivThumbnail = (ImageView) convertView.findViewById(R.id.ivThumbnailRez);
        TextView tvUpRez = (TextView) convertView.findViewById(R.id.tvUpvotesRez);
        TextView tvNazivRez = (TextView) convertView.findViewById(R.id.tvNazivRez);
        TextView tvUserRez = (TextView) convertView.findViewById(R.id.tvUserRez);
        TextView tvKreiranjeRez = (TextView) convertView.findViewById(R.id.tvKreiranjeRez);
        View vPostIDrez = (View) convertView.findViewById(R.id.vPostIDrez);

        //ivThumbnail.setImageDrawable();
        tvUpRez.setText(rezultati.getUpvotes());
        tvNazivRez.setText(rezultati.getNaziv());
        tvNazivRez.setTag(rezultati.getLink());
        tvUserRez.setText(rezultati.getUser());
        tvKreiranjeRez.setText(rezultati.getVrijemeKreiranja().toString());
        vPostIDrez.setTag(rezultati.getPostID());

        //ako je post fav, stavljanje crvenog srca
        final ImageButton imageButton = (ImageButton) convertView.findViewById(R.id.ibFavoriteRez);
        if (postFav(rezultati.getPostID())){
            Drawable draw = getContext().getResources().getDrawable(R.drawable.puno);
            imageButton.setBackground(draw);
        }

        final String IDpost = rezultati.getPostID();
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (postFav(IDpost)){

                    String[] columns = { ID, NAZIV, Link, Thumbnail, User, VRIJEMEKREIRANJA, PostID };
                    String selection = NAZIV + " =?";
                    String[] selectionArgs = { IDpost };
                    String limit = "1";

                    Cursor cursor = db.query(NAZIV_TABLICE, columns, selection, selectionArgs, null, null, null, limit);
                    favPost favPost = new favPost();
                    favPost.setID(cursor.getInt(cursor.getColumnIndex(ID)));
                    favPost.setNaziv(cursor.getString(cursor.getColumnIndex(NAZIV)));
                    favPost.setLink(cursor.getString(cursor.getColumnIndex(Link)));

                    //ovo mozda radi, ali mozda zbog picassa ce trebat izmjenit neke stvari
                    if (cursor.getBlob(cursor.getColumnIndex(Thumbnail)) != null)
                        favPost.setThumbnail(dbBit.getImage(cursor.getBlob(cursor.getColumnIndex(Thumbnail))));

                    favPost.setUser(cursor.getString(cursor.getColumnIndex(User)));

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
                    try {
                        favPost.setVrijemeKreiranja(sdf.parse(cursor.getString(cursor.getColumnIndex(VRIJEMEKREIRANJA))));
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }

                    favPost.setPostID(cursor.getString(cursor.getColumnIndex(PostID)));
                    cursor.close();

                    tblFavPost tblFavPost = new tblFavPost(new Baza(context).vratiBazu());
                    tblFavPost.Delete(favPost);
                    remove(rezultati);

                    Drawable draw = getContext().getResources().getDrawable(R.drawable.prazno);
                    imageButton.setBackground(draw);
                }

                else{
                    favPost favPost = new favPost();
                    favPost.setNaziv(rezultati.getNaziv());
                    favPost.setLink(rezultati.getLink());

                    //ovo mozda radi, ali mozda zbog picassa ce trebat izmjenit neke stvari
                    if (rezultati.getThumbnail() != null)
                        //favPost.setThumbnail(dbBit.getImage(rezultati.getThumbnail()));

                    favPost.setUser(rezultati.getUser());

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
                    try {
                        favPost.setVrijemeKreiranja(sdf.parse(rezultati.getVrijemeKreiranja()+""));
                    }
                    catch (ParseException e) {
                        e.printStackTrace();
                    }

                    favPost.setPostID(rezultati.getPostID());
                    tblFavPost tblFavPost = new tblFavPost(new Baza(context).vratiBazu());
                    tblFavPost.Insert(favPost);

                    Drawable draw = getContext().getResources().getDrawable(R.drawable.puno);
                    imageButton.setBackground(draw);
                }

            }
        });


        return convertView;
    }

    public boolean postFav(String searchItem){
        String[] columns = { PostID };
        String selection = PostID + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";

        Cursor cursor = db.query(NAZIV_TABLICE, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);

        cursor.close();

        return exists;
    }
}
