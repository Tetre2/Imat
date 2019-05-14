package Model.Checkout;

import Model.components.Navbar.Navbar;
import Model.components.TitledSection.TitledSection;
import Model.components.VarukorgItem.VarukorgItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class Checkout extends AnchorPane {

    @FXML
    private FlowPane content;
    @FXML AnchorPane topNavBar;

    public Checkout(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        addEventListeners();

        initUI();
    }

    private void initUI() {
        Navbar navbar = new Navbar();
        content.getChildren().add(navbar);


        TitledSection varukorgSection = new TitledSection("Granska din varukorg");
        VarukorgItem testItem1 = new VarukorgItem();
        VarukorgItem testItem2 = new VarukorgItem();
        VarukorgItem testItem3 = new VarukorgItem();

        VBox varukorgItemsContainer = new VBox();
        varukorgItemsContainer.setSpacing(15);

        varukorgItemsContainer.getChildren().add(testItem1);
        varukorgItemsContainer.getChildren().add(testItem2);
        varukorgItemsContainer.getChildren().add(testItem3);

        varukorgSection.addNode(varukorgItemsContainer);

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
