package Model.components.Navbar.SearchedItem;

import Model.Main;
import Model.components.ShoppingItem.ShoppingItem;
import Model.pages.Mainpage.MainPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchedItem extends AnchorPane {

    @FXML
    private ImageView iamge;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private AnchorPane root;

    private Product product;
    private static MainPage mainPage;

    public SearchedItem(Product product) {
        this.product = product;
        mainPage = Main.getMainPage();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        name.setText(product.getName());
        price.setText(product.getPrice() + "Kr");

        addEventListeners();

    }


    private void addEventListeners(){
        root.setOnMouseClicked(event -> {
            List<Product> arr = new ArrayList<>();
            arr.add(product);
            mainPage.showProductsToGrid(arr, product.getName());
        });
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchedItem.fxml"));
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
