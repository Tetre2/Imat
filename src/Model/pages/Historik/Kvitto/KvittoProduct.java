package Model.pages.Historik.Kvitto;

import Model.IMat;
import Model.components.LeftSidebar.LeftSidebar;
import Model.components.Navbar.Navbar;
import Model.pages.Historik.HistorikItem.HistorikItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import se.chalmers.cse.dat216.project.*;

import java.io.IOException;
import java.util.List;

public class KvittoProduct extends AnchorPane {

    @FXML
    private Label category;
    @FXML
    private VBox vBox;

    public KvittoProduct(String categoryName, List<Product> products) {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        System.out.println(categoryName);
        category.setText(categoryName);

        for (Product p : products) {
            vBox.getChildren().add(new Label((p.getName())));
        }


    }

    private void addEventListeners() {

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KvittoProduct.fxml"));
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
