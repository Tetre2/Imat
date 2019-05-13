package Model.components.ShoppingItem;

import Model.IMat;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;

public class ShoppingItem extends AnchorPane {
    @FXML AnchorPane rootPane;
    @FXML ImageView image;
    @FXML Label name;
    @FXML Label price;
    @FXML Label unit;
    @FXML Label comparePrice;
    @FXML Label comparePriceUnit;
    @FXML Button addToCartButton;
    @FXML ImageView starImageview;
    @FXML Button starButton;

    @FXML Button minusButton;
    @FXML Button plusButton;
    @FXML AnchorPane picker;

    private boolean isFavorite = false;
    private boolean isAddedToCart = false;
    private Product product;

    public ShoppingItem(Product p){
        this.product = p;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        //setFields();
        addEventListeners();

        name.setText(product.getName());
        price.setText(product.getPrice() + "");
        unit.setText(product.getUnit());
        image.setImage(IMat.getInstance().getImage(product));

        starButton.setVisible(false);
        picker.setVisible(false);

        if(isFavorited()){
            isFavorite = true;
        }
        updateStarButtonUI();

        //TODO Se till att "addedtoCart" funktionen värkligen funkar som det är tänkt
        if(addedToCart()){
            isAddedToCart = true;
        }


    }



    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Model/components/ShoppingItem/shoppingItem.fxml"));
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

    private void setFields() {
    }

    private void addEventListeners() {
        starButton.setOnAction(e -> onStarButtonPressed());
        addToCartButton.setOnAction(e -> onAddToCartButtonPressed());
        plusButton.setOnAction(e -> onPlusButtonPressed());
        minusButton.setOnAction(e -> onMinusButtonPressed());

        rootPane.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            starButton.setVisible(true);
        });
        rootPane.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            starButton.setVisible(false);
        });

    }

    private void onAddToCartButtonPressed() {
        isAddedToCart = true;
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // TODO: add backend logic
        IMat.getInstance().getShoppingCart().addProduct(product);
        System.out.println("Total cost: " + IMat.getInstance().getShoppingCart().getTotal());
        System.out.println("Antal Varor: " + IMat.getInstance().getShoppingCart().getItems().size());

        // TODO: ge visuell feedback att den lagts till
        showPlusMinus();
    }


    private void showPlusMinus(){
        picker.setVisible(true);
    }

    private void onPlusButtonPressed(){

    }

    private void onMinusButtonPressed(){

    }


    private void onStarButtonPressed() {

        if(isFavorite){
            IMat.getInstance().getFavorites().remove(product);
            System.out.println("Removed " + product.getName() + " From Favorites");
            isFavorite = false;
        }else {
            IMat.getInstance().addFavorite(product);
            System.out.println("Added " + product.getName() + " To Favorites");
            isFavorite = true;
        }

        updateStarButtonUI();
    }

    private void updateStarButtonUI() {
        String iconPathFavorite = "Model/resources/star-checked.png";
        String iconPathNoFavorite = "Model/resources/star-unchecked.png";
        Image icon;

        if (isFavorite) {
            icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPathFavorite));
        } else {
            icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPathNoFavorite));
        }

        starImageview.setImage(icon);
    }

    private boolean isFavorited(){
        return IMat.getInstance().getFavorites().contains(product);
    }

    private boolean addedToCart(){
        return IMat.getInstance().getShoppingCart().getItems().contains(product);
    }

}