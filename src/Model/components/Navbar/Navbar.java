package Model.components.Navbar;

import Model.Main;
import Model.components.LeftSidebar.LeftSidebar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static Model.components.LeftSidebar.LeftSidebarCategory.MainCategory.ALLA_VAROR;

//import static Model.components.LeftSidebar.LeftSidebarCategory.MainCategory.ALLA_VAROR;

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

    private List<Button> buttons;

    public Navbar(){
        buttons = new ArrayList<>();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        buttons.add(hjalp);
        buttons.add(kvitton);
        buttons.add(minaSidor);
        buttons.add(handla);
    }

    private void addEventListeners(){
        iMatLabel.setOnMouseClicked(event -> goToMainPage());
        hjalp.setOnAction(e -> goToHjalp());
        kvitton.setOnAction(e -> goToHistorik());
        minaSidor.setOnAction(e -> goToMinaSidor());
        handla.setOnAction(event -> goToMainPage());

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

    @FXML
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
