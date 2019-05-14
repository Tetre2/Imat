package Model.components.ShoppingCheckout;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ShoppingCheckout extends AnchorPane {
    @FXML private GridPane gridPane;
    @FXML private Label foodPriceLabel;
    @FXML private Label transportPriceLabel;
    @FXML private Label totalPriceLabel;

    public ShoppingCheckout() {
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
    }

    public void addShoppingItem(Node item) {
        gridPane.add(item, 0, 1);
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Model/components/ShoppingCheckout/ShoppingCheckout.fxml"));
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
