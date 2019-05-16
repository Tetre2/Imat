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
    @FXML
    private AnchorPane gray;
    @FXML
    private AnchorPane err;


    public PersonUppgifter() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();
        InputItem postaddress = new InputItem("Postadress:", "Göteborg", "Ange din postadress", null);
        InputItem postcode = new InputItem("Postnummer:", "123 45", "Ange ditt postnummer", postaddress);
        InputItem address = new InputItem("Adress:", "Långgatan 6", "Ange din adress", postcode);
        InputItem email = new InputItem("Mejl:", "exempel@email.com", "Ange din mejl", address);
        InputItem phone = new InputItem("Telefonnummer:", "123456789", "Ange ditt telefonnummer", email);
        InputItem lastname = new InputItem("Efternamn:", "persson", "Ange ditt efternamn", phone);
        InputItem firstname = new InputItem("Förnamn:", "Brit", "Ange ditt förnamn", lastname);



        flowPane.getChildren().add(firstname);
        flowPane.getChildren().add(lastname);
        flowPane.getChildren().add(phone);
        flowPane.getChildren().add(email);
        flowPane.getChildren().add(address);
        flowPane.getChildren().add(postcode);
        flowPane.getChildren().add(postaddress);

        hideErr();

    }

    private void addEventListeners() {
        gray.setOnMouseClicked(event -> hideErr());
        err.setOnMouseClicked(event -> event.consume());
        save.setOnAction(event -> showErr());
    }

    private void showErr(){
        gray.setVisible(true);
    }

    private void hideErr(){
        gray.setVisible(false);
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
