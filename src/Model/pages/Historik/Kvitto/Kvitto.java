package Model.pages.Historik.Kvitto;

import Model.IMat;
import Model.components.LeftSidebar.LeftSidebar;
import Model.components.Navbar.Navbar;
import Model.pages.Historik.Historik;
import Model.pages.Historik.HistorikItem.HistorikItem;
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

    public Kvitto(Order order, Historik parent) {
        categories = new ArrayList();
        this.parent = parent;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        date.setText(order.getDate().toString());

        double calcPrice = 0;

        for (ShoppingItem shoppingItem : order.getItems()) {
            calcPrice += shoppingItem.getTotal();//ger totala kostnaden

            if(!categories.contains(shoppingItem.getProduct().getCategory())){//om categorin inte finns i listan läggs den till
                System.out.println("added category");
                categories.add(shoppingItem.getProduct().getCategory());
            }

        }

        price.setText(calcPrice + " Kr");

        System.out.println("categorier" + categories.size());

        for (int i = 0; i < categories.size(); i++) {

            List<Product> arr = new ArrayList<>();

            for (ShoppingItem shoppingItem : order.getItems()) {//lägger till alla produkter som har en kategori som finns i categories
                if(categories.get(i).equals(shoppingItem.getProduct().getCategory())){
                    System.out.println("added product");
                    arr.add(shoppingItem.getProduct());
                }
            }


            KvittoProduct kvittoProduct = new KvittoProduct(categories.get(i).name(), arr);
            grid.setConstraints(kvittoProduct, 0, i);
            grid.getChildren().add(kvittoProduct);
        }



    }

    private void addEventListeners() {
        close.setOnAction(event -> parent.hideKvitto());
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
