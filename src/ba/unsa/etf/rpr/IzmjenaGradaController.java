package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class IzmjenaGradaController {

    public TextField stariNazivGr;
    public TextField noviNazivGr;
    public TextField drzava;
    public TextField noviBrStan;
    public Button mijenjanjeDr;

    public void mijenjanjeGr(ActionEvent actionEvent) {
        Grad nadjeni = GeografijaDAO.getInstance().nadjiGrad(stariNazivGr.getText());
        Drzava nadjena = GeografijaDAO.getInstance().nadjiDrzavu(drzava.getText());
        if (nadjeni == null) {
            System.out.println("nema grada");
        }
        else {
            GeografijaDAO.getInstance().izmijeniGrad(new Grad(nadjeni.getId(), noviNazivGr.getText(), nadjena, Integer.parseInt(noviBrStan.getText())));
            System.out.println("grad izmijenjen");
        }
    }
}
