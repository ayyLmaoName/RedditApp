package projekt.redditapp.Baza;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import projekt.redditapp.R;


public class BazaOpen extends SQLiteOpenHelper {
    Context context;
    public BazaOpen(Context context, String name, SQLiteDatabase.CursorFactory factory,
                    int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(this.context.getResources().getString(R.string.create_table_favPost));
        db.execSQL(this.context.getResources().getString(R.string.create_table_favSub));

    }
    @Override
    public void onConfigure(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
        super.onConfigure(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS favPost");
        db.execSQL("DROP TABLE IF EXISTS favSub");
        this.onCreate(db);
    }
}