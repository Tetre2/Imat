package Model.components.Forms.InputItem;

import Model.IMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class InputItem extends AnchorPane {


    @FXML
    private TextField textField;
    @FXML
    private Label label;

    public InputItem(String label, String preVeiwText) {

        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        this.label.setText(label);
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
                }
            }});
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("InputItem.fxml"));
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
