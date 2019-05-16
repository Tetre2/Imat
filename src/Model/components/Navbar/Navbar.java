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

import static Model.components.LeftSidebar.LeftSidebarCategory.MainCategory.ALLA_VAROR;

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
    private TextField searchBar;

    LeftSidebar leftSidebar = new LeftSidebar();


    public Navbar(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();
    }

    @FXML
    private void onIMatPressed() { //setMainCategoryFocused fungerar inte helt när man använder den på detta vis verkar det som.
        Main.setSceneToMainPage();
        leftSidebar.setMainCategoryFocused(ALLA_VAROR);
        Main.changeCategory(ALLA_VAROR);


    }

    private void addEventListeners(){

        hjalp.setOnAction(e -> Main.setSceneToHjalp());
        kvitton.setOnAction(e -> Main.setSceneToHistorik());
        minaSidor.setOnAction(e -> Main.setSceneToMinaSidor());
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


}
