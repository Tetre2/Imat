package Model.components.Forms.PersonUppgifter;

import Model.IMat;
import Model.components.Forms.InputItem.InputItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class PersonUppgifter extends AnchorPane {

    @FXML
    private FlowPane flowPane;
    @FXML
    private Button save;


    public PersonUppgifter() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();
        flowPane.getChildren().add(new InputItem("Förnamn:", "Brit"));
        flowPane.getChildren().add(new InputItem("Efternamn:", "persson"));
        flowPane.getChildren().add(new InputItem("Telefon Nummer:", "123456789"));
        flowPane.getChildren().add(new InputItem("Mejl:", "exempel@email.com"));
        flowPane.getChildren().add(new InputItem("Adress:", "Långgatan 6"));
        flowPane.getChildren().add(new InputItem("Post nummer:", "123 45"));
        flowPane.getChildren().add(new InputItem("Post adress:", "Göteborg"));

    }

    private void addEventListeners() {

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PersonUppgifter.fxml"));
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
