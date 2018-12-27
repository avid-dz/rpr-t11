package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BrisanjeDrzaveController {

    public Button brisanjeBtn;
    public TextField imeDrzave;

    public void brisiDr(ActionEvent actionEvent) {
        Drzava nadjena = GeografijaDAO.getInstance().nadjiDrzavu(imeDrzave.getText());
        if (nadjena == null) {
            System.out.println("drzava uopce ni ne postoji");
        }
        else {
            GeografijaDAO.getInstance().obrisiDrzavu(imeDrzave.getText());
            System.out.println("drzava obrisana");
        }
    }
}
