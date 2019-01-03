package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;

public class StampanjePoDrzaviController {

    public TextField tf;

    public void printajPoDrzavi(ActionEvent actionEvent) {
        try {
            new GradoviReport().showReport1(GeografijaDAO.getInstance().getConnection(), tf.getText());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
