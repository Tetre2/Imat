package Model.components.Forms.Kontouppgifter;

import Model.IMat;
import Model.components.Forms.Focusable;
import Model.components.Forms.InputItem.InputItem.TextInput;
import Model.components.Forms.InputItem.KontrollSiffror.KontrolSiffror;
import Model.components.Forms.InputItem.MonthYearInputItem.MonthYearInputItem;
import Model.components.Forms.InputItem.KontoNummerInputItem.KontoNummerInputItem;
import Model.components.Forms.NotValidInput;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CreditCard;

import java.io.IOException;

public class KontoUppgifter extends AnchorPane implements Focusable {

    @FXML
    private FlowPane flowPane;
    @FXML
    private Button save;
    @FXML
    private AnchorPane error;
    @FXML
    private Label errLabel;

    private CreditCard creditCard;

    private KontrolSiffror kontrollSiffror;
    private MonthYearInputItem monthYearInputItem;
    private KontoNummerInputItem kontonummer;
    private TextInput kontoAgare;

    public KontoUppgifter() {
        this.creditCard = IMat.getInstance().getCreditCard();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        kontrollSiffror = new KontrolSiffror(null);
        monthYearInputItem = new MonthYearInputItem(kontrollSiffror);
        kontonummer = new KontoNummerInputItem(monthYearInputItem);
        kontoAgare = new TextInput("Ägarens förnamn:", "Britt", "Ange ägarens förnamn till kontokortet *", kontonummer.getLimitedTextFields().get(0), true);

        flowPane.getChildren().add(kontoAgare);
        flowPane.getChildren().add(kontonummer);
        flowPane.getChildren().add(monthYearInputItem);
        flowPane.getChildren().add(kontrollSiffror);

        kontrollSiffror.setKontrolKod(creditCard.getVerificationCode());
        monthYearInputItem.setMonth(creditCard.getValidMonth() + "");
        monthYearInputItem.setYear(creditCard.getValidYear() + "");
        kontonummer.setCardNumber(creditCard.getCardNumber());
        kontoAgare.setText(creditCard.getHoldersName());

        addEventListeners();

        hideErr();

    }

    private void addEventListeners() {
        save.setOnAction(event -> saveInfo());

    }

    private void saveInfo(){
        hideErr();
        clearVisuals();
        try {
            creditCard.setVerificationCode(kontrollSiffror.getInput());
            creditCard.setValidMonth(monthYearInputItem.getMonth());
            creditCard.setValidYear(monthYearInputItem.getYear());
            creditCard.setCardNumber(kontonummer.getInput());
            creditCard.setHoldersName(kontoAgare.getInput());
            save.setText("Sparad");
            showSaved();
        } catch (NotValidInput notValidInput) {
            System.out.println("Input not Valid");
            showErr();
        }
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
