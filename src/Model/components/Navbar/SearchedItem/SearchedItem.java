package Model.components.Navbar.SearchedItem;

import Model.IMat;
import Model.Main;
import Model.components.Navbar.Navbar;
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
    private ImageView image;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private AnchorPane root;

    private Product product;
    private Navbar parent;

    public SearchedItem(Navbar parent, Product product) {
        this.product = product;
        this.parent = parent;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        name.setText(product.getName());
        price.setText(product.getPrice() + "Kr");
        image.setImage(IMat.getInstance().getImage(product));

        addEventListeners();

    }


    private void addEventListeners(){
        root.setOnMouseClicked(event -> {
            List<Product> arr = new ArrayList<>();
            arr.add(product);
            Main.getMainPage().showProductsToGrid(arr, "Sökresultat: " + product.getName());
            parent.goToMainPage();

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
