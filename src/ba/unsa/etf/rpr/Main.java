package ba.unsa.etf.rpr;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(ispisiGradove());
        glavniGrad();
    }

    private static void glavniGrad() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO dao = GeografijaDAO.getInstance();
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite naziv države:");
        String nazivDrzave = ulaz.nextLine();
        if (dao.nadjiDrzavu(nazivDrzave) == null) System.out.println("Nepostojeća država");
        else {
            Drzava drzava = dao.nadjiDrzavu(nazivDrzave);
            System.out.println("Glavni grad države " + drzava.getNaziv() + " je " + drzava.getGlavniGrad().getNaziv());
        }
    }

    public static String ispisiGradove() {
        GeografijaDAO.removeInstance();
        File dbfile = new File("baza.db");
        dbfile.delete();
        GeografijaDAO dao = GeografijaDAO.getInstance();
        String sadrzaj = "";
        for (Grad grad : dao.gradovi()) {
            sadrzaj += grad.getNaziv();
            sadrzaj += " ";
            sadrzaj += "(";
            sadrzaj += grad.getDrzava().getNaziv();
            sadrzaj += ") - ";
            sadrzaj += grad.getBrojStanovnika();
            sadrzaj += "\n";
        }
        return sadrzaj;
    }
}
