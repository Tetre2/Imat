package Model.components.Forms.InputItem.LimitedTextField;

import Model.components.Forms.ValidityCheckable;
import Model.components.Forms.Focusable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class LimitedTextField extends AnchorPane implements Focusable {

    @FXML
    private Label label;
    @FXML
    private Label tooltip;
    @FXML
    private TextField textField;

    private Focusable next;
    private int charLength;
    private ValidityCheckable parent;

    public LimitedTextField(String preView, String tooltip, String separator, int charLength, Focusable next, int width, ValidityCheckable parent) {
        this.next = next;
        this.charLength = charLength;
        this.parent = parent;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        if (width != 0) {
            textField.setMaxWidth(width);
        }
        this.tooltip.setText(tooltip);
        label.setText(separator);
        textField.setPromptText(preView);


        addEventListeners();
    }

    public LimitedTextField(String preView, String tooltip, String separator, int charLength, Focusable next, ValidityCheckable parent) {
        this(preView, tooltip, separator, charLength, next, 0, parent);
    }

    private void addEventListeners() {

        //upptäcker om man skrivigt något i rutan
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isAtCharLength()) {
                if (isValid()) {
                    clearErr();
                    next.setFocus();
                }
            }
            if (textField.getText().length() > charLength) {
                textField.setText(textField.getText().substring(0, charLength));
            }
        });

        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                clearErr();
            }
            if (wasFocused) {
                isValid();
            }
        });

    }

    public void setTextFieldText(String text) {
        textField.setText(text);
    }

    private boolean isAtCharLength() {

        return textField.getText().length() == charLength;
    }

    public boolean isValid() {

        if (parent.checkValidity(textField.getText())) {
            clearErr();
            return true;
        }

        showErr();
        return false;
    }

    private void clearErr() {
        textField.getStyleClass().clear();
        textField.getStyleClass().addAll("text-field", "text-input");
    }

    private void showErr() {
        textField.getStyleClass().add("textBoxErr");
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

    public void setNext(Focusable next) {
        this.next = next;
    }

    public String getInput() {
        return textField.getText();
    }

    public void setSaved() {
        textField.getStyleClass().add("textBoxSaved");
    }

}