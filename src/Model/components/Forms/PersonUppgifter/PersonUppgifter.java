package Model.components.Forms.PersonUppgifter;

import Model.components.Forms.InputItem.InputItem.InputItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
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
        flowPane.getChildren().add(new InputItem("Förnamn:", "Brit", "Ange ditt förnamn"));
        flowPane.getChildren().add(new InputItem("Efternamn:", "persson", "Ange ditt efternamn"));
        flowPane.getChildren().add(new InputItem("Telefonnummer:", "123456789", "Ange ditt telefonnummer"));
        flowPane.getChildren().add(new InputItem("Mejl:", "exempel@email.com", "Ange din mejl"));
        flowPane.getChildren().add(new InputItem("Adress:", "Långgatan 6", "Ange din adress"));
        flowPane.getChildren().add(new InputItem("Postnummer:", "123 45", "Ange ditt postnummer"));
        flowPane.getChildren().add(new InputItem("Postadress:", "Göteborg", "Ange din postadress"));
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
