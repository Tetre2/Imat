package Model.components.RightSidebar.RightSidebarItem;

import Model.IMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class RightSidebarItem extends AnchorPane implements ShoppingCartListener {

    private ShoppingItem shoppingItem;

    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML//kanske sätta till double
    private Spinner<Integer> amount;
    @FXML
    private Button close;


    public RightSidebarItem(ShoppingItem shoppingItem){
        this.shoppingItem = shoppingItem;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);


        addEventListeners();

        name.setText(shoppingItem.getProduct().getName());
        image.setImage(IMat.getInstance().getImage(shoppingItem.getProduct()));

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RightSidebarItem.fxml"));
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


    private void addEventListeners() {
        close.setOnAction(e -> onClosePressed());
    }

    public void onClosePressed(){
        IMat.getInstance().getShoppingCart().removeItem(shoppingItem);
    }


    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        //amount.getValueFactory().setValue( (int) shoppingItem.getAmount()); funkar inte
    }
}
