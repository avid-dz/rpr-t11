package ba.unsa.etf.rpr;

public class Grad {

    private int id;
    private String naziv;
    private Drzava drzava;
    private int brojStanovnika;

    public Grad() {}

    public Grad(int id, String naziv, Drzava drzava, int brojStanovnika) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
        this.brojStanovnika = brojStanovnika;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Drzava getDrzava() {
        return drzava;
    }

    public void setDrzava(Drzava drzava) {
        this.drzava = drzava;
    }

    public void setBrojStanovnika(int i) {
        this.brojStanovnika = i;
    }

    public int getBrojStanovnika() {
        return brojStanovnika;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String ispisiGrad() {
        return getNaziv() + " (" + getBrojStanovnika() + " stanovnika) u državi " + getDrzava().getNaziv();
    }
}
