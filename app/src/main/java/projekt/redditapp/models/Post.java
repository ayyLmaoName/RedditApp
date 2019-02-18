package projekt.redditapp.models;

import android.graphics.Bitmap;

import java.util.Date;

public class Post {

    //polja
    private String Naziv;
    private String Link;
    private String Thumbnail;
    private String User;
    private String Upvotes;
    private Date VrijemeKreiranja;
    private String PostID;
    private int ID;


    public Post() {
    }

    public Post(String naziv, String link, String user, String upvotes, Date vrijemeKreiranja, String postID) {
        Naziv = naziv;
        Link = link;
        User = user;
        Upvotes = upvotes;
        VrijemeKreiranja = vrijemeKreiranja;
        PostID = postID;
    }

    public Post(String naziv,
                String link,
                String user, String upvotes,
                Date vrijemeKreiranja, String postID, String thumbnail) {
        Naziv = naziv;
        Link = link;
        if (thumbnail != "self") {
            Thumbnail = thumbnail;
        }
        User = user;
        Upvotes = upvotes;
        VrijemeKreiranja = vrijemeKreiranja;
        PostID = postID;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Post)) return false;
        Post other = (Post) obj;
        return other.getNaziv().equals(this.getNaziv());
    }

    //get i set

    public int getID() {
        return ID;
    }

    public void setID(int id) {
        ID = id;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String naziv) {
        Naziv = naziv;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public String getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        Thumbnail = thumbnail;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }

    public Date getVrijemeKreiranja() {
        return VrijemeKreiranja;
    }

    public void setVrijemeKreiranja(Date vrijemeKreiranja) {
        VrijemeKreiranja = vrijemeKreiranja;
    }

    public String getPostID() {
        return PostID;
    }

    public void setPostID(String postID) {
        PostID = postID;
    }

    public String getUpvotes() {
        return Upvotes;
    }

    public void setUpvotes(String upvotes) {
        Upvotes = upvotes;
    }
}

/*
   public boolean postFav(String searchItem) {

        String[] columns = { PostID };
        String selection = PostID + " =?";
        String[] selectionArgs = { searchItem };
        String limit = "1";

        Cursor cursor = db.query(NAZIV_TABLICE, columns, selection, selectionArgs, null, null, null, limit);
        boolean exists = (cursor.getCount() > 0);

        cursor.close();

        return false;
                //return exists;
                }
 */

/*
imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (postFav(IDpost)) {

                    String[] columns = {ID, NAZIV, Link, Thumbnail, User, VRIJEMEKREIRANJA, PostID};
                    String selection = NAZIV + " =?";
                    String[] selectionArgs = {IDpost};
                    String limit = "1";

                    Cursor cursor = db.query(NAZIV_TABLICE, columns, selection, selectionArgs, null, null, null, limit);
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
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    favPost.setPostID(cursor.getString(cursor.getColumnIndex(PostID)));
                    cursor.close();

                    TblPost TblPost = new TblPost(new Baza(context).vratiBazu());
                    TblPost.Delete(favPost);
                    remove(post);

                    Drawable draw = getContext().getResources().getDrawable(R.drawable.prazno);
                    imageButton.setBackground(draw);
                } else {
                    favPost favPost = new favPost();
                    favPost.setNaziv(post.getNaziv());
                    favPost.setLink(post.getLink());

                    //ovo mozda radi, ali mozda zbog picassa ce trebat izmjenit neke stvari
                    //if (rezultat.getThumbnail() != null)
                    //favPost.setThumbnail(dbBit.getImage(rezultat.getThumbnail()));


                    favPost.setUser(post.getUser());

                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
                    try {
                        favPost.setVrijemeKreiranja(sdf.parse(post.getVrijemeKreiranja() + ""));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    favPost.setPostID(post.getPostID());
                    TblPost TblPost = new TblPost(new Baza(context).vratiBazu());
                    TblPost.Insert(favPost);

                    Drawable draw = getContext().getResources().getDrawable(R.drawable.puno);
                    imageButton.setBackground(draw);
                }

            }
        });


 */
