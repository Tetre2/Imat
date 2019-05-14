package Model.components.Navbar;

import Model.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Navbar extends AnchorPane {

    @FXML
    private Button hjalp;
    @FXML
    private Button kvitton;
    @FXML
    private Button minaSidor;
    @FXML
    private Label escape;


    public Navbar(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

    }


    private void addEventListeners(){

        hjalp.setOnAction(e -> Main.setSceneToHjalp());
        kvitton.setOnAction(e -> Main.setSceneToHistorik());
        minaSidor.setOnAction(e -> Main.setSceneToMinaSidor());
        escape.setOnMouseClicked(e -> Main.setSceneToMainPage());

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Navbar.fxml"));
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
