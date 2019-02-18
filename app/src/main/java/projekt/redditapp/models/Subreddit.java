package projekt.redditapp.models;

public class Subreddit {
    //polja
    private int ID;
    private String Naziv;
    private String Link;
    private String ZadnjiViden; //zadnji viden post na odredenom subredditu, koristi se kako bi objavili korisniku jel ima novih postova

    public Subreddit() {
    }

    public Subreddit(String naziv, String link, String zadnjiViden) {
        Naziv = naziv;
        Link = link;
        ZadnjiViden = zadnjiViden;
    }

    //get i set
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

    public String getZadnjiViden() {
        return ZadnjiViden;
    }

    public void setZadnjiViden(String zadnjiViden) {
        ZadnjiViden = zadnjiViden;
    }
}
