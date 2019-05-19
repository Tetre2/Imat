package Model.components.Navbar;

import Model.IMat;
import Model.Main;
import Model.components.Navbar.SearchedItem.SearchedItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Navbar extends AnchorPane {

    @FXML
    private ImageView iMatLabel;
    @FXML
    private Button hjalp;
    @FXML
    private Button kvitton;
    @FXML
    private Button minaSidor;
    @FXML
    private Button handla;
    @FXML
    private TextField searchBar;
    @FXML
    private FlowPane searchItems;
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane nav;

    private List<Product> searchedItems;
    private List<Button> buttons;

    public Navbar(){
        buttons = new ArrayList<>();
        searchedItems = new ArrayList<>();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        buttons.add(hjalp);
        buttons.add(kvitton);
        buttons.add(minaSidor);
        buttons.add(handla);
        hideSearchedItems();
    }

    private void addEventListeners(){
        iMatLabel.setOnMouseClicked(event -> goToMainPage());
        hjalp.setOnAction(e -> goToHjalp());
        kvitton.setOnAction(e -> goToHistorik());
        minaSidor.setOnAction(e -> goToMinaSidor());
        handla.setOnAction(event -> goToMainPage());
        nav.setOnMouseClicked(event -> event.consume());
        root.setOnMouseClicked(event -> hideSearchedItems());

        searchBar.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
            if (isNowFocused) {
                showSearchedItems();
            }
            if (wasFocused) {
                hideSearchedItems();
            }
        });

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (searchBar.getText().length() >= 3) {
                //TODO TA bort output
                System.out.println();
                for (Product p : IMat.getInstance().findProducts(searchBar.getText())) {
                    System.out.println(p.getName());
                }
                searchedItems.clear();
                searchedItems.addAll(IMat.getInstance().findProducts(searchBar.getText()));
                showSearchedItems();
            }else {
                hideSearchedItems();
            }
        });

    }

    private void showSearchedItems(){
        System.out.println("SHOWING");
        searchItems.setVisible(true);
        searchItems.getChildren().clear();
        for (Product p : searchedItems) {
            searchItems.getChildren().add(new SearchedItem(p));
        }

    }

    private void hideSearchedItems(){
        searchedItems.clear();
        searchItems.getChildren().clear();
        searchItems.setVisible(false);

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Navbar.fxml"));
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

    private void onSearchTyped() {
        System.out.println(searchBar.getCharacters());

    }

    public void goToMainPage(){
        clearAllButtonStyles();
        setActiveButton(handla);
        Main.setSceneToMainPage();
    }

    public void goToMinaSidor(){
        clearAllButtonStyles();
        setActiveButton(minaSidor);
        Main.setSceneToMinaSidor();
    }

    public void goToHistorik(){
        clearAllButtonStyles();
        setActiveButton(kvitton);
        Main.setSceneToHistorik();
    }

    public void goToHjalp(){
        clearAllButtonStyles();
        setActiveButton(hjalp);
        Main.setSceneToHjalp();
    }

    private void clearAllButtonStyles(){
        for (Button b : buttons) {
            b.getStyleClass().clear();
            b.getStyleClass().add("button");
        }
    }

    private void setActiveButton(Button b){
        b.getStyleClass().add("selected");
    }

}
