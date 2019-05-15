package Model.pages.MinaSidor;

import Model.components.Navbar.Navbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

import java.io.IOException;

public class MinaSidor extends AnchorPane {


    @FXML
    private AnchorPane TopNav;
    @FXML
    private AnchorPane pane;

    public MinaSidor(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();


        TopNav.getChildren().add(new Navbar());
        pane.getChildren().add(new TilePane());


    }

    private void addEventListeners(){

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MinaSidor.fxml"));
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
