package Model.components.Forms.InputItem.MonthYearInputItem;

import Model.components.Forms.InputItem.isInputItem;
import Model.components.Forms.NotValidInput;
import Model.components.Forms.ValidityCheckable;
import Model.components.Forms.Focusable;
import Model.components.Forms.InputItem.LimitedTextField.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MonthYearInputItem extends AnchorPane implements ValidityCheckable, isInputItem {

    @FXML
    private FlowPane flowPane;

    private Focusable next;
    private List<LimitedTextField> limitedTextFields;

    public MonthYearInputItem(Focusable next) {
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

        LimitedTextField year = new LimitedTextField("År", "År", "", 2, next, this);
        LimitedTextField month = new LimitedTextField("Månad", "Månad", "/", 2, year, this);

        limitedTextFields.add(month);
        limitedTextFields.add(year);
    }

    private void addEventListeners() {

    }

    public void setMonth(String month){
        limitedTextFields.get(0).setTextFieldText(month);
    }

    public void setYear(String year){
        limitedTextFields.get(1).setTextFieldText(year);
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

    public List<LimitedTextField> getLimitedTextFields() {
        return limitedTextFields;
    }

    @Override
    public boolean checkValidity(String text) {
        try {
            if(Integer.parseInt(text) <= 31 && Integer.parseInt(text) > 0){
                return true;
            }
        }catch (Exception e){
        }
        return false;
    }

    @Override
    public String getInput() throws NotValidInput {
        String s = "";
        if(isValide()){
            for (LimitedTextField l : limitedTextFields) {
                s += l.getInput() + " ";
            }
        }
        return s;
    }

    @Override
    public boolean isValide() {
        boolean valid = true;
        for (LimitedTextField l : limitedTextFields) {
            if(!l.isValid()){
                valid = false;
            }
        }
        return valid;
    }
}