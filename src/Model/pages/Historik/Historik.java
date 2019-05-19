package Model.pages.Historik;

import Model.IMat;
import Model.components.Navbar.Navbar;
import Model.pages.Historik.HistorikItem.HistorikItem;
import Model.pages.Historik.Kvitto.Kvitto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Order;

import java.io.IOException;
import java.util.Date;

public class Historik extends AnchorPane {

    @FXML
    private AnchorPane TopNav;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane kvittoOverlay;
    @FXML
    private AnchorPane kvittoGray;

    public Historik(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();
        hideKvitto();

    }

    private void addEventListeners(){
        kvittoGray.setOnMouseClicked(event -> hideKvitto());
        kvittoOverlay.setOnMouseClicked(event -> event.consume());
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

    private void loadOrders(){
        for (int i = 0; i < IMat.getInstance().getOrders().size(); i++) {
            HistorikItem historikItem = new HistorikItem(IMat.getInstance().getOrders().get(i), this);
            gridPane.setConstraints(historikItem, 0, i);
            gridPane.getChildren().add(historikItem);
        }
    }

    public void showKvitto(Order order){
        kvittoGray.setVisible(true);
        kvittoOverlay.getChildren().clear();
        Kvitto kvitto = new Kvitto(order, this);
        kvittoOverlay.getChildren().add(kvitto);
    }

    public void hideKvitto(){
        kvittoOverlay.getChildren().clear();
        kvittoGray.setVisible(false);
    }

    public void setNavBar(Navbar navBar){
        if(!TopNav.getChildren().contains(navBar))
        TopNav.getChildren().add(navBar);

        loadOrders();
    }

    public String getDate(Date d){
        String[] date = d.toString().split(" ");
        return date[2] + " " + date[1];
    }

}
