package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BrisanjeGradaController {

    public Button brisanjeBtn;
    public TextField imeGrada;

    public void brisiGr(ActionEvent actionEvent) {
        Grad nadjeni = GeografijaDAO.getInstance().nadjiGrad(imeGrada.getText());
        if (nadjeni == null) {
            System.out.println("grad uopce ni ne postoji");
        }
        else {
            GeografijaDAO.getInstance().obrisiGrad(imeGrada.getText());
            System.out.println("grad obrisan");
        }
    }
}
