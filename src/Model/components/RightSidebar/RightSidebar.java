package Model.components.RightSidebar;

import Model.IMat;
import Model.Main;
import Model.components.RightSidebar.RightSidebarItem.RightSidebarItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RightSidebar extends AnchorPane implements ShoppingCartListener {


    @FXML
    private FlowPane inventory;
    @FXML
    private Label amaunt;
    @FXML
    private Button pay;
    @FXML
    private Pane helpPane;
    @FXML
    private Hyperlink link;

    public RightSidebar(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);

        addEventListeners();
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

    private void addEventListeners() {
        pay.setOnAction(e -> onPayPressed());
        link.setOnAction(e -> onLinkPressed());
    }

    private void onLinkPressed(){
        IMat.getInstance().setSceneToHelp();
    }

    private void onPayPressed(){
       IMat.getInstance().setSceneToCheckout();
       //IMat.getInstance().placeOrder();
    }


    private void updateShoppingCart(){
        inventory.getChildren().clear();
        List<ShoppingItem> items = IMat.getInstance().getShoppingCart().getItems();
        for (ShoppingItem si : items) {
            //System.out.println("RightsideBar productName:  " + si.getProduct().getName());
            RightSidebarItem rightSidebarItem = new RightSidebarItem(si);
            inventory.getChildren().add(rightSidebarItem);
        }

        //ta bort hjälp texten
        helpPane.setVisible(items.size() == 0);
        String s = String.format("%.2f", IMat.getInstance().getShoppingCart().getTotal());
        amaunt.setText( s + " kr");
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        updateShoppingCart();
    }

}
