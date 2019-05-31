package Model.components.Forms.PersonUppgifter;

import Model.HelperClasses.UpdateButtonObservable;
import Model.IMat;
import Model.components.Forms.Focusable;
import Model.components.Forms.InputItem.InputItem.TextInput;
import Model.components.Forms.NotValidInput;
import Model.components.TitledSection.TitledSection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.Customer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonUppgifter extends AnchorPane implements Focusable{

    @FXML private AnchorPane rootAnchorPane;
    @FXML
    private FlowPane containerEditFlowPane;
    @FXML
    private Button save;
    @FXML
    private AnchorPane error;
    @FXML
    private Label errLabel;
    @FXML
    private AnchorPane rootButton;
    @FXML
    private VBox rootVBox;


    private VBox containerDoneVBox;

    private TextInput postaddress;
    private TextInput postcode;
    private TextInput address;
    private TextInput email;
    private TextInput phone;
    private TextInput lastname;
    private TextInput firstname;

    private Label numberLabel;
    private Label mailLabel;
    private Label nameLabel;
    private Label addressLabel;

    private Customer customer;
    private List<TextInput> textInputs;

    private final String indent = "    ";
    private final String indent2 = "          ";
    private final String indent4 = "                  ";

    private final UpdateButtonObservable updateButtonObservable;

    private TitledSection parent;

    public PersonUppgifter(UpdateButtonObservable updateButtonObservable, TitledSection parent) {
        this.updateButtonObservable = updateButtonObservable;
        this.parent = parent;
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

        containerDoneVBox = new VBox();
        containerDoneVBox.setSpacing(10.0);
        containerDoneVBox.setAlignment(Pos.CENTER_LEFT);

        parent.setTitle("1. Kontrollera dina uppgifter");
        //parent.setTooltip("Kontrollera dina uppgifter");

        updatePreviewLabels();

        Label contactHeaderLabel = new Label (indent2 + "Kontaktuppgifter");
        contactHeaderLabel.getStyleClass().addAll("text", "text-md");
        contactHeaderLabel.getStyleClass().add("bold");

        Label deliveryHeaderLabel = new Label (indent2 + "Leveransuppgifter");
        deliveryHeaderLabel.getStyleClass().addAll("text", "text-md");
        deliveryHeaderLabel.getStyleClass().add("bold");

        Label edit = previewLabel(indent2 + "Redigera");
        edit.getStyleClass().addAll("text-link", "text-md");
        edit.setOnMouseClicked(e -> transitionToEditUI());

        containerDoneVBox.getChildren().addAll(contactHeaderLabel, numberLabel, mailLabel, deliveryHeaderLabel, nameLabel, addressLabel, edit);
        containerEditFlowPane.getChildren().add(containerDoneVBox);
    }

    private void updatePreviewLabels() {
        numberLabel = new Label(indent2 + customer.getPhoneNumber());
        numberLabel.getStyleClass().add("text-md");

        mailLabel = new Label (indent2 + customer.getEmail());
        mailLabel.getStyleClass().add("text-md");

        nameLabel = new Label (indent2 + customer.getFirstName() + " " + customer.getLastName());
        nameLabel.getStyleClass().add("text-md");

        addressLabel = new Label (indent2 + customer.getAddress() + ", " + customer.getPostCode() + ", " + customer.getPostAddress());
        addressLabel.getStyleClass().add("text-md");
    }

    private void transitionToEditUI() {
        containerEditFlowPane.getChildren().clear();
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
        postaddress = new TextInput("Postadress:", "", "Ange din postadress ex. Göteborg", null, true);
        postcode = new TextInput("Postnummer:", "", "Ange ditt postnummer ex. 654 23", postaddress, true);
        address = new TextInput("Adress:", "", "Ange din adress ex. Långgatan 6", postcode, true);
        email = new TextInput("Mail:", "", "Ange din mail ex. brit@email.com", address, true);
        phone = new TextInput("Telefonnummer:", "", "Ange ditt telefonnummer ex. 031458295", email, true);
        lastname = new TextInput("Efternamn:", "", "Ange ditt efternamn ex. Person", phone, true);
        firstname = new TextInput("Förnamn:", "", "Ange ditt förnamn ex. Brit", lastname, true);

        parent.setTitle("1. Skriv in dina uppgifter");
        //parent.setTooltip("Skriv in dina uppgifter");


        containerEditFlowPane.getChildren().add(firstname);
        containerEditFlowPane.getChildren().add(lastname);
        containerEditFlowPane.getChildren().add(phone);
        containerEditFlowPane.getChildren().add(email);
        containerEditFlowPane.getChildren().add(address);
        containerEditFlowPane.getChildren().add(postcode);
        containerEditFlowPane.getChildren().add(postaddress);

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

        save.setText("Spara");

        if (!rootVBox.getChildren().contains(rootButton)) {
            rootVBox.getChildren().add(rootButton);
        }
    }

    private void addEventListeners() {
        save.setOnAction(event -> save());
    }

    private void save(){
        hideErr();
        clearVisuals();

        boolean isValied = true;

        try {
            firstname.setHasBinFocused(true);
            customer.setFirstName(firstname.getInput());
        }catch (Exception e){
            isValied = false;
        }

        try {
            lastname.setHasBinFocused(true);
            customer.setLastName(lastname.getInput());
        }catch (Exception e){
            isValied = false;
        }

        try {
            phone.setHasBinFocused(true);
            customer.setPhoneNumber(phone.getInput());
        }catch (Exception e){
            isValied = false;
        }

        try {
            email.setHasBinFocused(true);
            customer.setEmail(email.getInput());
        }catch (Exception e){
            isValied = false;
        }

        try {
            address.setHasBinFocused(true);
            customer.setAddress(address.getInput());
        }catch (Exception e){
            isValied = false;
        }

        try {
            postcode.setHasBinFocused(true);
            customer.setPostCode(postcode.getInput());
        }catch (Exception e){
            isValied = false;
        }

        try {
            postaddress.setHasBinFocused(true);
            customer.setPostAddress(postaddress.getInput());

        } catch (NotValidInput notValidInput) {
            isValied = false;
        }

        if(isValied){
            save.setText("Sparad");
            showSaved();
            transitionToDoneUI();
            updateButtonObservable.updateButton();
        }else {
            showErr();
        }

    }

    private void transitionToDoneUI() {
        rootVBox.getChildren().remove(rootButton);
        containerEditFlowPane.getChildren().clear();
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
