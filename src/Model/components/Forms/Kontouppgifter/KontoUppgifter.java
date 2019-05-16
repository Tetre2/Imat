package Model.components.Forms.Kontouppgifter;

import Model.components.Forms.InputItem.DayMonthInputItem.DayMonthInputItem;
import Model.components.Forms.InputItem.InputItem.InputItem;
import Model.components.Forms.InputItem.KontoInputItem.KontoInputItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

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

    public KontoUppgifter() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        InputItem kontrollSiffror = new InputItem("Kontrollsiffror:", "XXX", "Ange de tre kontrollsiffrorna", 100, null);
        DayMonthInputItem dayMonthInputItem = new DayMonthInputItem(kontrollSiffror);
        KontoInputItem kontonummer = new KontoInputItem(dayMonthInputItem.getLimitedTextFields().get(0));
        InputItem kontoAgare = new InputItem("Ägarens förnamn:", "Britt", "Ange ägarens förnamn till kontokortet", kontonummer.getLimitedTextFields().get(0));

        flowPane.getChildren().add(kontoAgare);
        flowPane.getChildren().add(kontonummer);
        flowPane.getChildren().add(dayMonthInputItem);
        flowPane.getChildren().add(kontrollSiffror);

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
