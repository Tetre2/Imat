package Model.pages.Checkout.ShoppingCheckout.ShoppingCheckoutDetails;

import Model.IMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;

public class ShoppingCheckoutDetails extends AnchorPane implements ShoppingCartListener {
    private static final Double TRANSPORT_PRICE = 50.0;

    @FXML private Label foodPriceLabel;
    @FXML private Label transportPriceLabel;
    @FXML private Label totalPriceLabel;

    public ShoppingCheckoutDetails() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        updateLabels();

        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);
    }

    private void updateLabels() {
        Double foodPrice = IMat.getInstance().getShoppingCart().getTotal();
        transportPriceLabel.setText(Double.toString(TRANSPORT_PRICE));
        String price = String.format("%.2f", foodPrice);
        foodPriceLabel.setText(price);
        System.out.println("Ceckout, TotalPrice: " + price);

        price = String.format("%.2f", foodPrice + TRANSPORT_PRICE);
        totalPriceLabel.setText(price);
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Model/pages/Checkout/ShoppingCheckout/ShoppingCheckoutDetails/ShoppingCheckoutDetails.fxml"));
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
        updateLabels();
    }
}
