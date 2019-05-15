package Model.pages.Checkout.ShoppingCartIsEmpty;

import Model.IMat;
import Model.Main;
import Model.components.Navbar.Navbar;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckout;
import Model.components.TitledSection.TitledSection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.awt.*;
import java.io.IOException;

public class ShoppingCartIsEmpty extends AnchorPane {
    @FXML private Button continueShoppingButton;

    public ShoppingCartIsEmpty(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        continueShoppingButton.addActionListener(e -> onContinueShoppingButtonPressed());
    }

    private void onContinueShoppingButtonPressed() {
        Main.setSceneToMainPage();
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ShoppingCartIsEmpty.fxml"));
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
