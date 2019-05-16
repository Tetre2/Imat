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


    public KontoUppgifter() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        flowPane.getChildren().add(new KontoInputItem("Kontonummer:", "Ange kontokortets kontonummer"));
        flowPane.getChildren().add(new InputItem("Ägarens förnamn:", "Britt", "Ange ägarens förnamn till kontokortet"));
        flowPane.getChildren().add(new DayMonthInputItem());
        flowPane.getChildren().add(new InputItem("Kontrollsiffror:", "XXX", "Ange de tre kontrollsiffrorna", 100));

    }

    private void addEventListeners() {


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
