package Model.components.Forms.InputItem.DayMonthInputItem;

import Model.IMat;
import Model.components.Forms.CheckValidity;
import Model.components.Forms.Focusalbe;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DayMonthInputItem extends AnchorPane implements Focusalbe, CheckValidity {

    @FXML
    private TextField year;
    @FXML
    private TextField month;

    private Focusalbe next;

    public DayMonthInputItem(Focusalbe next) {
        this.next = next;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();
    }

    private void addEventListeners() {

        //month
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
            }});
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


    @Override
    public void setFocus() {
        month.requestFocus();
    }

    @Override
    public boolean checkValidity() {//TODO
        return false;
    }
}