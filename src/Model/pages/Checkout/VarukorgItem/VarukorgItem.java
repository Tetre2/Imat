package Model.pages.Checkout.VarukorgItem;

import Model.IMat;
import Model.Main;
import Model.components.Picker.Picker;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckout;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
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
    @FXML private ImageView deleteImageView;
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
        updatePriceLabels();

        productImageView.setImage(IMat.getInstance().getImage(p));
        //rundade hörn på bilden
        Rectangle clip = new Rectangle(
                productImageView.getFitWidth(), productImageView.getFitHeight()-5
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        productImageView.setClip(clip);


        pickerPane.getChildren().add(new Picker(shoppingItem));

        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);
        deleteButton.setOnAction(e -> onCloseButtonPressed());
        deleteButton.setOnMouseEntered(e -> closeButtonMouseEntered());
        deleteButton.setOnMousePressed(e -> closeButtonMousePressed());
        deleteButton.setOnMouseExited(e -> closeButtonMouseExited());
        System.out.println("NYTT ITEM");
    }

    private void updatePriceLabels() {
        Product p = shoppingItem.getProduct();

        String s = String.format("%.2f", p.getPrice());
        priceLabel.setText(s + " kr");
        s = String.format("%.2f", p.getPrice() * shoppingItem.getAmount());
        totalPriceLabel.setText(s + " kr");
    }

    private void onCloseButtonPressed() {
        shoppingItem.setAmount(0);
        IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        removeItemFromUI();
    }

    private void removeItemFromUI() {
        shoppingCheckout.removeShoppingItemFromUI(this);
    }

    @FXML
    public void closeButtonMouseEntered(){
        Main.getCurrentScene().setCursor(Cursor.HAND);
        deleteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Model/resources/icon_close_hover.png")));
    }

    @FXML
    public void closeButtonMousePressed(){
        deleteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Model/resources/icon_close_pressed.png")));
    }

    @FXML
    public void closeButtonMouseExited(){
        Main.getCurrentScene().setCursor(Cursor.DEFAULT);
        deleteImageView.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Model/resources/icon_close.png")));
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
        if (itemIsDeleted()) {
            removeItemFromUI();
        } else {
            updatePriceLabels();
        }
    }

    private boolean itemIsDeleted() {
        return shoppingItem.getAmount() == 0;
    }
}
