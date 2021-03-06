package Model.pages.FirstLandingPage;

import Model.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FirstLandingPage extends AnchorPane {


    @FXML
    private Button handla;
    @FXML
    private Button hjalp;
    @FXML
    private TextArea textArea;

    public FirstLandingPage() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        textArea.getStyleClass().remove("text-input");

        addLisners();

    }

    private void addLisners(){
        handla.setOnAction(event -> Main.getNavbar().goToMainPage(true));
        hjalp.setOnAction(event -> Main.getNavbar().goToHjalp());
    }


    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FirstLandingPage.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        return fxmlLoader;
    }

    private void tryToLoadFXML(FXMLLoader fxmlLoader) {
        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

}
