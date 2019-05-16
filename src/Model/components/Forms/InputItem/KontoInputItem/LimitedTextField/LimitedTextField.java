package Model.components.Forms.InputItem.KontoInputItem.LimitedTextField;

import Model.components.Forms.Focusalbe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LimitedTextField extends AnchorPane implements Focusalbe {

    @FXML
    private Label label;
    @FXML
    private TextField textField;

    private Focusalbe next;

    public LimitedTextField(String preView, String separator, int charLength, Focusalbe next, int width) {
        this.next = next;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        if(width != 0){
            textField.setMaxWidth(width);
        }
        label.setText(separator);
        textField.setPromptText(preView);


        addEventListeners();
    }

    public LimitedTextField(String preView, String separator, int charLength, Focusalbe next) {
        this(preView, separator, charLength, next, 0);
    }

    private void addEventListeners() {

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LimitedTextField.fxml"));
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
        textField.requestFocus();
    }

    public void setNext(Focusalbe next) {
        this.next = next;
    }
}