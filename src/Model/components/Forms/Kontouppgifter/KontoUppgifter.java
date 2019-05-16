package Model.components.Forms.Kontouppgifter;

import Model.IMat;
import Model.components.Forms.InputItem.InputItem.TextInput;
import Model.components.Forms.InputItem.KontrollSiffror.KontrolSiffror;
import Model.components.Forms.InputItem.MonthYearInputItem.MonthYearInputItem;
import Model.components.Forms.InputItem.KontoNummerInputItem.KontoNummerInputItem;
import Model.components.Forms.NotValidInput;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CreditCard;

import java.io.IOException;

public class KontoUppgifter extends AnchorPane {

    @FXML
    private FlowPane flowPane;
    @FXML
    private Button save;
    @FXML
    private AnchorPane gray;
    @FXML
    private AnchorPane err;

    private CreditCard creditCard;

    private KontrolSiffror kontrollSiffror;
    private MonthYearInputItem monthYearInputItem;
    private KontoNummerInputItem kontonummer;
    private TextInput kontoAgare;

    public KontoUppgifter() {
        this.creditCard = IMat.getInstance().getCreditCard();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        kontrollSiffror = new KontrolSiffror(null);
        monthYearInputItem = new MonthYearInputItem(kontrollSiffror);
        kontonummer = new KontoNummerInputItem(monthYearInputItem);
        kontoAgare = new TextInput("Ägarens förnamn:", "Britt", "Ange ägarens förnamn till kontokortet", kontonummer.getLimitedTextFields().get(0));

        flowPane.getChildren().add(kontoAgare);
        flowPane.getChildren().add(kontonummer);
        flowPane.getChildren().add(monthYearInputItem);
        flowPane.getChildren().add(kontrollSiffror);

        kontrollSiffror.setKontrolKod(creditCard.getVerificationCode());
        monthYearInputItem.setMonth(creditCard.getValidMonth() + "");
        monthYearInputItem.setYear(creditCard.getValidYear() + "");
        kontonummer.setCardNumber(creditCard.getCardNumber());
        kontoAgare.setText(creditCard.getHoldersName());

        hideErr();

    }

    private void addEventListeners() {

        gray.setOnMouseClicked(event -> hideErr());
        err.setOnMouseClicked(event -> event.consume());
        save.setOnAction(event -> saveInfo());

    }

    private void saveInfo(){

        try {
            creditCard.setVerificationCode(kontrollSiffror.getInput());
            creditCard.setValidMonth(monthYearInputItem.getMonth());
            creditCard.setValidYear(monthYearInputItem.getYear());
            creditCard.setCardNumber(kontonummer.getInput());
            creditCard.setHoldersName(kontoAgare.getInput());

        } catch (NotValidInput notValidInput) {
            System.out.println("Input not Valid");
        }
    }

    private void showErr(){
        gray.setVisible(true);
    }

    private void hideErr(){
        gray.setVisible(false);
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

}
