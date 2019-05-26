package Model.pages.Hjalp;

import Model.components.Navbar.Navbar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class Hjalp extends AnchorPane {

    @FXML
    private AnchorPane TopNav;
    @FXML
    private TextArea textArea;

    public Hjalp(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        textArea.getStyleClass().removeAll(/*"text-area", */ "text-input");


        //TopNav.getChildren().add(new Navbar());

    }


    private void addEventListeners(){


    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Hjalp.fxml"));
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

}
