package Model.pages.Checkout.SequenceMap;

import Model.IMat;
import Model.Main;
import Model.components.Forms.Kontouppgifter.KontoUppgifter;
import Model.components.Forms.PersonUppgifter.PersonUppgifter;
import Model.components.LeftSidebar.LeftSidebarCategory.MainCategory;
import Model.components.Navbar.Navbar;
import Model.pages.Checkout.Checkout;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckout;
import Model.components.TitledSection.TitledSection;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckoutDetails.ShoppingCheckoutDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class SequenceMap extends AnchorPane {
    public SequenceMap(Order order){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SequenceMap.fxml"));
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
