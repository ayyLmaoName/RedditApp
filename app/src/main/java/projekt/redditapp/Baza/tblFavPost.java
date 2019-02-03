package projekt.redditapp.Baza;

import projekt.redditapp.*;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import projekt.redditapp.ListFavPost;

public class tblFavPost {
    public static final String NAZIV_TABLICE = "favPost";
    public static final String ID = "ID";
    public static final String NAZIV = "Naziv";
    public static final String Link = "Link";
    public static final String Thumbnail = "Thumbnail";
    public static final String User = "User";
    public static final String VRIJEMEKREIRANJA = "VrijemeKreiranja";
    public static final String PostID = "PostID";

    SQLiteDatabase db;
    public tblFavPost (SQLiteDatabase db)
    {
        this.db = db;
    }
    DbBitmapUtility dbBit = new DbBitmapUtility();

    public void Insert (favPost favPost)
    {
        ContentValues cv = new ContentValues();
        cv.put(NAZIV, favPost.getNaziv());
        cv.put(Link, favPost.getLink());

        //ovo mozda radi, ali mozda zbog picassa ce trebat izmjenit neke stvari
        cv.put(Thumbnail, dbBit.getBytes(favPost.getThumbnail()));

        cv.put(User, favPost.getUser());

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
        cv.put(VRIJEMEKREIRANJA, sdf.format(favPost.getVrijemeKreiranja()));

        int id = (int)this.db.insertOrThrow(NAZIV_TABLICE, null, cv);
        favPost.setID(id);
    }

    public void Delete (favPost favPost)
    {
        this.db.delete(NAZIV_TABLICE, ID + "=?", new String[]{favPost.getID()+""});
    }

    public ListFavPost SelectAll ()
    {
        return this.select(null);
    }


    //nisam napravio update, jel nam uopće treba?

    private ListFavPost select (String id)
    {
        String[] kolone = new String[]{ID, NAZIV, Link, Thumbnail, User, VRIJEMEKREIRANJA, PostID};
        String where = null;
        String[] whereArgs = null;
        if (id != null)
        {
            where = ID + "?";
            whereArgs = new String[] {id};
        }
        Cursor cursor = this.db.query(NAZIV_TABLICE, kolone, where, whereArgs, null, null, ID+" DESC");

        ListFavPost lista = new ListFavPost();

        while (cursor.moveToNext())
        {
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

            lista.add(favPost);
        }

        return lista;
    }

    public favPost Select (int id)
    {
        favPost favPost = null;

        ListFavPost favPostovi = this.select(String.valueOf(id));
        if (favPostovi.size() == 1)
            favPost = favPostovi.get(0);

        return favPost;
    }


}
