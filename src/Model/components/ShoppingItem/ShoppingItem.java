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

    private boolean isFavorite = false;
    private boolean isAddedToCart = false;

    public ShoppingItem(Product p){
        this();

        name.setText(p.getName());
        price.setText(p.getPrice() + "");
        unit.setText(p.getUnit());
        image.setImage(IMat.getInstance().getImage(p));
    }


    private ShoppingItem(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        //setFields();
        //addEventListeners();
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
        rootPane.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            // TODO: only show star on hover. Kinda optional.
        });
    }

    private void onAddToCartButtonPressed() {
        isAddedToCart = true;
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        // TODO: add backend logic
        // TODO: ge visuell feedback att den lagts till
    }

    @FXML
    private void onStarButtonPressed() {
        updateStarButtonUI();
        isFavorite = !isFavorite;
        System.out.println("kjgldfk");
        // TODO: add favorite logic
    }

    private void updateStarButtonUI() {
        String iconPathFavorite = "Model/resources/star-checked.png";
        String iconPathNoFavorite = "Model/resources/star-unchecked.png";
        Image icon;

        if (isFavorite) {
            icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPathNoFavorite));
        } else {
            icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPathFavorite));
        }

        starImageview.setImage(icon);
    }


}