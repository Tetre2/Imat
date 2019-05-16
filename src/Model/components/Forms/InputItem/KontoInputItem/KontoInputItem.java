package Model.components.Forms.InputItem.KontoInputItem;

import Model.components.Forms.CheckValidity;
import Model.components.Forms.Focusalbe;
import Model.components.Forms.InputItem.LimitedTextField.LimitedTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KontoInputItem extends AnchorPane implements CheckValidity {

    @FXML
    private Label label;
    @FXML
    private Label tooltip;
    @FXML
    private FlowPane flowPane;

    private Focusalbe next;
    private List<LimitedTextField> limitedTextFields;

    public KontoInputItem(Focusalbe next) {
        this.next = next;
        limitedTextFields = new ArrayList<>();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        this.label.setText("Kontonummer:");
        this.tooltip.setText("Ange kontokortets kontonummer");

        init();

        for (LimitedTextField l : limitedTextFields) {
            flowPane.getChildren().add(l);
        }
        addEventListeners();
    }

    private void init(){
        LimitedTextField l4 = new LimitedTextField("XXXX", "", "", 4, next);
        LimitedTextField l3 = new LimitedTextField("XXXX", "", "-", 4, l4);
        LimitedTextField l2 = new LimitedTextField("XXXX", "", "-", 4, l3);
        LimitedTextField l1 = new LimitedTextField("XXXX", "", "-", 4, l2);

        limitedTextFields.add(l1);
        limitedTextFields.add(l2);
        limitedTextFields.add(l3);
        limitedTextFields.add(l4);

    }

    private void addEventListeners() {


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

    public List<LimitedTextField> getLimitedTextFields() {
        return limitedTextFields;
    }

    @Override
    public boolean checkValidity() {
        boolean valid = true;
        for (LimitedTextField l : limitedTextFields) {
            if(!l.isValid()){
                valid = false;
            }
        }
        return valid;
    }

    public String getInput(){
        String input = "";
        if (checkValidity()) {
            for (LimitedTextField l : limitedTextFields) {
                input += l.getInput();
            }
        }
        return input;
    }


}
