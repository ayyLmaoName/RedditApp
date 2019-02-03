package projekt.redditapp.Baza;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.LinearLayout;

import projekt.redditapp.ListFavSub;

public class tblFavSub {
    public static final String NAZIV_TABLICE = "favSub";
    public static final String ID = "ID";
    public static final String NAZIV = "Naziv";
    public static final String Link = "Link";
    public static final String ZadnjiViden = "ZadnjiViden";

    SQLiteDatabase db;
    public tblFavSub (SQLiteDatabase db)
    {
        this.db = db;
    }


    public void Insert (favSub favSub)
    {
        ContentValues cv = new ContentValues();
        cv.put(NAZIV, favSub.getNaziv());
        cv.put(Link, favSub.getLink());
        cv.put(ZadnjiViden, favSub.getZadnjiViden());

        int id = (int)this.db.insertOrThrow(NAZIV_TABLICE, null, cv);
        favSub.setID(id);
    }

    public void Update (favSub favSub)
    {
        ContentValues cv = new ContentValues();
        cv.put(NAZIV, favSub.getNaziv());
        cv.put(Link, favSub.getLink());
        cv.put(ZadnjiViden, favSub.getZadnjiViden());

        this.db.update(NAZIV_TABLICE, cv, ID + "=?", new String[]{favSub.getID()+""});
    }

    public void Delete (favSub favSub)
    {
        this.db.delete(NAZIV_TABLICE, ID + "=?", new String[]{favSub.getID()+""});
    }

    public ListFavSub SelectAll ()
    {
        return this.select(null);
    }


    private ListFavSub select (String id)
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

        ListFavSub lista = new ListFavSub();

        while (cursor.moveToNext())
        {
            favSub favSub = new favSub();

            favSub.setID(cursor.getInt(cursor.getColumnIndex(ID)));
            favSub.setNaziv(cursor.getString(cursor.getColumnIndex(NAZIV)));
            favSub.setLink(cursor.getString(cursor.getColumnIndex(Link)));
            favSub.setZadnjiViden(cursor.getString(cursor.getColumnIndex(ZadnjiViden)));

            lista.add(favSub);
        }

        return lista;
    }

    public favSub Select (int id)
    {
        favSub favSub = null;

        ListFavSub favSubovi = this.select(String.valueOf(id));
        if (favSubovi.size() == 1)
            favSub = favSubovi.get(0);

        return favSub;
    }



}
