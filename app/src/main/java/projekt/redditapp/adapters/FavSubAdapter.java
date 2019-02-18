package projekt.redditapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import projekt.redditapp.Baza.*;
import projekt.redditapp.R;
import projekt.redditapp.models.Subreddit;

public class FavSubAdapter extends ArrayAdapter<Subreddit> {
    LayoutInflater inflater;
    DbBitmapUtility dbBit = new DbBitmapUtility();


    public FavSubAdapter(Context context, int textViewResourceId,
                         ArrayList<Subreddit> objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Subreddit favSub = this.getItem(position);

        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.fav_sub, null);

        final Context context = this.inflater.getContext();

        TextView tvNazivSub = (TextView) convertView.findViewById(R.id.tvSubNaziv);
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
                TblSubreddit tblFavSub = new TblSubreddit(new Baza(context).vratiBazu());
                tblFavSub.Delete(favSub);
                remove(favSub);
            }
        });


        return convertView;
    }
}
