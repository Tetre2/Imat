package Model.pages.Checkout.VarukorgItem;

import Model.IMat;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class VarukorgItem extends AnchorPane {
    @FXML private Label productNameLabel;
    @FXML private Label priceLabel;
    @FXML private Label totalPriceLabel;
    @FXML private ImageView productImageView;
    @FXML private Button deleteButton;

    private ShoppingCheckout shoppingCheckout;
    private ShoppingItem shoppingItem;

    public VarukorgItem(ShoppingItem shoppingItem, ShoppingCheckout shoppingCheckout) {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        this.shoppingItem = shoppingItem;
        this.shoppingCheckout = shoppingCheckout;

        Product p = shoppingItem.getProduct();
        productNameLabel.setText(p.getName());
        priceLabel.setText(Double.toString(p.getPrice()));
        productImageView.setImage(IMat.getInstance().getImage(p));

        deleteButton.setOnAction(e -> onCloseButtonPressed());
    }

    private void onCloseButtonPressed() {
        IMat.getInstance().getShoppingCart().removeItem(shoppingItem);
        shoppingCheckout.removeShoppingItemFromUI(this);
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Model/pages/Checkout/VarukorgItem/VarukorgItem.fxml"));
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
