package Model.pages.Checkout;

import Model.IMat;
import Model.components.Forms.Kontouppgifter.KontoUppgifter;
import Model.components.Forms.PersonUppgifter.PersonUppgifter;
import Model.components.Navbar.Navbar;
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

import java.io.IOException;

public class Checkout extends AnchorPane {
    @FXML private AnchorPane TopNav;

    @FXML private AnchorPane paymentContainerAnchorPane;
    @FXML private ScrollPane paymentScrollPane;
    @FXML private VBox paymentContainerVBox;

    @FXML private AnchorPane varukorgContainerAnchorPane;
    @FXML private VBox varukorgContainerVBox;

    @FXML private AnchorPane paymentDoneContainerAnchorPane;


    public Checkout(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        addEventListeners();

        initUI();
    }

    public void initUI() {
        TopNav.getChildren().add(new Navbar());

        createVarukorgUI();
        createPaymentUI();
    }

    private void createPaymentUI() {
        paymentContainerVBox.setSpacing(20.0);

        Button goToVarukorgButton = new Button();
        goToVarukorgButton.setText("<-- Gå Tillbaka till Varukorgen");
        goToVarukorgButton.getStyleClass().add("btn-primary");
        goToVarukorgButton.setOnAction(e -> goToVarukorgStep());
        HBox goToVarukorgHBox = new HBox();
        goToVarukorgHBox.setAlignment(Pos.CENTER_LEFT);
        goToVarukorgHBox.getChildren().add(goToVarukorgButton);

        Button makePaymentButton = new Button();
        makePaymentButton.setText("Betala");
        makePaymentButton.getStyleClass().add("btn-primary");
        makePaymentButton.getStyleClass().add("btn-lg");
        makePaymentButton.setOnAction(e -> makePaymentButtonPressed());
        HBox makePaymentHBox = new HBox();
        makePaymentHBox.setAlignment(Pos.CENTER);
        makePaymentHBox.getChildren().add(makePaymentButton);

        TitledSection titledSectionPerson = new TitledSection("Kontrollera dina personuppgifter");
        titledSectionPerson.addNode(new PersonUppgifter());

        TitledSection titledSectionKonto = new TitledSection("Gör din beställning");
        ShoppingCheckoutDetails details = new ShoppingCheckoutDetails();
        titledSectionKonto.addNode(details);
        titledSectionKonto.addNode(new KontoUppgifter());


        paymentContainerVBox.getChildren().add(goToVarukorgHBox);
        paymentContainerVBox.getChildren().add(titledSectionPerson);
        paymentContainerVBox.getChildren().add(titledSectionKonto);
    }

    private void makePaymentButtonPressed() {
        IMat.getInstance().placeOrder(true);
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

        varukorgContainerVBox.getChildren().add(continueShoppingHBox);
        varukorgContainerVBox.getChildren().add(varukorgSection);
        varukorgContainerVBox.getChildren().add(goToPaymentHBox);
    }

    private void goToPaymentStep() {
        paymentContainerAnchorPane.toFront();
    }

    private void goToVarukorgStep() {
        varukorgContainerAnchorPane.toFront();
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
