package Model.pages.Checkout;

import Model.components.Navbar.Navbar;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckout;
import Model.components.TitledSection.TitledSection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Checkout extends AnchorPane {
    @FXML
    private VBox content;
    @FXML AnchorPane topNavBar;

    public Checkout(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        addEventListeners();
    }

    public void initUI() {
        clearUI();

        Navbar navbar = new Navbar();
        content.getChildren().add(navbar);

        //TitledSection varukorgSection = new TitledSection("Granska din varukorg");

        ShoppingCheckout shoppingCheckout = new ShoppingCheckout();

        //varukorgSection.addNode(shoppingCheckout);

        content.getChildren().add(shoppingCheckout);
    }

    private void clearUI() {
        content.getChildren().clear();
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Checkout.fxml"));
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

    private void addEventListeners() {
        //pay.setOnAction(e -> onPayPressed());
    }


}
