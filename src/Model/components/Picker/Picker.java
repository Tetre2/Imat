package Model.components.Picker;

import Model.IMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class Picker extends AnchorPane {

    private ShoppingItem shoppingItem;
    @FXML
    private Button minus;
    @FXML
    private Button plus;

    public Picker(ShoppingItem shoppingItem){
        this.shoppingItem = shoppingItem;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

    }

    private void addEventListeners() {
        plus.setOnAction(e -> onPlusButtonPressed());
        minus.setOnAction(e -> onMinusButtonPressed());

    }


    //TODO Lägg i picker
    private void onPlusButtonPressed(){
        shoppingItem.setAmount(shoppingItem.getAmount()+1);
        IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        shopingDebugg();
    }

    //TODO Lägg i picker
    private void onMinusButtonPressed(){
        shoppingItem.setAmount(shoppingItem.getAmount()-1);
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


}
