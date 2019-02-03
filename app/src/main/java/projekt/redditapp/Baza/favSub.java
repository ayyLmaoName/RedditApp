package projekt.redditapp.Baza;

public class favSub {
    //polja
    private int ID;
    private String Naziv;
    private String Link;
    private String ZadnjiViden; //zadnji viden post na odredenom subredditu, koristi se kako bi objavili korisniku jel ima novih postova


    //konstruktori
    public favSub() {
    }

    public favSub(int ID, String naziv, String link, String zadnjiViden) {
        this.ID = ID;
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
