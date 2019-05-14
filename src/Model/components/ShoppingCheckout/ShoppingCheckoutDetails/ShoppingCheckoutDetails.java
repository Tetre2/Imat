package Model.components.ShoppingCheckout.ShoppingCheckoutDetails;

import Model.IMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ShoppingCheckoutDetails extends AnchorPane {
    @FXML private Label foodPriceLabel;
    @FXML private Label transportPriceLabel;
    @FXML private Label totalPriceLabel;

    public ShoppingCheckoutDetails() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        Double foodPrice = IMat.getInstance().getShoppingCart().getTotal();
        transportPriceLabel.setText("50.0");
        foodPriceLabel.setText(Double.toString(foodPrice));
        totalPriceLabel.setText(Double.toString(foodPrice + 50.0));
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Model/components/ShoppingCheckout/ShoppingCheckoutDetails/ShoppingCheckoutDetails.fxml"));
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
