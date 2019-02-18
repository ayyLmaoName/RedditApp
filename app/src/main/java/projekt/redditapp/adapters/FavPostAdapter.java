package projekt.redditapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import projekt.redditapp.Baza.Baza;
import projekt.redditapp.Baza.DbBitmapUtility;
import projekt.redditapp.Baza.TblPost;
import projekt.redditapp.R;
import projekt.redditapp.models.Post;

public class FavPostAdapter extends ArrayAdapter<Post> {
    LayoutInflater inflater;
    DbBitmapUtility dbBit = new DbBitmapUtility();

    public FavPostAdapter(Context context, int textViewResourceId,
                          ArrayList<Post> objects) {
        super(context, textViewResourceId, objects);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Post favPost = this.getItem(position);

        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.rezultat, null);

        final Context context = this.inflater.getContext();

        ImageView ivThumbnail = convertView.findViewById(R.id.ivThumbnailRez);
        TextView tvNaziv = convertView.findViewById(R.id.tvNazivRez);
        TextView tvUser = convertView.findViewById(R.id.tvUserRez);
        TextView tvKreiranje = convertView.findViewById(R.id.tvKreiranjeRez);
        View vPostID = convertView.findViewById(R.id.vPostIDrez);

        //ivThumbnail.setImageDrawable();
        tvNaziv.setText(favPost.getNaziv());
        tvNaziv.setTag(favPost.getLink()); //spremanje linka
        tvUser.setText(favPost.getUser());
        tvKreiranje.setText(favPost.getVrijemeKreiranja().toString());
        vPostID.setTag(favPost.getPostID());


        if (favPost.getThumbnail() == null) {
            ivThumbnail.setImageDrawable(context.getDrawable(R.drawable.reddit_logo));
        } else {
            Picasso.with(context).load(favPost.getThumbnail()).into(ivThumbnail);
        }

        ImageButton ibFavoritePost = convertView.findViewById(R.id.ibFavoriteRez);
        ibFavoritePost.setBackground(context.getDrawable(R.drawable.puno));

        ibFavoritePost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                TblPost tblFavPost = new TblPost(new Baza(context).vratiBazu());
                tblFavPost.Delete(favPost);
                remove(favPost);
            }
        });


        tvNaziv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(favPost.getLink()));
                getContext().startActivity(intent);
            }
        });
        return convertView;
    }
}
