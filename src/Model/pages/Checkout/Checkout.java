package Model.pages.Checkout;

import Model.IMat;
import Model.components.Navbar.Navbar;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckout;
import Model.components.TitledSection.TitledSection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.io.IOException;

public class Checkout extends AnchorPane {
    @FXML private AnchorPane TopNav;

    @FXML private AnchorPane paymentContainerAnchorPane;
    @FXML private ScrollPane paymentScrollPane;
    @FXML private VBox paymentContainerVBox;

    @FXML private AnchorPane varukorgContainerAnchorPane;
    @FXML
    private VBox content;
    @FXML private AnchorPane topNavBar;

    public Checkout(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        addEventListeners();
    }

    public void initUI() {
        clearUI();

        TopNav.getChildren().add(new Navbar());

        createVarukorgUI();
        createPaymentUI();
    }

    private void createPaymentUI() {
        Button goToVarukorgButton = new Button();
        goToVarukorgButton.setText("<-- Gå Tillbaka till Varukorgen");
        goToVarukorgButton.getStyleClass().add("btn-primary");
        goToVarukorgButton.setOnAction(e -> IMat.getInstance().setSceneToMainPage());
        HBox goToVarukorgHBox = new HBox();
        goToVarukorgHBox.setAlignment(Pos.CENTER_LEFT);
        goToVarukorgHBox.getChildren().add(goToVarukorgButton);
    }

    private void createVarukorgUI() {
        TitledSection varukorgSection = new TitledSection("Granska din varukorg");

        ShoppingCheckout shoppingCheckout = new ShoppingCheckout();

        varukorgSection.addNode(shoppingCheckout);

        Button continueShopping = new Button();
        continueShopping.setText("<-- Fortsätt Handla");
        continueShopping.getStyleClass().add("btn-primary");
        continueShopping.setOnAction(e -> IMat.getInstance().setSceneToMainPage());
        HBox continueShoppingHBox = new HBox();
        continueShoppingHBox.setAlignment(Pos.CENTER_LEFT);
        continueShoppingHBox.getChildren().add(continueShopping);

        Button goToPaymentButton = new Button();
        goToPaymentButton.setText("Gå till Kassan -->");
        goToPaymentButton.getStyleClass().add("btn-primary");
        goToPaymentButton.setOnAction(e -> goToPaymentStep());
        HBox goToPaymentHBox = new HBox();
        goToPaymentHBox.setAlignment(Pos.CENTER_RIGHT);
        goToPaymentHBox.getChildren().add(goToPaymentButton);

        content.getChildren().add(continueShoppingHBox);
        content.getChildren().add(varukorgSection);
        content.getChildren().add(goToPaymentHBox);
    }

    private void goToPaymentStep() {
        paymentContainerAnchorPane.toFront();
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
