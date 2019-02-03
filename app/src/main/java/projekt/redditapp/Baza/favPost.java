package projekt.redditapp.Baza;

import android.graphics.Bitmap;

import java.util.Date;

public class favPost {

    //polja
    private int ID;
    private String Naziv;
    private String Link;
    private Bitmap Thumbnail;
    private String User;
    private Date VrijemeKreiranja;
    private String PostID;


    //konstruktori
    public favPost() {
    }

    public favPost(int ID, String naziv, String link, String user, Date vrijemeKreiranja, String PostID) { //jer thumbnail nije obavezan npr text post
        this.ID = ID;
        Naziv = naziv;
        Link = link;
        User = user;
        VrijemeKreiranja = vrijemeKreiranja;
    }

    public favPost(int ID, String naziv, String link, Bitmap thumbnail, String user, Date vrijemeKreiranja, String PostID) {
        this.ID = ID;
        Naziv = naziv;
        Link = link;
        Thumbnail = thumbnail;
        User = user;
        VrijemeKreiranja = vrijemeKreiranja;
    }


    //get i set za sva polja
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
}
