package Model.pages.Historik.Kvitto.KvittoItem;

import Model.pages.Historik.Historik;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.Product;
import se.chalmers.cse.dat216.project.ShoppingItem;

import java.io.IOException;
import java.util.List;

public class KvittoItem extends AnchorPane {
    @FXML private Label titleLabel;
    @FXML private Label amountLabel;
    @FXML private Label priceLabel;

    public KvittoItem(ShoppingItem shoppingItem){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        titleLabel.setText(shoppingItem.getProduct().getName());
        amountLabel.setText(Double.toString(Math.floor(shoppingItem.getAmount())) + " " + shoppingItem.getProduct().getUnit());
        priceLabel.setText(Double.toString(shoppingItem.getTotal()) + "kr");
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("KvittoItem.fxml"));
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
