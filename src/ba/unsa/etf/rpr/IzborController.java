package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class IzborController {

    public void pretrazujGradEvent(ActionEvent actionEvent) {
        try {
            Stage noviStage = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(getClass().getResource("pretragaGradaProzor.fxml"));
                loader.setController(new PretragaGradaController());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle("Pretraga grada");
                noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                noviStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    public void pretrazujDrzavuEvent(ActionEvent actionEvent) {
        try {
            Stage noviStage = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(getClass().getResource("pretragaDrzaveProzor.fxml"));
                loader.setController(new PretragaDrzaveController());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle("Pretraga države");
                noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                noviStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    public void mijenjajGradEvent(ActionEvent actionEvent) {
        try {
            Stage noviStage = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(getClass().getResource("izmjenaGradaProzor.fxml"));
                loader.setController(new IzmjenaGradaController());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle("Izmjena grada");
                noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                noviStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    public void mijenjajDrzavuEvent(ActionEvent actionEvent) {
        try {
            Stage noviStage = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(getClass().getResource("izmjenaDrzaveProzor.fxml"));
                loader.setController(new IzmjenaDrzaveController());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle("Izmjena države");
                noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                noviStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    public void brisiGradEvent(ActionEvent actionEvent) {
        Stage noviStage = null;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getResource("brisanjeGradaProzor.fxml"));
            loader.setController(new BrisanjeGradaController());
            Parent root = loader.load();
            noviStage = new Stage();
            noviStage.setResizable(false);
            noviStage.setTitle("Brisanje grada");
            noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            noviStage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void brisiDrzavuEvent(ActionEvent actionEvent) {
        Stage noviStage = null;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getResource("brisanjeDrzaveProzor.fxml"));
            loader.setController(new BrisanjeDrzaveController());
            Parent root = loader.load();
            noviStage = new Stage();
            noviStage.setResizable(false);
            noviStage.setTitle("Brisanje države");
            noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            noviStage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void stampajGradovePoredanePoBrojuStanovnika(ActionEvent actionEvent) {
        try {
            new GradoviReport().showReport(GeografijaDAO.getInstance().getConnection());
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}
