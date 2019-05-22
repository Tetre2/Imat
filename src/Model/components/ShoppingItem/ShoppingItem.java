package Model.components.ShoppingItem;

import Model.IMat;
import Model.Main;
import Model.components.LeftSidebar.LeftSidebarCategory.CategoryListener;
import Model.components.LeftSidebar.LeftSidebarCategory.MainCategory;
import Model.components.Picker.Picker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingCartListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingItem extends AnchorPane implements ShoppingCartListener {
    @FXML
    AnchorPane rootPane;
    @FXML
    ImageView image;
    @FXML
    Label name;
    @FXML
    Label price;
    @FXML
    Label unit;
    @FXML
    Label comparePrice;
    @FXML
    Label comparePriceUnit;
    @FXML
    Button addToCartButton;
    @FXML
    ImageView starImageview;
    @FXML
    Button starButton;

    @FXML
    AnchorPane pickerPane;

    private List<ShoppingItemListener> listeners = new ArrayList<>();

    private Product product;
    private se.chalmers.cse.dat216.project.ShoppingItem item;
    private Picker picker;

    public ShoppingItem(Product p) {
        this.product = p;
        item = IMat.getInstance().getShoppingCartItem(product);
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
        unit.setText(getUnit());
        image.setImage(IMat.getInstance().getImage(product));

        //Jömmer det som inte ska synas från början
        starButton.setVisible(false);
        hidePlusMinus();

        //sätter rätt stjärna
        updateStarButtonUI();
        if (IMat.getInstance().shoppingCartContainsProduct(p)) {
            toggleItemIsSelected();
        }

    }

    private String getUnit(){
        String unit = product.getUnit();
        if(unit.length() > 6){
            unit = "kr/st";
        }
        return unit;
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
            if (!isFavorited()) {
                starButton.setVisible(false);
            }

        });

    }

    private void toggleItemIsSelected() {
        rootPane.getStyleClass().addAll("selected", "active-state-border");
        addToCartButton.setVisible(false);
        showPlusMinus();
    }

    private void onAddToCartButtonPressed() {
        //visar att varan är lagd i varukorgen
        toggleItemIsSelected();
        // rootPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(12), Insets.EMPTY)));

        //lägger en utav varan i varukorgen
        item.setAmount(1);
        IMat.getInstance().getShoppingCart().addItem(item);

        showPlusMinus();
    }

    private void showPlusMinus() {
        pickerPane.setVisible(true);
    }

    private void hidePlusMinus() {
        item.setAmount(0);
        pickerPane.setVisible(false);
        rootPane.getStyleClass().clear();
        rootPane.getStyleClass().add("anchor-container");
        addToCartButton.setVisible(true);
    }

    private void onStarButtonPressed() {
        if (isFavorited()) {
            //Om stjärnan blir klickad när den är en favorit tas den bort
            IMat.getInstance().getFavorites().remove(product);
            System.out.println("Removed " + product.getName() + " From Favorites");
            fireRemoveShoppingItem();
        } else {
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
            starButton.setVisible(true);
        } else {
            icon = new Image(getClass().getClassLoader().getResourceAsStream(iconPathNoFavorite));
        }

        starImageview.setImage(icon);
    }

    public void fireRemoveShoppingItem() {
        Iterator var4 = this.listeners.iterator();
        while (var4.hasNext()) {
            ShoppingItemListener ssl = (ShoppingItemListener) var4.next();
            ssl.shoppingItemClicked(this);
        }
    }

    private boolean isFavorited() {
        return IMat.getInstance().favoritesContainsProduct(product);
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {

        if (cartEvent.isAddEvent()) {

            if (cartEvent.getShoppingItem().equals(item)) {
                System.out.println("ShoppingItem, HEJ " + cartEvent.getShoppingItem().getProduct().getName());
                if (!IMat.getInstance().getShoppingCart().getItems().contains(item)) {
                    hidePlusMinus();
                }else {
                    showPlusMinus();
                }

            }
            if(item.getAmount() == 0 && pickerPane.isVisible()){
                hidePlusMinus();

                if(IMat.getInstance().getShoppingCartItems().contains(item)){
                    IMat.getInstance().getShoppingCart().removeItem(item);
                }
            }
        }


    }
    public void addShoppingItemListener(ShoppingItemListener sil) {
        this.listeners.add(sil);
    }


    public se.chalmers.cse.dat216.project.ShoppingItem getShoppingItem() {
        return item;
    }

    public Product getProduct() {
        return product;
    }
}