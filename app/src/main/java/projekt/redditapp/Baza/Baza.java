package projekt.redditapp.Baza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Baza {
    public static final int TRENUTNA_VERZIJA = 5;
    BazaOpen open;

    public Baza(Context context) {
        this.open = new BazaOpen(context, "baza.db", null, TRENUTNA_VERZIJA);
    }

    public SQLiteDatabase vratiBazu() {
        return this.open.getWritableDatabase();
    }
}
