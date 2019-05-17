package Model.components.Forms.PersonUppgifter;

import Model.IMat;
import Model.components.Forms.InputItem.InputItem.TextInput;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Customer;

import javax.swing.text.html.ImageView;
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

    private TextInput postaddress;
    private TextInput postcode;
    private TextInput address;
    private TextInput email;
    private TextInput phone;
    private TextInput lastname;
    private TextInput firstname;

    private Customer customer;

    public PersonUppgifter() {
        Customer customer = IMat.getInstance().getCustomer();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();
        postaddress = new TextInput("Postadress:", "Göteborg", "Ange din postadress", null);
        postcode = new TextInput("Postnummer:", "123 45", "Ange ditt postnummer", postaddress);
        address = new TextInput("Adress:", "Långgatan 6", "Ange din adress", postcode);
        email = new TextInput("Mejl:", "exempel@email.com", "Ange din mejl", address);
        phone = new TextInput("Telefonnummer:", "123456789", "Ange ditt telefonnummer", email);
        lastname = new TextInput("Efternamn:", "persson", "Ange ditt efternamn", phone);
        firstname = new TextInput("Förnamn:", "Brit", "Ange ditt förnamn", lastname);

        flowPane.getChildren().add(firstname);
        flowPane.getChildren().add(lastname);
        flowPane.getChildren().add(phone);
        flowPane.getChildren().add(email);
        flowPane.getChildren().add(address);
        flowPane.getChildren().add(postcode);
        flowPane.getChildren().add(postaddress);

        postaddress.setText(customer.getPostAddress());
        postcode.setText(customer.getPostCode());
        address.setText(customer.getAddress());
        email.setText(customer.getEmail());
        phone.setText(customer.getPhoneNumber());
        lastname.setText(customer.getLastName());
        firstname.setText(customer.getFirstName());

        hideErr();

    }

    private void addEventListeners() {
        gray.setOnMouseClicked(event -> hideErr());
        err.setOnMouseClicked(event -> event.consume());
        save.setOnAction(event -> save());
    }

    private void save(){
        customer.setPostAddress(postaddress.getInput());
        customer.setPostCode(postcode.getInput());
        customer.setAddress(address.getInput());
        customer.setEmail(email.getInput());
        customer.setPhoneNumber(phone.getInput());
        customer.setLastName(lastname.getInput());
        customer.setFirstName(firstname.getInput());
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
