package ba.unsa.etf.rpr;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class GeografijaDAO {

    private static GeografijaDAO instance = null;
    private Connection connection;
    private PreparedStatement psGlavniGrad1;
    private PreparedStatement psGlavniGrad2;
    private PreparedStatement psObrisiDrzavu1;
    private PreparedStatement psObrisiDrzavu2;
    private PreparedStatement psGradovi1;
    private PreparedStatement psGradovi2;
    private PreparedStatement psDodajGrad;
    private PreparedStatement psDodajDrzavu;
    private PreparedStatement psIzmijeniGrad;
    private PreparedStatement psNadjiDrzavu1;
    private PreparedStatement psNadjiDrzavu2;
    private static int brojGradova;
    private static int brojDrzava;

    private static void initialize() {
        instance = new GeografijaDAO();
    }

    private GeografijaDAO() {
        boolean postoji = true;
        File file = new File("baza.db");
        if (!file.exists()) postoji = false;
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:sqlite:baza.db";
        try {
            connection = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!postoji) {
            brojDrzava = 0;
            brojGradova = 0;
            try {
                PreparedStatement psKreiranjeDrzava = connection.prepareStatement
                        ("CREATE TABLE drzava { }");
                PreparedStatement psKreiranjeGradova = connection.prepareStatement
                        ("CREATE TABLE `grad` (\n" +
                                "\t`id`\tINTEGER NOT NULL,\n" +
                                "\t`naziv`\tTEXT,\n" +
                                "\t`broj_stanovnika`\tINTEGER,\n" +
                                "\t`drzava`\tINTEGER\n" +
                                ");");
                psKreiranjeDrzava.execute();
                psKreiranjeGradova.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Grad pariz = new Grad(1, "Pariz", null, 2206488);
            Grad london = new Grad(2, "London", null, 8825000);
            Grad bech = new Grad(3, "Beč", null, 1899055);
            Grad manchester = new Grad(4, "Manchester", null, 545500);
            Grad graz = new Grad(5, "Graz", null, 280200);
            Drzava francuska = new Drzava(1, "Francuska", pariz);
            Drzava velikaBritanija = new Drzava(2, "Velika Britanija", london);
            Drzava austrija = new Drzava(3, "Austrija", bech);
            pariz.setDrzava(francuska);
            london.setDrzava(velikaBritanija);
            bech.setDrzava(austrija);
            manchester.setDrzava(velikaBritanija);
            graz.setDrzava(austrija);
            dodajGrad(pariz);
            dodajGrad(london);
            dodajGrad(bech);
            dodajGrad(manchester);
            dodajGrad(graz);
            dodajDrzavu(francuska);
            dodajDrzavu(velikaBritanija);
            dodajDrzavu(austrija);
        }
        brojGradova = 5;
        brojDrzava = 3;
        try {
            psGlavniGrad1 = connection.prepareStatement
                    ("SELECT glavni_grad FROM drzava WHERE naziv=?");
            psGlavniGrad2 = connection.prepareStatement
                    ("SELECT naziv, broj_stanovnika, drzava FROM grad WHERE id=?");
            psObrisiDrzavu1 = connection.prepareStatement
                    ("DELETE FROM drzava WHERE id=?");
            psObrisiDrzavu2 = connection.prepareStatement
                    ("DELETE FROM grad WHERE drzava=?");
            psGradovi1 = connection.prepareStatement
                    ("SELECT * FROM grad ORDER BY broj_stanovnika DESC");
            psGradovi2 = connection.prepareStatement
                    ("SELECT naziv FROM drzava WHERE id=?");
            psDodajGrad = connection.prepareStatement
                    ("INSERT INTO grad(id, naziv, broj_stanovnika, drzava) VALUES(?, ?, ?, ?)");
            psDodajDrzavu = connection.prepareStatement
                    ("INSERT INTO drzava(id, naziv, glavni_grad) VALUES(?, ?, ?)");
            psIzmijeniGrad = connection.prepareStatement
                    ("UPDATE grad SET id=?, naziv=?, broj_stanovnika=?, drzava=?");
            psNadjiDrzavu1 = connection.prepareStatement
                    ("SELECT id, glavni_grad FROM drzava WHERE naziv=?");
            psNadjiDrzavu2 = connection.prepareStatement
                    ("SELECT naziv, broj_stanovnika FROM grad WHERE id=?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static GeografijaDAO getInstance() {
        if (instance == null) initialize();
        return instance;
    }

    public static void removeInstance() {
        instance = null;
    }

    public Grad glavniGrad(String drzava) {
        try {
            psGlavniGrad1.setString(1, drzava);
            ResultSet rezultat1 = psGlavniGrad1.executeQuery();
            int id = rezultat1.getInt(1);
            psGlavniGrad2.setInt(1, id);
            rezultat1.close();
            ResultSet rezultat2 = psGlavniGrad2.executeQuery();
            String nazivGrada = rezultat2.getString(1);
            int brojStanovnikaGrada = rezultat2.getInt(2);
            Grad grad = new Grad(id, nazivGrada, nadjiDrzavu(drzava), brojStanovnikaGrada);
            rezultat2.close();
            return grad;
        } catch (Exception e) {
            return null;
        }
    }

    public void obrisiDrzavu(String drzava) {
        try {
            Drzava trazena = nadjiDrzavu(drzava);
            int idDrzave = trazena.getId();
            psObrisiDrzavu1.setInt(1, idDrzave);
            psObrisiDrzavu2.setInt(1, idDrzave);
            psObrisiDrzavu1.executeUpdate();
            psObrisiDrzavu2.executeUpdate();
        } catch (Exception e) {

        }
    }

    public ArrayList<Grad> gradovi() {
        ArrayList<Grad> listaGradova = new ArrayList<>();
        try {
            ResultSet rezultat1 = psGradovi1.executeQuery();
            while (rezultat1.next()) {
                int idGrada = rezultat1.getInt(1);
                String nazivGrada = rezultat1.getString(2);
                int brojStanovnika = rezultat1.getInt(3);
                int idDrzave = rezultat1.getInt(4);
                psGradovi2.setInt(1, idDrzave);
                ResultSet rezultat2 = psGradovi2.executeQuery();
                Drzava trazena = nadjiDrzavu(rezultat2.getString(1));
                Grad trazeni = new Grad(idGrada, nazivGrada, trazena, brojStanovnika);
                listaGradova.add(trazeni);
            }
            rezultat1.close();
            return listaGradova;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void dodajGrad(Grad grad) {
        try {
            brojGradova++;
            grad.setId(brojGradova);
            psDodajGrad.setInt(1, grad.getId());
            psDodajGrad.setString(2, grad.getNaziv());
            psDodajGrad.setInt(3, grad.getBrojStanovnika());
            psDodajGrad.setInt(4, grad.getDrzava().getId());
            psDodajGrad.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void dodajDrzavu(Drzava drzava) {
        try {
            brojDrzava++;
            drzava.setId(brojDrzava);
            psDodajDrzavu.setInt(1, drzava.getId());
            psDodajDrzavu.setString(2, drzava.getNaziv());
            psDodajDrzavu.setInt(3, drzava.getGlavniGrad().getId());
            psDodajDrzavu.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public void izmijeniGrad(Grad grad) {
        try {
            psIzmijeniGrad.setInt(1, grad.getId());
            psIzmijeniGrad.setString(2, grad.getNaziv());
            psIzmijeniGrad.setInt(3, grad.getBrojStanovnika());
            psIzmijeniGrad.setInt(4, grad.getDrzava().getId());
            psIzmijeniGrad.executeUpdate();
        } catch (Exception e) {
            return;
        }
    }

    public Drzava nadjiDrzavu(String drzava) {
        try {
            psNadjiDrzavu1.setString(1, drzava);
            ResultSet rezultat1 = psNadjiDrzavu1.executeQuery();
            if (!rezultat1.next()) return null;
            int idDrzave = rezultat1.getInt(1);
            int idGrada = rezultat1.getInt(2);
            psNadjiDrzavu2.setInt(1, idGrada);
            ResultSet rezultat2 = psNadjiDrzavu2.executeQuery();
            String nazivGrada = rezultat2.getString(1);
            int brojStanovnika = rezultat2.getInt(2);
            Grad grad = new Grad(idGrada, nazivGrada, null, brojStanovnika);
            Drzava trazena = new Drzava(idDrzave, drzava, grad);
            grad.setDrzava(trazena);
            return trazena;
        } catch (Exception e) {
            return null;
        }
    }
}