package Model.components.SequensMap;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class SequensMap extends AnchorPane {

    public SequensMap() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();
    }

    private void addEventListeners(){

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SequensMap.fxml"));
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
