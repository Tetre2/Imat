package Model.components.Navbar;

import Model.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class Navbar extends AnchorPane {

    @FXML
    Hyperlink iMatLabel;
    @FXML
    private Button hjalp;
    @FXML
    private Button kvitton;
    @FXML
    private Button minaSidor;
    @FXML
    private TextField searchBar;


    public Navbar(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();
    }

    @FXML
    private void onIMatPressed() {
        Main.setSceneToMainPage();
    }

    private void addEventListeners(){

        hjalp.setOnAction(e -> Main.setSceneToHjalp());
        kvitton.setOnAction(e -> Main.setSceneToHistorik());
        minaSidor.setOnAction(e -> Main.setSceneToMinaSidor());
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

    @FXML
    private void onSearchTyped() {
        System.out.println(searchBar.getCharacters());

    }


}
