package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PretragaDrzaveController {

    public TextField tfNazivDr;
    public Button dugmeTraziDr;

    public void pretragaDr(ActionEvent actionEvent) {
        if (GeografijaDAO.getInstance().nadjiDrzavu(tfNazivDr.getText()) == null) {
            System.out.println("nema drzave");
        }
        else {
            System.out.println(GeografijaDAO.getInstance().nadjiDrzavu(tfNazivDr.getText()).ispisiDrzavu());
        }
    }
}
