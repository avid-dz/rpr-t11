package ba.unsa.etf.rpr;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Gradovi su:\n" + ispisiGradove());
        glavniGrad();
    }

    private static void glavniGrad() {
        Scanner ulaz = new Scanner(System.in);
        System.out.println("Unesite naziv drzave:");
        String nazivDrzave = ulaz.next();

    }

    public static String ispisiGradove() {
        return "";
    }
}
