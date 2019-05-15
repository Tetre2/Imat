package Model.components.Picker;

import Model.IMat;
import Model.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class Picker extends AnchorPane implements ShoppingCartListener {

    private ShoppingItem shoppingItem;
    @FXML
    private Button minus;
    @FXML
    private Button plus;
    @FXML
    private TextField amount;

    public Picker(ShoppingItem shoppingItem){
        this.shoppingItem = shoppingItem;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);
        addEventListeners();

        updatePickerText();
    }

    private void addEventListeners() {
        plus.setOnAction(e -> onPlusButtonPressed());
        minus.setOnAction(e -> onMinusButtonPressed());

        //upptäcker om man skrivigt något i rutan
        amount.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if(wasFocused){
                int i = (int) shoppingItem.getAmount();
                try {
                    i = Integer.parseInt(amount.getText());
                    shoppingItem.setAmount(i);
                    amount.setPromptText(i+"");
                    amount.setText("");
                    IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
                }catch (Exception e){
                    System.out.println( i + " is not defined");
                    amount.setText("");
                }
            }
        });

        //för att man ska kunna trycka enter
        amount.setOnKeyPressed(ke -> {
            if (ke.getCode().equals(KeyCode.ENTER)) {
                int i = (int) shoppingItem.getAmount();
                try {
                    i = Integer.parseInt(amount.getText());
                    shoppingItem.setAmount(i);
                    IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
                }catch (Exception e){
                    System.out.println( i + " is not defined");
                    amount.setText("");
            }
        }});

    }

    private void onPlusButtonPressed(){
        shoppingItem.setAmount(shoppingItem.getAmount()+1);     //ökar varan med ett
        IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        shopingDebugg();
    }

    private void onMinusButtonPressed(){
        shoppingItem.setAmount(shoppingItem.getAmount()-1);     //minskar varan med ett
        IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        shopingDebugg();
    }

    private void shopingDebugg(){
        System.out.println("Total cost: " + IMat.getInstance().getShoppingCart().getTotal());
        System.out.println("Antal Varor: " + IMat.getInstance().getShoppingCart().getItems().size());
        //System.out.println(Model.IMat.getInstance().getShoppingCart().getItems().get(0).getAmount());
    }


    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Picker.fxml"));
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
    public void shoppingCartChanged(CartEvent cartEvent) {
        updatePickerText();
    }

    private void updatePickerText() {
        amount.setPromptText((int) shoppingItem.getAmount() +"");
    }
}
