package projekt.redditapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import projekt.redditapp.Baza.Baza;
import projekt.redditapp.Baza.DbBitmapUtility;
import projekt.redditapp.Baza.favPost;
import projekt.redditapp.Baza.tblFavPost;

public class FavPostAdapter extends ArrayAdapter<favPost> {
    LayoutInflater inflater;
    DbBitmapUtility dbBit = new DbBitmapUtility();

    public FavPostAdapter(Context context, int textViewResourceId,
                            ListFavPost objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final favPost favPost= this.getItem(position);

        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.fav_post, null);

        final Context context = this.inflater.getContext();

        ImageView ivThumbnail = (ImageView) convertView.findViewById(R.id.ivThumbnail);
        TextView tvNaziv = (TextView) convertView.findViewById(R.id.tvNaziv);
        TextView tvUser = (TextView) convertView.findViewById(R.id.tvUser);
        TextView tvKreiranje = (TextView) convertView.findViewById(R.id.tvKreiranje);
        View vPostID = (View) convertView.findViewById(R.id.vPostID);

        //ivThumbnail.setImageDrawable();
        tvNaziv.setText(favPost.getNaziv());
        tvNaziv.setTag(favPost.getLink()); //spremanje linka
        tvUser.setText(favPost.getUser());
        tvKreiranje.setText(favPost.getVrijemeKreiranja().toString());
        vPostID.setTag(favPost.getPostID());

        ImageButton ibFavoritePost = (ImageButton) convertView.findViewById(R.id.ibFavorite);

        ibFavoritePost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tblFavPost tblFavPost = new tblFavPost(new Baza(context).vratiBazu());
                tblFavPost.Delete(favPost);
                remove(favPost);
            }
        });


        return convertView;
    }
}
