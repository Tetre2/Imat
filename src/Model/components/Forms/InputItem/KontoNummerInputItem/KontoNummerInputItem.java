package Model.components.Forms.InputItem.KontoNummerInputItem;

import Model.components.Forms.InputItem.isInputItem;
import Model.components.Forms.NotValidInput;
import Model.components.Forms.ValidityCheckable;
import Model.components.Forms.Focusable;
import Model.components.Forms.InputItem.LimitedTextField.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KontoNummerInputItem extends AnchorPane implements ValidityCheckable, isInputItem {

    @FXML
    private Label label;
    @FXML
    private Label tooltip;
    @FXML
    private FlowPane flowPane;

    private Focusable next;
    private List<LimitedTextField> limitedTextFields;

    public KontoNummerInputItem(Focusable next) {
        this.next = next;
        limitedTextFields = new ArrayList<>();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        this.label.setText("Kontonummer:");
        this.tooltip.setText("Ange kontokortets kontonummer *");

        init();

        for (LimitedTextField l : limitedTextFields) {
            flowPane.getChildren().add(l);
        }
        addEventListeners();
    }

    private void init(){
        LimitedTextField l4 = new LimitedTextField("XXXX", "", "", 4, next, this);
        LimitedTextField l3 = new LimitedTextField("XXXX", "", "-", 4, l4, this);
        LimitedTextField l2 = new LimitedTextField("XXXX", "", "-", 4, l3, this);
        LimitedTextField l1 = new LimitedTextField("XXXX", "", "-", 4, l2, this);

        limitedTextFields.add(l1);
        limitedTextFields.add(l2);
        limitedTextFields.add(l3);
        limitedTextFields.add(l4);

    }

    private void addEventListeners() {


    }

    public void setCardNumber(String cardNumber){
        String[] arr = {"", "", "", ""};
        if(!cardNumber.equals("")){
            int start = 0;
            for (int i = 1; i <= 4; i++) {
                try {
                    arr[i-1] = cardNumber.substring(start, i*4);
                }catch (Exception e){
                    arr[i-1] = "";
                }
                start = i*4;
            }
        }

        for (int i = 0; i < limitedTextFields.size(); i++) {
            limitedTextFields.get(i).setTextFieldText(arr[i]);
        }

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KontoNummerInputItem.fxml"));
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
    public String getInput() throws NotValidInput {
        String input = "";
        if (isValide()) {
            for (LimitedTextField l : limitedTextFields) {
                input += l.getInput();
            }
        }
        if(input.length() == 16){
            return input;
        }else {
            throw new NotValidInput();
        }

    }

    public void setSaved(){
        for (LimitedTextField l : limitedTextFields) {
            l.setSaved();
        }
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


    @Override
    public boolean checkValidity(String text) {
        try {
            Integer.parseInt(text);
            return true;
        }catch (Exception e){
        }
        return false;
    }
}
