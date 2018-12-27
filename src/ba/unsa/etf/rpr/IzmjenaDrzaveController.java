package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class IzmjenaDrzaveController {

    public TextField stariNazivDr;
    public TextField noviNazivDr;
    public TextField noviNazivGr;
    public Button mijenjanjeDr;

    public void mijenjanjeDr(ActionEvent actionEvent) {
        Drzava nadjena = GeografijaDAO.getInstance().nadjiDrzavu(stariNazivDr.getText());
        Grad nadjeni = GeografijaDAO.getInstance().nadjiGrad(noviNazivGr.getText());
        if (nadjena == null) {
            System.out.println("nema drzave");
        }
        else {
            GeografijaDAO.getInstance().izmijeniDrzavu(new Drzava(nadjena.getId(), noviNazivDr.getText(), nadjeni));
            System.out.println("drzava izmijenjena");
        }
    }
}
