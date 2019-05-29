package Model.pages.Checkout;

import Model.HelperClasses.UpdateButtonObservable;
import Model.IMat;
import Model.Main;
import Model.components.Forms.Kontouppgifter.KontoUppgifter;
import Model.components.Forms.PersonUppgifter.PersonUppgifter;
import Model.components.Navbar.Navbar;
import Model.pages.Checkout.PaymentDoneBox.PaymentDoneBox;
import Model.pages.Checkout.SequenceMap.SequenceMap;
import Model.pages.Checkout.SequenceMapFinal.SequenceMapFinal;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckout;
import Model.components.TitledSection.TitledSection;
import Model.pages.Checkout.ShoppingCheckout.ShoppingCheckoutDetails.ShoppingCheckoutDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import se.chalmers.cse.dat216.project.*;

import java.awt.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Checkout extends AnchorPane implements UpdateButtonObservable, ShoppingCartListener {
    @FXML private AnchorPane TopNav;

    @FXML private AnchorPane paymentContainerAnchorPane;
    @FXML private ScrollPane paymentScrollPane;
    @FXML private VBox paymentContainerVBox;

    @FXML private AnchorPane varukorgContainerAnchorPane;
    @FXML private VBox varukorgContainerVBox;

    @FXML private AnchorPane paymentDoneContainerAnchorPane;
    @FXML private VBox paymentDoneContainerVBox;
    @FXML private ScrollPane paymentDoneScrollPane;

    private Button makePaymentButton;

    private Label totalPriceLabel;

    public Checkout(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        IMat.getInstance().getShoppingCart().addShoppingCartListener(this);

        paymentScrollPane.getStyleClass().clear();
        paymentDoneScrollPane.getStyleClass().clear();

        initUI();
    }

    public void initUI() {
        //TopNav.getChildren().add(new Navbar());
        varukorgContainerAnchorPane.toFront();


        //createPaymentDoneUI();
        createPaymentUI();
        createVarukorgUI();
        updateButton();
    }

    private void createPaymentDoneUI() {
        paymentDoneContainerVBox.getChildren().add(new PaymentDoneBox(null));
    }

    private void createPaymentUI() {
        paymentContainerVBox.setSpacing(20.0);
        Hyperlink goTovarukorgLink = new Hyperlink("<-- Gå Tillbaka till Varukorgen");
        goTovarukorgLink.getStyleClass().add("text-md");
        goTovarukorgLink.setOnAction(event -> goToVarukorgStep());

        SequenceMapFinal sequenceMapFinal = new SequenceMapFinal();
        HBox goToVarukorgHBox = new HBox();
        goToVarukorgHBox.setAlignment(Pos.CENTER_LEFT);
        goToVarukorgHBox.setSpacing(55);
        goToVarukorgHBox.getChildren().addAll(goTovarukorgLink, sequenceMapFinal);

        makePaymentButton = new Button();
        makePaymentButton.getStyleClass().addAll("btn-lg", "btn-primary");
        makePaymentButton.setPrefWidth(paymentContainerVBox.getPrefWidth() - 55);
        makePaymentButton.setTranslateX( makePaymentButton.getTranslateX()-45);
        makePaymentButton.setOnAction(e -> makePaymentButtonPressed());
        disablePayButton();
        HBox makePaymentHBox = new HBox();
        makePaymentHBox.setPrefWidth(makePaymentButton.getPrefWidth());
        makePaymentHBox.setAlignment(Pos.CENTER);
        makePaymentHBox.getChildren().add(makePaymentButton);

        TitledSection titledSectionPerson = new TitledSection("1. Kontrollera dina personuppgifter", "");
        PersonUppgifter personUppgifter = new PersonUppgifter(this);
        titledSectionPerson.addNode(personUppgifter);

        TitledSection titledSectionKonto = new TitledSection("2. Kontrollera dina kontouppgifter", "");
        titledSectionKonto.addNode(new KontoUppgifter(this));

        //TitledSection titledSectionPay = new TitledSection("3. Slutför din order", null);
        ShoppingCheckoutDetails details = new ShoppingCheckoutDetails();

        HBox detailsHBox = new HBox();
        detailsHBox.setPrefWidth(1100);
        detailsHBox.setAlignment(Pos.CENTER);
        detailsHBox.setTranslateX(detailsHBox.getTranslateX() - 55);
        detailsHBox.getChildren().add(details);

        HBox hContainer = new HBox();
        hContainer.setTranslateX(hContainer.getTranslateX() + 50);
        hContainer.setAlignment(Pos.CENTER);
        VBox container = new VBox();
        container.setSpacing(25);
        container.setAlignment(Pos.CENTER);
        container.getChildren().addAll(detailsHBox, makePaymentHBox);
        hContainer.getChildren().add(container);


        Rectangle padding = new Rectangle(10, 20);
        padding.setFill(Color.TRANSPARENT);

        paymentContainerVBox.getChildren().addAll(goToVarukorgHBox, titledSectionPerson, titledSectionKonto, hContainer, padding);
    }

    @Override
    public void updateButton() {
        if (IMat.getInstance().isCustomerComplete() && IMat.getInstance().isCreditCardComplete()) {
            enablePayButton();
        } else {
            disablePayButton();
        }
    }

    private void enablePayButton() {
        makePaymentButton.setDisable(false);
        makePaymentButton.setText("Betala");
    }

    private void disablePayButton() {
        makePaymentButton.setDisable(true);
        makePaymentButton.setText("Fyll i all obligatorisk information");
    }

    private void makePaymentButtonPressed() {
        //IMat.getInstance().placeOrder(true);
        Order order = IMat.getInstance().placeOrder();


        double d = 0;
        for (ShoppingItem s : order.getItems()) {
            d += s.getTotal();
        }

        System.out.println("--Checkout--");
        System.out.println("Totalt pris: " + d);
        System.out.println("Antal produkter: " + order.getItems().size());

        paymentDoneContainerVBox.getChildren().clear();
        paymentDoneContainerVBox.getChildren().add(new PaymentDoneBox(order));
        goToPaymentDoneStep();
    }

    private void createVarukorgUI() {
        TitledSection varukorgSection = new TitledSection("Granska din varukorg", "Få en överblick och lägg till eller ta bort varor. Gå sen vidare till kassan.");

        ShoppingCheckout shoppingCheckout = new ShoppingCheckout();

        varukorgSection.addNode(shoppingCheckout);
        Hyperlink continueShoppingHyperlink = new Hyperlink("<-- Handla mer");
        continueShoppingHyperlink.getStyleClass().add("text-md");
        continueShoppingHyperlink.setOnAction(event -> Main.getNavbar().goToMainPage());

        SequenceMap sequenceMap = new SequenceMap();
        HBox continueShoppingHBox = new HBox();
        continueShoppingHBox.setAlignment(Pos.CENTER_LEFT);
        continueShoppingHBox.setSpacing(90);
        continueShoppingHBox.getChildren().addAll(continueShoppingHyperlink, sequenceMap);

        Button goToPaymentButton = new Button();
        Label totalLabel = new Label("Totalt pris: ");
        totalLabel.getStyleClass().add("text-lg");
        double totalPrice = IMat.getInstance().getShoppingCart().getTotal();
        String totPrice = String.format("%.2f", totalPrice);

        totalPriceLabel = new Label(totPrice+ " kr   ");
        totalPriceLabel.getStyleClass().addAll("text-lg", "bold");
        goToPaymentButton.setText("Gå till Kassan -->");
        goToPaymentButton.getStyleClass().addAll("btn-primary", "btn-lg");
        goToPaymentButton.setOnAction(e -> goToPaymentStep());
        HBox goToPaymentHBox = new HBox();
        goToPaymentHBox.setSpacing(5);
        goToPaymentHBox.setAlignment(Pos.CENTER_RIGHT);
        goToPaymentHBox.getChildren().addAll(totalLabel, totalPriceLabel, goToPaymentButton);

        varukorgContainerVBox.getChildren().add(continueShoppingHBox);
        varukorgContainerVBox.getChildren().add(varukorgSection);
        varukorgContainerVBox.getChildren().add(goToPaymentHBox);
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    private void goToPaymentDoneStep() {
        paymentDoneContainerAnchorPane.toFront();
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


    public void setNavBar(Navbar navBar){
        if(!TopNav.getChildren().contains(navBar))
        TopNav.getChildren().add(navBar);
    }

    @Override
    public void shoppingCartChanged(CartEvent cartEvent) {
        System.out.println("here******************************");
        totalPriceLabel.setText(Double.toString(IMat.getInstance().getShoppingCart().getTotal()) + " kr   ");
    }
}
