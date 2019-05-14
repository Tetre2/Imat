package Model.pages.Mainpage;

import Model.IMat;
import Model.components.LeftSidebar.LeftSidebar;
import Model.components.Navbar.Navbar;
import Model.components.RightSidebar.RightSidebar;
import Model.components.ShoppingItem.ShoppingItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.List;

public class MainPage extends AnchorPane{


    @FXML
    GridPane grid;
    @FXML
    AnchorPane LeftNavBar;
    @FXML
    AnchorPane varukorg;
    @FXML
    AnchorPane topNavBar;

    private IMat iMat;


    public MainPage(){
        iMat = IMat.getInstance();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        showProductsGrid(iMat.getProducts());

        LeftSidebar leftSidebar = new LeftSidebar();
        LeftNavBar.getChildren().add(leftSidebar);

        RightSidebar rightSidebar = new RightSidebar();
        varukorg.getChildren().add(rightSidebar);

        Navbar navbar = new Navbar();
        topNavBar.getChildren().add(navbar);




    }




    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
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

    private void showProductsGrid(List<Product> products){

        for (int i = 0; i < products.size(); i++) {
            ShoppingItem shoppingItem = new ShoppingItem(products.get(i));

            grid.setConstraints(shoppingItem, i%4, i/4);
            grid.getChildren().add(shoppingItem);

        }
    }

}
