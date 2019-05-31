package Model.components.RightSidebar.RightSidebarItem;

import Model.IMat;
import Model.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import se.chalmers.cse.dat216.project.CartEvent;
import se.chalmers.cse.dat216.project.ShoppingCartListener;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;

public class RightSidebarItem extends AnchorPane implements ShoppingCartListener {

    private ShoppingItem shoppingItem;

    @FXML
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Spinner<Integer> amount;
    @FXML
    private Button close;
    @FXML
    private ImageView closeImage;

    private ComboBox<String> fontFamilyField;

    public RightSidebarItem(ShoppingItem shoppingItem){
        this.shoppingItem = shoppingItem;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, (int)shoppingItem.getAmount());

        amount.setValueFactory(valueFactory);

        addEventListeners();


        String s = String.format("%.2f", shoppingItem.getTotal());
        price.setText(s + " kr");

        name.setText(shoppingItem.getProduct().getName());
        image.setImage(IMat.getInstance().getImage(shoppingItem.getProduct()));

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RightSidebarItem.fxml"));
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


    @FXML
    public void closeButtonMouseEntered(){
        Main.getCurrentScene().setCursor(Cursor.HAND);
        closeImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Model/resources/icon_close_hover.png")));
    }

    @FXML
    public void closeButtonMouseExited(){
        Main.getCurrentScene().setCursor(Cursor.DEFAULT);
        closeImage.setImage(new Image(getClass().getClassLoader().getResourceAsStream(
                "Model/resources/icon_close.png")));
    }


    private void addEventListeners() {
        close.setOnAction(e -> onClosePressed());
        close.setOnMouseEntered(e -> closeButtonMouseEntered());
        close.setOnMouseExited(e -> closeButtonMouseExited());

        amount.valueProperty().addListener((obs, oldValue, newValue) -> setShoppingItemAmount());
    }

    private void setShoppingItemAmount(){
        shoppingItem.setAmount(amount.getValue());
        if(amount.getValue() <= 0){
            removeItem();
        }
        IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }

    private void removeItem(){
        IMat.getInstance().getShoppingCart().removeItem(shoppingItem);
    }

    public void onClosePressed(){
        shoppingItem.setAmount(0);
        IMat.getInstance().getShoppingCart().removeItem(shoppingItem);
        IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {

        //amount.getValueFactory().setValue( (int) shoppingItem.getAmount()); funkar inte
    }
}
