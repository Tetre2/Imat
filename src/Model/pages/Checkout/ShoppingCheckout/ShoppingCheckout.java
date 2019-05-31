package Model.pages.Checkout.ShoppingCheckout;

import Model.HelperClasses.UpdateGoToPaymentButtonObservable;
import Model.IMat;
import Model.pages.Checkout.ShoppingCartIsEmpty.ShoppingCartIsEmpty;
import Model.pages.Checkout.VarukorgItem.VarukorgItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class ShoppingCheckout extends AnchorPane implements ShoppingCartListener {
    @FXML private FlowPane centerContainerFlowPane;

    private UpdateGoToPaymentButtonObservable parent;

    public ShoppingCheckout(UpdateGoToPaymentButtonObservable parent) {
        this.parent = parent;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        List<ShoppingItem> products = IMat.getInstance().getShoppingCart().getItems();
        for (int i = 0; i < products.size(); i++) {
            ShoppingItem s = products.get(i);
            VarukorgItem varukorgItem = new VarukorgItem(s, this);
            centerContainerFlowPane.getChildren().add(varukorgItem);
        }

        if(products.size() <= 0){
            parent.setGoToPaymentButtonDisable(true);
        }else {
            parent.setGoToPaymentButtonDisable(false);
        }

        //centerContainerFlowPane.getChildren().add(new ShoppingCheckoutDetails());

        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);
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

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        if (IMat.getInstance().isShoppingCartEmpty()) {
            centerContainerFlowPane.getChildren().clear();
            centerContainerFlowPane.getChildren().add(new ShoppingCartIsEmpty());
            parent.setGoToPaymentButtonDisable(true);
        }
    }
}
