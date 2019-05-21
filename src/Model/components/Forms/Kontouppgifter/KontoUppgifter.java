package Model.components.Forms.Kontouppgifter;

import Model.HelperClasses.UpdateButtonObservable;
import Model.IMat;
import Model.components.Forms.Focusable;
import Model.components.Forms.InputItem.InputItem.TextInput;
import Model.components.Forms.InputItem.KontrollSiffror.KontrolSiffror;
import Model.components.Forms.InputItem.MonthYearInputItem.MonthYearInputItem;
import Model.components.Forms.InputItem.KontoNummerInputItem.KontoNummerInputItem;
import Model.components.Forms.NotValidInput;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.CreditCard;

import java.io.IOException;

public class KontoUppgifter extends AnchorPane implements Focusable {

    @FXML
    private AnchorPane rootAnchorPane;
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

    private VBox containerDoneVBox = new VBox();

    private CreditCard creditCard;

    private KontrolSiffror kontrollSiffror = new KontrolSiffror(null);
    private MonthYearInputItem monthYearInputItem = new MonthYearInputItem(kontrollSiffror);
    private KontoNummerInputItem kontonummer = new KontoNummerInputItem(monthYearInputItem);
    private TextInput kontoAgare = new TextInput("Ägarens förnamn:", "Britt", "Ange ägarens förnamn till kontokortet *", kontonummer.getLimitedTextFields().get(0), true);

    private Label nameLabel = new Label();
    private Label creditCardNumberLabel = new Label();
    private Label experyDateLabel = new Label();
    private Label controlNumberLabel = new Label();

    private final String indent = "    ";
    private final UpdateButtonObservable updateButtonObservable;


    public KontoUppgifter(UpdateButtonObservable updateButtonObservable) {
        this.updateButtonObservable = updateButtonObservable;
        this.creditCard = IMat.getInstance().getCreditCard();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        if (IMat.getInstance().isCreditCardComplete()) {
            transitionToDoneUI();
        } else {
            transitionToEditUI();
        }

        addEventListeners();

        hideErr();

    }

    private void initDoneUI() {
        containerDoneVBox = new VBox();
        containerDoneVBox.setSpacing(10.0);
        containerDoneVBox.setAlignment(Pos.CENTER_LEFT);

        updatePreviewLabels();

        Label contactHeaderLabel = new Label ("Kontouppgifter");
        contactHeaderLabel.getStyleClass().addAll("text", "text-md");
        contactHeaderLabel.getStyleClass().add("bold");
        nameLabel.getStyleClass().addAll("text", "text-md");
        creditCardNumberLabel.getStyleClass().addAll("text", "text-md");
        experyDateLabel.getStyleClass().addAll("text", "text-md");
        controlNumberLabel.getStyleClass().addAll("text", "text-md");

        Label edit = previewLabel(indent + "Redigera");
        edit.getStyleClass().add("text-link");
        edit.setOnMouseClicked(e -> transitionToEditUI());

        containerDoneVBox.getChildren().addAll(contactHeaderLabel, nameLabel, creditCardNumberLabel, experyDateLabel, controlNumberLabel, edit);
        containerEditFlowPane.getChildren().add(containerDoneVBox);
    }

    private void transitionToEditUI() {
        removeDoneUI();
        initEditUI();
    }

    private void removeDoneUI() {
        containerEditFlowPane.getChildren().clear();
    }

    private void updatePreviewLabels() {
        nameLabel = new Label(indent + "Kortägare: " + creditCard.getHoldersName());
        creditCardNumberLabel = new Label (indent + "Kontonummer: " + creditCard.getCardNumber());
        experyDateLabel= new Label (indent + "Utgångsdatum: " +creditCard.getValidMonth() + "/" + creditCard.getValidYear());
        controlNumberLabel = new Label (indent + "CVC: " + creditCard.getVerificationCode());
    }

    private String getFormattedCardNumber() {
        StringBuilder str = new StringBuilder();
        int i = 0;
        for (char c : creditCard.getCardNumber().toCharArray()) {
            i++;

            if (i % 4 == 1) {
                str.append(" ");
            } else {
                str.append(c);
            }
        }
        return str.toString();
    }

    private Label previewLabel(String text) {
        Label label = new Label(text);
        label.getStyleClass().addAll("text", "text-md");
        return label;
    }

    private void initEditUI() {
        containerEditFlowPane.getChildren().clear();
        containerEditFlowPane.getChildren().add(kontoAgare);
        containerEditFlowPane.getChildren().add(kontonummer);
        containerEditFlowPane.getChildren().add(monthYearInputItem);
        containerEditFlowPane.getChildren().add(kontrollSiffror);
        containerEditFlowPane.getChildren().add(save);

        kontrollSiffror.setKontrolKod(creditCard.getVerificationCode());
        monthYearInputItem.setMonth(creditCard.getValidMonth() + "");
        monthYearInputItem.setYear(creditCard.getValidYear() + "");
        kontonummer.setCardNumber(creditCard.getCardNumber());
        kontoAgare.setText(creditCard.getHoldersName());

        if (!rootVBox.getChildren().contains(rootButton)) {
            rootVBox.getChildren().add(rootButton);
        }
    }

    private void addEventListeners() {
        save.setOnAction(event -> saveInfo());
    }

    private void saveInfo(){
        hideErr();
        clearVisuals();
        try {
            String cardType;
            if (kontonummer.getInput().substring(0,1).equals("5")) {
                cardType = "Master Card";
            } else {
                cardType = "Visa";
            }

            creditCard.setCardType(cardType);
            creditCard.setVerificationCode(kontrollSiffror.getInput());
            creditCard.setValidMonth(monthYearInputItem.getMonth());
            creditCard.setValidYear(monthYearInputItem.getYear());
            creditCard.setCardNumber(kontonummer.getInput());
            creditCard.setHoldersName(kontoAgare.getInput());
            save.setText("Sparad");

            showSaved();
            transitionToDoneUI();
            updateButtonObservable.updateButton();
        } catch (NotValidInput notValidInput) {
            System.out.println("Input not Valid");
            showErr();
        }
    }

    private void transitionToDoneUI() {
        removeEditUI();
        initDoneUI();
    }

    private void removeEditUI() {


        rootVBox.getChildren().remove(rootButton);
        containerEditFlowPane.getChildren().clear();

    }

    private void clearVisuals(){
        kontrollSiffror.setDefault();
        monthYearInputItem.setDefaults();
        kontonummer.setDefault();
        kontoAgare.setDefault();
    }

    private void showSaved(){
        kontrollSiffror.setSaved();
        monthYearInputItem.setSaved();
        kontonummer.setSaved();
        kontoAgare.setSuccess();
    }

    private void showErr(){
        error.setVisible(true);
        errLabel.setText("Dina uppgifter sparades inte eftersom felaktig information angivits");
    }

    private void hideErr(){
        error.setVisible(false);
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KontoUppgifter.fxml"));
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
        kontoAgare.setFocus();
    }
}
