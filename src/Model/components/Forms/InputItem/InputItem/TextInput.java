package Model.components.Forms.InputItem.InputItem;

import Model.components.Forms.Focusable;
import Model.components.Forms.NotValidInput;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TextInput extends AnchorPane implements Focusable{


    @FXML
    private TextField textField;
    @FXML
    private Label label;
    @FXML
    private Label tooltip;
    @FXML
    private AnchorPane error;
    @FXML
    private Label errLabel;

    private Focusable next;
    private Boolean needed;

    public TextInput(String label, String preVeiwText, String tooltip, Focusable next, Boolean needed) {
        this(label,preVeiwText,tooltip, 0, next, needed);

    }

    public TextInput(String label, String preVeiwText, String tooltip, int width, Focusable next, Boolean needed) {
        this.next = next;
        this.needed = needed;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        if(width != 0){
            textField.setMaxWidth(width);
        }

        this.label.setText(label);
        this.tooltip.setText(tooltip);
        textField.setPromptText(preVeiwText);

        addEventListeners();

        if (isValid()) {
            setSuccess();
        } else {
            setErr();
        }
    }

    private void addEventListeners() {

        //upptäcker om man skrivigt något i rutan
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(isValid()){
                    setSuccess();
                } else if (!isValid()) {
                    setErr();
                }
            }
        });

        //för att man ska kunna trycka enter
        textField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(isValid()) {
                    if(next != null){
                        next.setFocus();
                    }
                }
            }
        });

        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (isValid()) {
                setSuccess();
            } else {
                setErr();
            }
        });
    }

    public void setText(String s) {
        this.textField.setText(s);
    }

    public TextField getTextField(){
        return textField;
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TextInput.fxml"));
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

    public String getInput() throws NotValidInput {
        if(isValid()){
            return textField.getText();
        }else {
            setErr();
            throw new NotValidInput();
        }
    }

    public void setErr(){
        setDefault();
        textField.getStyleClass().add("textBoxErr");
        error.setVisible(true);
        errLabel.setText("Du har inte anget något i ett fällt som det är nödvändigt att fylla i.");
    }

    public void setSuccess(){
        setDefault();
        textField.getStyleClass().add("textBoxSaved");
        error.setVisible(false);
    }

    public void setDefault(){
        textField.getStyleClass().clear();
        textField.getStyleClass().addAll("text-field", "text-input");
    }

    public boolean isValid() {
        if(needed){
            return !textField.getText().equals("");
        }else {
            return true;
        }

    }

}
