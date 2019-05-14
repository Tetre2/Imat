package Model.Checkout;

import Model.components.Navbar.Navbar;
import Model.Checkout.ShoppingCheckout.ShoppingCheckout;
import Model.components.TitledSection.TitledSection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class Checkout extends AnchorPane {

    @FXML
    private FlowPane content;
    @FXML AnchorPane topNavBar;

    public Checkout(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        addEventListeners();
    }

    public void updateUI() {
        Navbar navbar = new Navbar();
        content.getChildren().add(navbar);


        TitledSection varukorgSection = new TitledSection("Granska din varukorg");

        ShoppingCheckout shoppingCheckout = new ShoppingCheckout();

        varukorgSection.addNode(shoppingCheckout);

        content.getChildren().add(varukorgSection);
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
