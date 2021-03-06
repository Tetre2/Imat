package Model.components.Forms.InputItem.MonthYearInputItem;

import Model.components.Forms.NotValidInput;
import Model.components.Forms.Focusable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MonthYearInputItem extends AnchorPane implements Focusable {

    @FXML
    private TextField month;
    @FXML
    private TextField year;
    @FXML
    private AnchorPane error;
    @FXML
    private Label errLabel;

    private Focusable next;

    public MonthYearInputItem(Focusable next) {
        this.next = next;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        errLabel.getStyleClass().add("text-md");

        addEventListeners();

        error.setVisible(false);
    }


    private void addEventListeners() {
        //upptäcker om man skrivigt något i rutan
        month.textProperty().addListener((observable, oldValue, newValue) -> {
            if (month.getText().length() == 2) {
                if (isMonthValide()) {
                    clearErr(month);
                    year.requestFocus();
                }
            }
            if (month.getText().length() > 2) {
                month.setText(month.getText().substring(0, 2));
            }
        });

        month.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                clearErr(month);
            }
            if (wasFocused) {
                isMonthValide();
            }
        });
        //för att man ska kunna trycka enter
        month.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                if(!month.getText().equals("")) {
                    year.requestFocus();
                }
            }});


        //upptäcker om man skrivigt något i rutan
        year.textProperty().addListener((observable, oldValue, newValue) -> {
            if (year.getText().length() == 4) {
                if (isYearValide()) {
                    clearErr(year);
                    next.setFocus();
                }
            }
            if (year.getText().length() > 4) {
                year.setText(year.getText().substring(0, 4));
            }
        });

        year.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                clearErr(year);
            }
            if (wasFocused) {
                isYearValide();
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

    public void setSaved(){
        month.getStyleClass().add("textBoxSaved");
        year.getStyleClass().add("textBoxSaved");
    }


    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MonthYearInputItem.fxml"));
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

    private void clearErr(TextField textField) {
        textField.getStyleClass().clear();
        textField.getStyleClass().addAll("text-field", "text-input");
        error.setVisible(false);
    }

    public void setDefaults(){
        month.getStyleClass().clear();
        month.getStyleClass().addAll("text-field", "text-input");
        year.getStyleClass().clear();
        year.getStyleClass().addAll("text-field", "text-input");
    }

    private void showSeucsses(TextField textField){
        clearErr(textField);
        textField.getStyleClass().add("textBoxSaved");
    }

    private void showErr(TextField textField) {
        textField.getStyleClass().add("textBoxErr");
        error.setVisible(true);

        if(textField.getId().equals("month")){
            errLabel.setText(textField.getText() + " Är inte en giltlig Månad");
        }else if(textField.getId().equals("year")){
            errLabel.setText(textField.getText() + " Är inte ett giltlig år");
        }

    }

    public void setMonth(String month){
        this.month.setText(month);
    }

    public void setYear(String year){
        this.year.setText(year);
    }

    public int getMonth() throws NotValidInput{
        if(isMonthValide()){
            return Integer.parseInt(month.getText());
        }else{
            throw new NotValidInput();
        }
    }

    public int getYear() throws NotValidInput{
        if(isYearValide()){
            return Integer.parseInt(year.getText());
        }else{
            throw new NotValidInput();
        }
    }

    public boolean isMonthValide() {
        int month = 0;
        try {
            month = Integer.parseInt(this.month.getText());
        }catch (Exception e){
            showErr(this.month);
            return false;
        }
        if(month < 13 && month > 0) {
            clearErr(this.month);
            showSeucsses(this.month);
            return true;
        }
        showErr(this.month);
        return false;
    }

    public boolean isYearValide() {
        int year = 0;
        try {
            year = Integer.parseInt(this.year.getText());
        }catch (Exception e){
            showErr(this.year);
            return false;
        }
        if(year > 2018 && year < 2026){
            showSeucsses(this.year);
            return true;
        }else if(year > 18 && year < 26){
            showSeucsses(this.year);
            return true;
        }
        showErr(this.year);
        return false;
    }

    public boolean isValid() {
        return isYearValide() && isMonthValide();
    }

    @Override
    public void setFocus() {
        month.requestFocus();
    }
}