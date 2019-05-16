package Model.pages.Historik.HistorikItem;

import Model.IMat;
import Model.components.Navbar.Navbar;
import Model.pages.Historik.Historik;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;

public class HistorikItem extends AnchorPane {

    @FXML
    private Button kvittoButton;

    private Historik parent;
    private Order order;

    public HistorikItem(Order order, Historik parent){
        this.parent = parent;
        this.order = order;
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();




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
