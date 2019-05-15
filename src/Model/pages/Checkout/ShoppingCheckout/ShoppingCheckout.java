package Model.pages.Checkout.ShoppingCheckout;

import Model.IMat;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckoutDetails.ShoppingCheckoutDetails;
import Model.pages.Checkout.VarukorgItem.VarukorgItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class ShoppingCheckout extends AnchorPane {
    @FXML private FlowPane centerContainerFlowPane;

    public ShoppingCheckout() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        List<ShoppingItem> products = IMat.getInstance().getShoppingCart().getItems();
        for (int i = 0; i < products.size(); i++) {
            ShoppingItem s = products.get(i);
            VarukorgItem varukorgItem = new VarukorgItem(s, this);
            centerContainerFlowPane.getChildren().add(varukorgItem);
        }

        centerContainerFlowPane.getChildren().add(new ShoppingCheckoutDetails());
    }

    public void removeShoppingItemFromUI(VarukorgItem item) {
        centerContainerFlowPane.getChildren().remove(item);
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Model/pages/Checkout/ShoppingCheckout/ShoppingCheckout.fxml"));
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