package Model.components.Forms.Kontouppgifter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class KontoUppgifter extends AnchorPane {

    @FXML
    private TextField owner;
    @FXML
    private TextField month;
    @FXML
    private TextField day;
    @FXML
    private TextField cardNumber1;
    @FXML
    private TextField cardNumber2;
    @FXML
    private TextField cardNumber3;
    @FXML
    private TextField cardNumber4;
    @FXML
    private TextField verification;
    @FXML
    private Button save;


    public KontoUppgifter() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

    }

    private void addEventListeners() {

        //Owner events
        //upptäcker om man skrivigt något i rutan
        owner.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!owner.getText().equals("")){
                    System.out.println(owner.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        owner.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!owner.getText().equals("")) {
                    System.out.println(owner.getText());
                }
            }});

        //Month events
        //upptäcker om man skrivigt något i rutan
        month.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!month.getText().equals("")){
                    System.out.println(month.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        month.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!month.getText().equals("")) {
                    System.out.println(month.getText());
                }
            }});

        //day events
        //upptäcker om man skrivigt något i rutan
        day.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!day.getText().equals("")){
                    System.out.println(day.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        day.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!day.getText().equals("")) {
                    System.out.println(day.getText());
                }
            }});
        
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

        //verification
        //upptäcker om man skrivigt något i rutan
        verification.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!verification.getText().equals("")){
                    System.out.println(verification.getText());
                }
            }
        });

        //för att man ska kunna trycka enter
        verification.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!verification.getText().equals("")) {
                    System.out.println(verification.getText());
                }
            }});
        

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
