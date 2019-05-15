package Model.pages.Checkout.VarukorgItem;

import Model.IMat;
import Model.components.Picker.Picker;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class VarukorgItem extends AnchorPane implements ShoppingCartListener {
    @FXML private Label productNameLabel;
    @FXML private Label priceLabel;
    @FXML private Label totalPriceLabel;
    @FXML private ImageView productImageView;
    @FXML private Button deleteButton;
    @FXML private Pane pickerPane;

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
        totalPriceLabel.setText(Double.toString(p.getPrice() * shoppingItem.getAmount()));
        productImageView.setImage(IMat.getInstance().getImage(p));
        pickerPane.getChildren().add(new Picker(shoppingItem));

        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);
        deleteButton.setOnAction(e -> onCloseButtonPressed());
    }

    private void onCloseButtonPressed() {
        shoppingItem.setAmount(0);
        IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
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

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        totalPriceLabel.setText(Double.toString(shoppingItem.getProduct().getPrice() * shoppingItem.getAmount()));
    }
}
