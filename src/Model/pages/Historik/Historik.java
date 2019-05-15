package Model.pages.Historik;

import Model.IMat;
import Model.components.LeftSidebar.LeftSidebar;
import Model.components.Navbar.Navbar;
import Model.pages.Historik.HistorikItem.HistorikItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;

public class Historik extends AnchorPane {

    @FXML
    private AnchorPane TopNav;
    @FXML
    private GridPane gridPane;

    public Historik(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        TopNav.getChildren().add(new Navbar());

        for (int i = 0; i < IMat.getInstance().getOrders().size(); i++) {
            HistorikItem historikItem = new HistorikItem(IMat.getInstance().getOrders().get(i));
            gridPane.setConstraints(historikItem, 0, i);
            gridPane.getChildren().add(historikItem);
        }

    }

    private void addEventListeners(){

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Historik.fxml"));
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