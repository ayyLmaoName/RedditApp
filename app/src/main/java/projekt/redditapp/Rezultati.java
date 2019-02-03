package projekt.redditapp;

import android.graphics.Bitmap;

import java.util.Date;

public class Rezultati {

    //polja
    private String Naziv;
    private String Link;
    private Bitmap Thumbnail;
    private String User;
    private String Upvotes;
    private Date VrijemeKreiranja;
    private String PostID;


    //konstruktori
    public Rezultati() {
    }

    public Rezultati(String naziv, String link, String user, String upvotes, Date vrijemeKreiranja, String postID) {
        Naziv = naziv;
        Link = link;
        User = user;
        Upvotes = upvotes;
        VrijemeKreiranja = vrijemeKreiranja;
        PostID = postID;
    }

    public Rezultati(String naziv, String link, Bitmap thumbnail, String user, String upvotes, Date vrijemeKreiranja, String postID) {
        Naziv = naziv;
        Link = link;
        Thumbnail = thumbnail;
        User = user;
        Upvotes = upvotes;
        VrijemeKreiranja = vrijemeKreiranja;
        PostID = postID;
    }

    //get i set
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

    public Bitmap getThumbnail() {
        return Thumbnail;
    }

    public void setThumbnail(Bitmap thumbnail) {
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
