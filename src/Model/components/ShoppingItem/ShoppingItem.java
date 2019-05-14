package Model.components.ShoppingItem;

import Model.IMat;
import Model.components.Picker.Picker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;

public class ShoppingItem extends AnchorPane implements ShoppingCartListener {
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

    @FXML AnchorPane pickerPane;

    private Product product;
    private se.chalmers.cse.dat216.project.ShoppingItem item;
    private Picker picker;

    public ShoppingItem(Product p){
        this.product = p;
        item = new se.chalmers.cse.dat216.project.ShoppingItem(p);
        picker = new Picker(item);

        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        //Lägg grafisk kod under det här!

        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);

        //Lägger till picker så att den går att visas
        pickerPane.getChildren().add(picker);

        addEventListeners();

        //sätter produktens värden
        name.setText(product.getName());
        price.setText(product.getPrice() + "");
        unit.setText(product.getUnit());
        image.setImage(IMat.getInstance().getImage(product));

        //Jömmer det som inte ska synas från början
        starButton.setVisible(false);
        hidePlusMinus();

        //sätter rätt stjärna
        updateStarButtonUI();

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

    private void addEventListeners() {
        starButton.setOnAction(e -> onStarButtonPressed());
        addToCartButton.setOnAction(e -> onAddToCartButtonPressed());

        rootPane.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            starButton.setVisible(true);
        });
        rootPane.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            if(!isFavorited()){
                starButton.setVisible(false);
            }

        });

    }

    private void onAddToCartButtonPressed() {
        //visar att varan är lagd i varukorgen
        rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        addToShoppingCart();

        //debugging ändast
        shopingDebugg();

        showPlusMinus();
    }

    private void showPlusMinus(){
        pickerPane.setVisible(true);
    }

    private void hidePlusMinus(){
        pickerPane.setVisible(false);
    }

    private void shopingDebugg(){
        System.out.println("Total cost: " + IMat.getInstance().getShoppingCart().getTotal());
        System.out.println("Antal Varor: " + IMat.getInstance().getShoppingCart().getItems().size());
        //System.out.println(Model.IMat.getInstance().getShoppingCart().getItems().get(0).getAmount());
    }

    private void onStarButtonPressed() {
        if(isFavorited()){
            //Om stjärnan blir klickad när den är en favorit tas den bort
            IMat.getInstance().getFavorites().remove(product);
            System.out.println("Removed " + product.getName() + " From Favorites");
        }else {
            //Om stjärnan blir klickad när den inte är en favorit läggs den till
            IMat.getInstance().addFavorite(product);
            System.out.println("Added " + product.getName() + " To Favorites");
        }

        updateStarButtonUI();
    }

    private void updateStarButtonUI() {
        String iconPathFavorite = "Model/resources/star-checked.png";
        String iconPathNoFavorite = "Model/resources/star-unchecked.png";
        Image icon;

        if (isFavorited()) {
            icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPathFavorite));
        } else {
            icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPathNoFavorite));
        }

        starImageview.setImage(icon);
    }

    private boolean isFavorited(){
        return IMat.getInstance().getFavorites().contains(product);
    }

    private void addToShoppingCart(){
        //lägger en utav varan i varukorgen
        item.setAmount(1);
        IMat.getInstance().getShoppingCart().addItem(item);
    }

    private void removeFromShoppingCart(){
        //TODO: sätta rätt färg här
        rootPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        IMat.getInstance().getShoppingCart().removeItem(item);
        hidePlusMinus();
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {

        if(item.getAmount() == 0 && pickerPane.isVisible()){ //sker när man klickar på krysset från varukorgen
            pickerPane.setVisible(false);
            if(IMat.getInstance().getShoppingCartItems().contains(item)){
                removeFromShoppingCart();
            }
        }

    }
}