package ba.unsa.etf.rpr;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class IzborController {

    public RadioButton radio6;
    private ResourceBundle bundle;

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }

    public IzborController() {
        bundle = ResourceBundle.getBundle("Translation");
    }

    public void pretrazujGradEvent(ActionEvent actionEvent) {
        try {
            Stage noviStage = null;
            FXMLLoader loader = null;
            try {
                loader = new FXMLLoader(getClass().getResource("/pretragaGradaProzor.fxml"), bundle);
                loader.setController(new PretragaGradaController());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle(bundle.getString("pretragaGradaTitle"));
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
                loader = new FXMLLoader(getClass().getResource("/pretragaDrzaveProzor.fxml"), bundle);
                loader.setController(new PretragaDrzaveController());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle(bundle.getString("pretragaDrzaveTitle"));
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
                loader = new FXMLLoader(getClass().getResource("/izmjenaGradaProzor.fxml"), bundle);
                loader.setController(new IzmjenaGradaController());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle(bundle.getString("izmjenaGradaTitle"));
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
                loader = new FXMLLoader(getClass().getResource("/izmjenaDrzaveProzor.fxml"), bundle);
                loader.setController(new IzmjenaDrzaveController());
                Parent root = loader.load();
                noviStage = new Stage();
                noviStage.setResizable(false);
                noviStage.setTitle(bundle.getString("izmjenaDrzaveTitle"));
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
            loader = new FXMLLoader(getClass().getResource("/brisanjeGradaProzor.fxml"), bundle);
            loader.setController(new BrisanjeGradaController());
            Parent root = loader.load();
            noviStage = new Stage();
            noviStage.setResizable(false);
            noviStage.setTitle(bundle.getString("brisanjeGradatitle"));
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
            loader = new FXMLLoader(getClass().getResource("/brisanjeDrzaveProzor.fxml"), bundle);
            loader.setController(new BrisanjeDrzaveController());
            Parent root = loader.load();
            noviStage = new Stage();
            noviStage.setResizable(false);
            noviStage.setTitle(bundle.getString("brisanjeDrzaveTitle"));
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

    public void printPoDrzaviEvent(ActionEvent actionEvent) {
        Stage noviStage = null;
        FXMLLoader loader = null;
        try {
            loader = new FXMLLoader(getClass().getResource("/stampanjePoDrzavi.fxml"), bundle);
            loader.setController(new StampanjePoDrzaviController());
            Parent root = loader.load();
            noviStage = new Stage();
            noviStage.setResizable(false);
            noviStage.setTitle(bundle.getString("printajPoDrzaviDugme"));
            noviStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            noviStage.show();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void saveEvent(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save As");
        fileChooser.getExtensionFilters().addAll
                (new FileChooser.ExtensionFilter("PDF File", "*.pdf"),
                        new FileChooser.ExtensionFilter("Word Document", "*.docx"),
                        new FileChooser.ExtensionFilter("Excel Document", "*.xlsx"));
        File izabraniFajl = fileChooser.showSaveDialog(new Stage());
        if (izabraniFajl == null) return;
        try {
            new GradoviReport().saveAs(GeografijaDAO.getInstance().getConnection(), izabraniFajl.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void frEvent(ActionEvent actionEvent) {
        Stage ovaj = (Stage) radio6.getScene().getWindow();
        Locale.setDefault(new Locale("fr", "FR"));
        bundle = ResourceBundle.getBundle("Translation");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/izbor.fxml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ovaj.setScene(new Scene(root, 300, 200));
        ovaj.show();
    }

    public void enEvent(ActionEvent actionEvent) {
        Stage ovaj = (Stage) radio6.getScene().getWindow();
        Locale.setDefault(new Locale("en", "EN"));
        bundle = ResourceBundle.getBundle("Translation");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/izbor.fxml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ovaj.setScene(new Scene(root, 300, 200));
        ovaj.show();
    }

    public void bsEvent(ActionEvent actionEvent) {
        Stage ovaj = (Stage) radio6.getScene().getWindow();
        Locale.setDefault(new Locale("bs", "BA"));
        bundle = ResourceBundle.getBundle("Translation");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/izbor.fxml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ovaj.setScene(new Scene(root, 300, 200));
        ovaj.show();
    }

    public void deEvent(ActionEvent actionEvent) {
        Stage ovaj = (Stage) radio6.getScene().getWindow();
        Locale.setDefault(new Locale("de", "DE"));
        bundle = ResourceBundle.getBundle("Translation");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/izbor.fxml"), bundle);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ovaj.setScene(new Scene(root, 300, 200));
        ovaj.show();
    }
}
