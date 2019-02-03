package projekt.redditapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import projekt.redditapp.Baza.*;

public class FavSubAdapter extends ArrayAdapter<favSub> {
    LayoutInflater inflater;
    DbBitmapUtility dbBit = new DbBitmapUtility();


    public FavSubAdapter(Context context, int textViewResourceId,
                            ListFavSub objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final favSub favSub = this.getItem(position);

        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.fav_sub, null);

        final Context context = this.inflater.getContext();

        TextView tvNazivSub = (TextView) convertView.findViewById(R.id.tvNazivRez);
        View vZadnjiViden = (View) convertView.findViewById(R.id.vZadnjiViden);
        ImageButton ibFavoriteSub = (ImageButton) convertView.findViewById(R.id.ibFavoriteSub);

        //postavljanje vrijednosti

        //ivThumbnail.setImageDrawable();
        tvNazivSub.setText(favSub.getNaziv());
        tvNazivSub.setTag(favSub.getLink());
        vZadnjiViden.setTag(favSub.getZadnjiViden());


        //stiskanje srca
        ibFavoriteSub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tblFavSub tblFavSub = new tblFavSub(new Baza(context).vratiBazu());
                tblFavSub.Delete(favSub);
                remove(favSub);
            }
        });


        return convertView;
    }
}
