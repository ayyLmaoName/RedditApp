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
