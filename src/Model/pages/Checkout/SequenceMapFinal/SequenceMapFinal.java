package Model.pages.Checkout.SequenceMapFinal;

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
import javafx.scene.shape.Circle;
import se.chalmers.cse.dat216.project.Order;
import se.chalmers.cse.dat216.project.ShoppingItem;

import javax.swing.text.html.ImageView;
import java.io.IOException;

public class SequenceMapFinal extends AnchorPane {
    @FXML private Button handlaButton;
    @FXML private Button handlaButton2;
    @FXML private Button varukorgButton;
    @FXML private Button varukorgButton2;

    public SequenceMapFinal(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        handlaButton.setOnAction(e -> IMat.getInstance().setSceneToMainPage());
        handlaButton2.setOnAction(e -> IMat.getInstance().setSceneToMainPage());
        handlaButton.getStyleClass().add("btn-hover");
        handlaButton2.getStyleClass().add("btn-hover");

        varukorgButton.setOnAction(e -> IMat.getInstance().setSceneToCheckout());
        varukorgButton2.setOnAction(e -> IMat.getInstance().setSceneToCheckout());
        varukorgButton.getStyleClass().add("btn-hover");
        varukorgButton2.getStyleClass().add("btn-hover");
    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SequenceMapFinal.fxml"));
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
