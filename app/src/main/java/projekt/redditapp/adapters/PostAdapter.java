package projekt.redditapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import projekt.redditapp.Baza.Baza;
import projekt.redditapp.Baza.TblPost;
import projekt.redditapp.R;
import projekt.redditapp.models.Post;

public class PostAdapter extends ArrayAdapter<Post> {
    LayoutInflater inflater;
    ImageButton imageButton;
    Context context;

    public PostAdapter(Context context, int textViewResourceId, ArrayList<Post> posts) {
        super(context, textViewResourceId, posts);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Post post = this.getItem(position);

        if (convertView == null)
            convertView = this.inflater.inflate(R.layout.rezultat, null);

        context = this.inflater.getContext();

        ImageView ivThumbnail = convertView.findViewById(R.id.ivThumbnailRez);
        TextView tvUpRez = convertView.findViewById(R.id.tvUpvotesRez);
        TextView tvNazivRez = convertView.findViewById(R.id.tvNazivRez);
        TextView tvUserRez = convertView.findViewById(R.id.tvUserRez);
        TextView tvKreiranjeRez = convertView.findViewById(R.id.tvKreiranjeRez);
        View vPostIDrez = convertView.findViewById(R.id.vPostIDrez);
        imageButton = convertView.findViewById(R.id.ibFavoriteRez);

        if (post.getThumbnail() == null) {
            ivThumbnail.setImageDrawable(context.getDrawable(R.drawable.reddit_logo));
        } else {
            Picasso.with(context).load(post.getThumbnail()).into(ivThumbnail);
        }

        tvUpRez.setText(post.getUpvotes());
        tvNazivRez.setText(post.getNaziv());
        tvNazivRez.setTag(post.getLink());
        tvUserRez.setText(post.getUser());
        vPostIDrez.setTag(post.getPostID());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvKreiranjeRez.setText(dateFormat.format(post.getVrijemeKreiranja()));

        if (post.getID() != 0) {
            imageButton.setBackground(context.getDrawable(R.drawable.puno));
        } else {
            imageButton.setBackground(context.getDrawable(R.drawable.prazno));
        }

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TblPost tblPost = new TblPost(new Baza(context).vratiBazu());

                Drawable newImg;
                if (post.getID() != 0) {
                    newImg = getContext().getDrawable(R.drawable.prazno);
                    tblPost.Delete(post);
                    post.setID(0);
                } else {
                    newImg = getContext().getDrawable(R.drawable.puno);
                    post.setID(tblPost.Insert(post));
                }
                imageButton.setBackground(newImg);
                notifyDataSetChanged();
            }
        });

        tvNazivRez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(post.getLink()));
                getContext().startActivity(intent);
            }
        });

        return convertView;

    }

}
