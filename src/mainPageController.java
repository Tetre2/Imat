import components.ShoppingItem.ShoppingItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class mainPageController extends AnchorPane{


    @FXML
    GridPane grid;
    @FXML
    AnchorPane LeftNavBar;



    public mainPageController(IMat iMat){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        for (int i = 0; i < iMat.getProducts().size(); i++) {

            ShoppingItem shoppingItem = new ShoppingItem();

            grid.setConstraints(shoppingItem, i%4, i/4);
            grid.getChildren().add(shoppingItem);



        }


        /*ShoppingItem shoppingItem = new ShoppingItem();

        grid.setConstraints(shoppingItem, 0, 0);
        grid.getChildren().add(shoppingItem);*/

        //LeftNavBar.getChildren().add(new ShoppingItem());//Rectangle(10,10,100,100));

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



}
