package Model.pages.Historik.HistorikItem;

import Model.IMat;
import Model.components.Navbar.Navbar;
import Model.pages.Historik.Historik;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.swing.text.html.ListView;
import java.io.IOException;
import java.util.List;

public class HistorikItem extends AnchorPane {

    @FXML
    private Button kvittoButton;
    @FXML
    private Label date;
    @FXML
    private Label orderNumber;
    @FXML
    private Label price;

    private Historik parent;
    private Order order;

    public HistorikItem(Order order, Historik parent){
        this.parent = parent;
        this.order = order;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        List<ShoppingItem> items= order.getItems();
        double total = 0;
        for (ShoppingItem s : items) {
            total += s.getTotal();
        }
        price.setText(total + " Kr");
        date.setText(order.getDate().toString());
        orderNumber.setText(order.getOrderNumber() + "");



    }

    private void addEventListeners(){
        kvittoButton.setOnAction(e -> parent.showKvitto(order));
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("HistorikItem.fxml"));
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
