package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PretragaGradaController {

    public TextField tfNazivGr;
    public Button dugmeTraziGr;

    public void pretragaGr(ActionEvent actionEvent) {
        if (GeografijaDAO.getInstance().nadjiGrad(tfNazivGr.getText()) == null) {
            System.out.println("nema grada");
        }
        else {
            System.out.println(GeografijaDAO.getInstance().nadjiGrad(tfNazivGr.getText()).ispisiGrad());
        }
    }
}
