package Model.pages.Historik.Kvitto;

import Model.IMat;
import Model.Main;
import Model.components.LeftSidebar.LeftSidebar;
import Model.components.Navbar.Navbar;
import Model.pages.Historik.Historik;
import Model.pages.Historik.HistorikItem.HistorikItem;
import Model.pages.Mainpage.MainPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Kvitto extends AnchorPane {

    @FXML
    private Label date;
    @FXML
    private Label price;
    @FXML
    private Button addToCartButton;
    @FXML
    private GridPane grid;
    @FXML
    private Button close;

    private ArrayList<ProductCategory> categories;
    private Historik parent;
    private Order order;

    public Kvitto(Order order, Historik parent) {
        categories = new ArrayList();
        this.parent = parent;
        this.order = order;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();


        date.setText(parent.getDate(order.getDate()));

        double calcPrice = 0;

        for (ShoppingItem shoppingItem : order.getItems()) {
            calcPrice += shoppingItem.getTotal();//ger totala kostnaden

            if(!categories.contains(shoppingItem.getProduct().getCategory())){//om categorin inte finns i listan läggs den till
                System.out.println("added category");
                categories.add(shoppingItem.getProduct().getCategory());
            }

        }
        String totPrice = String.format("%.2f", calcPrice);
        price.setText(totPrice + " Kr");

        for (int i = 0; i < categories.size(); i++) {

            List<ShoppingItem> arr = new ArrayList<>();

            for (ShoppingItem shoppingItem : order.getItems()) {//lägger till alla produkter som har en kategori som finns i categories
                if(categories.get(i).equals(shoppingItem.getProduct().getCategory())){
                    arr.add(shoppingItem);
                }
            }


            KvittoProduct kvittoProduct = new KvittoProduct(categories.get(i).name(), arr);
            grid.setConstraints(kvittoProduct, 0, i);
            grid.getChildren().add(kvittoProduct);
        }


        System.out.println("--KVITTO--");
        System.out.println("Totalt pris: " + calcPrice);
        System.out.println("Antal produkter: " + order.getItems().size());

    }

    private void addEventListeners() {
        close.setOnAction(event -> parent.hideKvitto());
        addToCartButton.setOnAction(event -> addToCart());
    }

    private void addToCart(){

        MainPage mainPage = Main.getMainPage();
        for (ShoppingItem s : order.getItems()) {
            System.out.println("Kvitto, antal utav varorna: " + s.getAmount());
            if(IMat.getInstance().getShoppingCart().getItems().contains(s)){
                System.out.println("fdlksnflks");
                List<ShoppingItem> shoppingItems = IMat.getInstance().getShoppingCart().getItems();
                int i = shoppingItems.indexOf(s);
                shoppingItems.get(i).setAmount(shoppingItems.get(i).getAmount() + s.getAmount());
                IMat.getInstance().getShoppingCart().fireShoppingCartChanged(shoppingItems.get(i), true);
            }else {
                IMat.getInstance().getShoppingCart().addItem(s);
            }

        }

        Main.getNavbar().goToMainPage();
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Kvitto.fxml"));
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
