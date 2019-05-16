package Model.components.Forms.InputItem.KontoInputItem;

import Model.components.Forms.InputItem.InputItem.InputItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class KontoInputItem extends AnchorPane {

    @FXML
    private Label label;
    @FXML
    private Label tooltip;
    @FXML
    private TextField cardNumber1;
    @FXML
    private TextField cardNumber2;
    @FXML
    private TextField cardNumber3;
    @FXML
    private TextField cardNumber4;



    public KontoInputItem(String label, String tooltip) {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        this.label.setText(label);
        this.tooltip.setText(tooltip);

        addEventListeners();
    }

    private void addEventListeners() {
//cardNumber1
        //upptäcker om man skrivigt något i rutan
        cardNumber1.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!cardNumber1.getText().equals("")){
                    System.out.println(cardNumber1.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        cardNumber1.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!cardNumber1.getText().equals("")) {
                    System.out.println(cardNumber1.getText());
                }
            }});

        //cardNumber2
        //upptäcker om man skrivigt något i rutan
        cardNumber2.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!cardNumber2.getText().equals("")){
                    System.out.println(cardNumber2.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        cardNumber2.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!cardNumber2.getText().equals("")) {
                    System.out.println(cardNumber2.getText());
                }
            }});

        //cardNumber3
        //upptäcker om man skrivigt något i rutan
        cardNumber3.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!cardNumber3.getText().equals("")){
                    System.out.println(cardNumber3.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        cardNumber3.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!cardNumber3.getText().equals("")) {
                    System.out.println(cardNumber3.getText());
                }
            }});


        //cardNumber4
        //upptäcker om man skrivigt något i rutan
        cardNumber4.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!cardNumber4.getText().equals("")){
                    System.out.println(cardNumber4.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        cardNumber4.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!cardNumber4.getText().equals("")) {
                    System.out.println(cardNumber4.getText());
                }
            }});
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KontoInputItem.fxml"));
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
