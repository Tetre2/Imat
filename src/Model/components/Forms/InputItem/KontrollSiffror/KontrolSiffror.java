package Model.components.Forms.InputItem.KontrollSiffror;

import Model.components.Forms.Focusable;
import Model.components.Forms.NotValidInput;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class KontrolSiffror extends AnchorPane implements Focusable{

    @FXML
    private TextField textField;
    @FXML
    private AnchorPane error;
    @FXML
    private Label errLabel;

    private Focusable next;

    public KontrolSiffror(Focusable next){
        this.next = next;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

    }

    private void addEventListeners() {
    //upptäcker om man skrivigt något i rutan
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isAtCharLength()) {
                if (isValid()) {
                    clearErr();
                    if(next != null)
                        next.setFocus();
                }
            }
            if (textField.getText().length() > 3) {
                textField.setText(textField.getText().substring(0, 3));
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

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KontrollSiffror.fxml"));
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


    public void setKontrolKod(int i){
        if(i >= 0){
            textField.setText(i + "");
        }
    }

    private boolean isAtCharLength() {

        return textField.getText().length() == 3;
    }

    public boolean isValid() {
        try {
            Integer.parseInt(textField.getText());
            return true;
        }catch (Exception e){
            showErr();
        }
        showErr();
        return false;
    }

    private void clearErr() {
        textField.getStyleClass().clear();
        textField.getStyleClass().addAll("text-field", "text-input");
        error.setVisible(false);
    }

    private void showErr() {
        textField.getStyleClass().add("textBoxErr");
        error.setVisible(true);
        errLabel.setText(textField.getText() + " Är inte giltliga kontrollsiffror");
    }

    @Override
    public void setFocus() {
        textField.requestFocus();
    }

    public int getInput() throws NotValidInput {
        if(isValid()){
            return Integer.parseInt(textField.getText());
        }else {
            showErr();
            throw new NotValidInput();
        }
    }

    public void setSaved(){
        textField.getStyleClass().add("textBoxSaved");
    }


}
