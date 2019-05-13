package Model.components.RightSidebar;

import Model.IMat;
import Model.components.RightSidebar.RightSidebarItem.RightSidebarItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class RightSidebar extends AnchorPane implements ShoppingCartListener {


    @FXML
    private FlowPane inventory;

    public RightSidebar(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);

        updateShoppingCart();


    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RightSidebar.fxml"));
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

    private void updateShoppingCart(){
        inventory.getChildren().clear();
        List<ShoppingItem> items = IMat.getInstance().getShoppingCart().getItems();
        for (ShoppingItem si : items) {
            RightSidebarItem rightSidebarItem = new RightSidebarItem(si);
            inventory.getChildren().add(rightSidebarItem);
        }
    }


    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateShoppingCart();
    }

}