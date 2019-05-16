package Model.components.Forms.InputItem.InputItem;

import Model.components.Forms.Focusable;
import Model.components.Forms.InputItem.isInputItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class TextInput extends AnchorPane implements Focusable, isInputItem {


    @FXML
    private TextField textField;
    @FXML
    private Label label;
    @FXML
    private Label tooltip;

    private Focusable next;

    public TextInput(String label, String preVeiwText, String tooltip, Focusable next) {
        this(label,preVeiwText,tooltip, 0, next);

    }

    public TextInput(String label, String preVeiwText, String tooltip, int width, Focusable next) {
        this.next = next;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        if(width != 0){
            textField.setMaxWidth(width);
        }

        this.label.setText(label);
        this.tooltip.setText(tooltip);
        textField.setPromptText(preVeiwText);

        addEventListeners();
    }

    private void addEventListeners() {

        //upptäcker om man skrivigt något i rutan
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!textField.getText().equals("")){
                    System.out.println(textField.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        textField.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!textField.getText().equals("")) {
                    System.out.println(textField.getText());
                    if(next != null){
                        next.setFocus();
                    }
                }
            }});
    }

    public void setText(String s) {
        this.textField.setText(s);
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

    @Override
    public String getInput() {
        return textField.getText();
    }

    @Override
    public boolean isValide() {
        return true;
    }
}
