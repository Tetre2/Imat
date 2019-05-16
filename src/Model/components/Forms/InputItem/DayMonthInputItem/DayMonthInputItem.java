package Model.components.Forms.InputItem.DayMonthInputItem;

import Model.IMat;
import Model.components.Forms.CheckValidity;
import Model.components.Forms.Focusalbe;
import Model.components.Forms.InputItem.LimitedTextField.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DayMonthInputItem extends AnchorPane implements CheckValidity {

    @FXML
    private FlowPane flowPane;

    private Focusalbe next;
    private List<LimitedTextField> limitedTextFields;

    public DayMonthInputItem(Focusalbe next) {
        this.next = next;
        limitedTextFields = new ArrayList<>();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        init();

        for (LimitedTextField l : limitedTextFields) {
            flowPane.getChildren().add(l);
        }

        addEventListeners();
    }

    private void init(){

        LimitedTextField l2 = new LimitedTextField("År", "År", "", 2, next);
        LimitedTextField l1 = new LimitedTextField("Månad", "Månad", "/", 2, l2);

        limitedTextFields.add(l1);
        limitedTextFields.add(l2);
    }

    private void addEventListeners() {

        /*//month
        //upptäcker om man skrivigt något i rutan
        month.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!month.getText().equals("")){
                    try {
                        int i = Integer.parseInt(month.getText());
                        IMat.getInstance().getCreditCard().setValidMonth(i);
                    }catch (Exception e){
                        System.out.println("NOT VALID MONTH");
                    }
                }
            }
        });

        //för att man ska kunna trycka enter
        month.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!month.getText().equals("")) {
                    year.requestFocus();
                }
            }});

        //year
        //upptäcker om man skrivigt något i rutan
        year.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                if(!year.getText().equals("")){try {
                    int i = Integer.parseInt(year.getText());
                    IMat.getInstance().getCreditCard().setValidYear(i);
                }catch (Exception e){
                    System.out.println("NOT VALID YEAR");
                }
                    next.setFocus();
                }
            }
        });

        //för att man ska kunna trycka enter
        year.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!year.getText().equals("")) {
                    if(next != null){
                        next.setFocus();
                    }
                }
            }});*/
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DayMonthInputItem.fxml"));
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

    public List<LimitedTextField> getLimitedTextFields() {
        return limitedTextFields;
    }

    @Override
    public boolean checkValidity() {//TODO
        return false;
    }
}