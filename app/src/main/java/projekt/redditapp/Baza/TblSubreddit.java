package projekt.redditapp.Baza;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import projekt.redditapp.models.Subreddit;

public class TblSubreddit {
    public static final String NAZIV_TABLICE = "favSub";
    public static final String ID = "ID";
    public static final String NAZIV = "Naziv";
    public static final String Link = "Link";
    public static final String ZadnjiViden = "ZadnjiViden";

    SQLiteDatabase db;

    public TblSubreddit(SQLiteDatabase db)
    {
        this.db = db;
    }


    public int Insert(Subreddit favSub)
    {
        ContentValues cv = new ContentValues();
        cv.put(NAZIV, favSub.getNaziv());
        cv.put(Link, favSub.getLink());
        cv.put(ZadnjiViden, favSub.getZadnjiViden());

        int id = (int)this.db.insertOrThrow(NAZIV_TABLICE, null, cv);
        favSub.setID(id);
        return id;
    }

    public void Update(Subreddit favSub)
    {
        ContentValues cv = new ContentValues();
        cv.put(NAZIV, favSub.getNaziv());
        cv.put(Link, favSub.getLink());
        cv.put(ZadnjiViden, favSub.getZadnjiViden());

        this.db.update(NAZIV_TABLICE, cv, ID + "=?", new String[]{favSub.getID()+""});
    }

    public void Delete(Subreddit favSub)
    {
        this.db.delete(NAZIV_TABLICE, ID + "=?", new String[]{favSub.getID()+""});
    }

    public ArrayList<Subreddit> SelectAll() {
        return this.select(null);
    }


    private ArrayList<Subreddit> select(String id)
    {
        String[] kolone = new String[]{ID, NAZIV, Link, ZadnjiViden};
        String where = null;
        String[] whereArgs = null;
        if (id != null)
        {
            where = ID + "?";
            whereArgs = new String[] {id};
        }
        Cursor cursor = this.db.query(NAZIV_TABLICE, kolone, where, whereArgs, null, null, ID+" DESC");

        ArrayList<Subreddit> lista = new ArrayList<>();

        while (cursor.moveToNext())
        {
            Subreddit favSub = new Subreddit();

            favSub.setID(cursor.getInt(cursor.getColumnIndex(ID)));
            favSub.setNaziv(cursor.getString(cursor.getColumnIndex(NAZIV)));
            favSub.setLink(cursor.getString(cursor.getColumnIndex(Link)));
            favSub.setZadnjiViden(cursor.getString(cursor.getColumnIndex(ZadnjiViden)));

            lista.add(favSub);
        }

        return lista;
    }

    public Subreddit Select(int id)
    {
        Subreddit favSub = null;

        ArrayList<Subreddit> favSubovi = this.select(String.valueOf(id));
        if (favSubovi.size() == 1)
            favSub = favSubovi.get(0);
        return favSub;
    }

    public Subreddit search(String name) {
        String[] columns = {ID, NAZIV, Link};
        String selection = NAZIV + " =?";
        String[] selectionArgs = {name};
        String limit = "1";

        Cursor cursor = db.query(NAZIV_TABLICE, columns, selection, selectionArgs, null, null, null, limit);

        Subreddit subreddit;

        if (cursor.moveToNext()) {
            subreddit = new Subreddit();
            subreddit.setLink(cursor.getString(cursor.getColumnIndex(Link)));
            subreddit.setNaziv(cursor.getString(cursor.getColumnIndex(NAZIV)));
            subreddit.setID(cursor.getInt(cursor.getColumnIndex(ID)));
            cursor.close();
            return subreddit;
        }
        cursor.close();
        return null;
    }

}
