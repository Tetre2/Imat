package Model.components.Forms.PersonUppgifter;

import Model.IMat;
import Model.components.Forms.Focusable;
import Model.components.Forms.InputItem.InputItem.TextInput;
import Model.components.Forms.NotValidInput;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonUppgifter extends AnchorPane implements Focusable{

    @FXML private AnchorPane rootAnchorPane;
    @FXML
    private VBox containerEditVBox;
    @FXML
    private Button save;
    @FXML
    private AnchorPane error;
    @FXML
    private Label errLabel;

    private VBox containerDoneVBox;

    private TextInput postaddress;
    private TextInput postcode;
    private TextInput address;
    private TextInput email;
    private TextInput phone;
    private TextInput lastname;
    private TextInput firstname;

    private Label requiredDescriptionLabel;
    private Label numberLabel;
    private Label mailLabel;
    private Label nameLabel;
    private Label addressLabel;
    private Label postCodeLabel;
    private Label postAddressLabel;


    private Customer customer;
    private List<TextInput> textInputs;

    public PersonUppgifter() {
        customer = IMat.getInstance().getCustomer();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        if (isUserVerified()) {
            transitionToDoneUI();
        } else {
            initEditUI();
        }

        hideErr();

    }

    private void initDoneUI() {
        rootAnchorPane.setPrefHeight(320.0);
        rootAnchorPane.setMaxHeight(320.0);
        containerEditVBox.setMaxHeight(320.0);

        containerDoneVBox = new VBox();
        containerDoneVBox.setSpacing(10.0);
        containerDoneVBox.setAlignment(Pos.CENTER_LEFT);

        updatePreviewLabels();

        Label contactHeaderLabel = new Label ("Kontaktuppgifter");
        contactHeaderLabel.getStyleClass().addAll("text", "text-md");
        contactHeaderLabel.getStyleClass().add("bold");
        numberLabel.getStyleClass().addAll("text", "text-md");
        mailLabel.getStyleClass().addAll("text", "text-md");

        Label deliveryHeaderLabel = new Label ("Leveransuppgifter");
        deliveryHeaderLabel.getStyleClass().addAll("text", "text-md");
        deliveryHeaderLabel.getStyleClass().add("bold");
        nameLabel.getStyleClass().addAll("text", "text-md");
        addressLabel.getStyleClass().addAll("text", "text-md");
        postCodeLabel.getStyleClass().addAll("text", "text-md");
        postAddressLabel.getStyleClass().addAll("text", "text-md");

        Label edit = previewLabel("Redigera");
        edit.getStyleClass().add("text-link");
        edit.setOnMouseClicked(e -> transitionToEditUI());

        containerDoneVBox.getChildren().addAll(contactHeaderLabel, numberLabel, mailLabel, deliveryHeaderLabel, nameLabel, addressLabel, postCodeLabel, postAddressLabel, edit);
        containerEditVBox.getChildren().add(containerDoneVBox);
    }

    private void updatePreviewLabels() {
        numberLabel = new Label(customer.getPhoneNumber());
        mailLabel = new Label (customer.getEmail());
        nameLabel = new Label (customer.getFirstName() + " " + customer.getLastName());
        addressLabel = new Label (customer.getAddress());
        postCodeLabel = new Label (customer.getPostCode());
        postAddressLabel = new Label (customer.getPostAddress());
    }

    private void transitionToEditUI() {
        containerEditVBox.getChildren().remove(containerDoneVBox);
        initEditUI();
    }

    private Label previewLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().addAll("text", "text-md");
        return label;
    }

    private boolean isUserVerified() {
        return IMat.getInstance().isCustomerComplete();
    }

    private void initEditUI() {
        textInputs = new ArrayList<>();
        postaddress = new TextInput("Postadress:", "Göteborg", "Ange din postadress *", null, true);
        postcode = new TextInput("Postnummer:", "123 45", "Ange ditt postnummer *", postaddress, true);
        address = new TextInput("Adress:", "Långgatan 6", "Ange din adress *", postcode, true);
        email = new TextInput("Mejl:", "exempel@email.com", "Ange din mejl *", address, true);
        phone = new TextInput("Telefonnummer:", "123456789", "Ange ditt telefonnummer", email, false);
        lastname = new TextInput("Efternamn:", "persson", "Ange ditt efternamn *", phone, true);
        firstname = new TextInput("Förnamn:", "Brit", "Ange ditt förnamn *", lastname, true);

        requiredDescriptionLabel = new Label("Fält med * måste fyllas i");

        containerEditVBox.getChildren().add(requiredDescriptionLabel);
        containerEditVBox.getChildren().add(firstname);
        containerEditVBox.getChildren().add(lastname);
        containerEditVBox.getChildren().add(phone);
        containerEditVBox.getChildren().add(email);
        containerEditVBox.getChildren().add(address);
        containerEditVBox.getChildren().add(postcode);
        containerEditVBox.getChildren().add(postaddress);

        postaddress.setText(customer.getPostAddress());
        postcode.setText(customer.getPostCode());
        address.setText(customer.getAddress());
        email.setText(customer.getEmail());
        phone.setText(customer.getPhoneNumber());
        lastname.setText(customer.getLastName());
        firstname.setText(customer.getFirstName());

        textInputs.add(firstname);
        textInputs.add(lastname);
        textInputs.add(phone);
        textInputs.add(email);
        textInputs.add(address);
        textInputs.add(postcode);
        textInputs.add(postaddress);

        if (!rootAnchorPane.getChildren().contains(save)) {
            rootAnchorPane.getChildren().add(save);
        }
    }

    private void addEventListeners() {
        save.setOnAction(event -> save());
    }

    private void save(){
        hideErr();
        clearVisuals();
        try {
            customer.setPostAddress(postaddress.getInput());
            customer.setPostCode(postcode.getInput());
            customer.setAddress(address.getInput());
            customer.setEmail(email.getInput());
            customer.setPhoneNumber(phone.getInput());
            customer.setLastName(lastname.getInput());
            customer.setFirstName(firstname.getInput());

            save.setText("Sparad");
            showSaved();
            transitionToDoneUI();
        } catch (NotValidInput notValidInput) {
            notValidInput.printStackTrace();
            showErr();
        }
    }

    private void transitionToDoneUI() {
        rootAnchorPane.getChildren().remove(save);
        containerEditVBox.getChildren().removeAll(firstname, lastname, phone, email, address, postcode, postaddress, requiredDescriptionLabel);
        initDoneUI();
    }

    private void showSaved(){
        for (TextInput t : textInputs) {
            t.setSuccess();
        }
    }

    private void clearVisuals(){
        for (TextInput t : textInputs) {
            t.setDefault();
        }
    }

    private void showErr(){
        error.setVisible(true);
        errLabel.setText("Dina uppgifter sparades inte eftersom felaktig information angivits");
    }

    private void hideErr(){
        error.setVisible(false);
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

    @Override
    public void setFocus() {
        firstname.setFocus();
    }
}
