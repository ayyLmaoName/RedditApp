package projekt.redditapp.Baza;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import projekt.redditapp.models.Post;

public class TblPost {
    public static final String NAZIV_TABLICE = "favPost";
    public static final String ID = "ID";
    public static final String NAZIV = "Naziv";
    public static final String Link = "Link";
    public static final String Thumbnail = "Thumbnail";
    public static final String User = "User";
    public static final String VRIJEMEKREIRANJA = "VrijemeKreiranja";
    public static final String PostID = "PostID";

    SQLiteDatabase db;

    public TblPost(SQLiteDatabase db) {
        this.db = db;
    }

    DbBitmapUtility dbBit = new DbBitmapUtility();

    public int Insert(Post favPost) {
        ContentValues cv = new ContentValues();
        cv.put(NAZIV, favPost.getNaziv());
        cv.put(Link, favPost.getLink());
        if (favPost.getThumbnail() != null) {
            cv.put(Thumbnail, favPost.getThumbnail());
        }

        cv.put(User, favPost.getUser());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
        cv.put(VRIJEMEKREIRANJA, sdf.format(favPost.getVrijemeKreiranja()));

        int id = (int) this.db.insertOrThrow(NAZIV_TABLICE, null, cv);
        favPost.setID(id);
        return id;
    }

    public void Delete(Post favPost) {
        this.db.delete(NAZIV_TABLICE, ID + "=?", new String[]{favPost.getID() + ""});
    }

    public ArrayList<Post> SelectAll() {
        return this.select(null);
    }


    //nisam napravio update, jel nam uopće treba?
    //brijem da ne

    private ArrayList<Post> select(String id) {
        String[] kolone = new String[]{ID, NAZIV, Link, Thumbnail, User, VRIJEMEKREIRANJA, PostID};
        String where = null;
        String[] whereArgs = null;
        if (id != null) {
            where = ID + "?";
            whereArgs = new String[]{id};
        }
        Cursor cursor = this.db.query(NAZIV_TABLICE, kolone, where, whereArgs, null, null, ID + " DESC");

        ArrayList<Post> lista = new ArrayList<>();

        while (cursor.moveToNext()) {
            Post favPost = new Post();

            favPost.setID(cursor.getInt(cursor.getColumnIndex(ID)));
            favPost.setNaziv(cursor.getString(cursor.getColumnIndex(NAZIV)));
            favPost.setLink(cursor.getString(cursor.getColumnIndex(Link)));
            favPost.setThumbnail(cursor.getString(cursor.getColumnIndex(Thumbnail)));
            favPost.setUser(cursor.getString(cursor.getColumnIndex(User)));

            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
            try {
                favPost.setVrijemeKreiranja(sdf.parse(cursor.getString(cursor.getColumnIndex(VRIJEMEKREIRANJA))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            favPost.setPostID(cursor.getString(cursor.getColumnIndex(PostID)));
            lista.add(favPost);
        }

        return lista;
    }

    public Post Select(int id) {
        Post favPost = null;

        ArrayList<Post> favPostovi = this.select(String.valueOf(id));
        if (favPostovi.size() == 1)
            favPost = favPostovi.get(0);

        return favPost;
    }


}
