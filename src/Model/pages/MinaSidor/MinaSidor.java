package Model.pages.MinaSidor;

import Model.components.Forms.Kontouppgifter.KontoUppgifter;
import Model.components.Forms.PersonUppgifter.PersonUppgifter;
import Model.components.Navbar.Navbar;
import Model.components.TitledSection.TitledSection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MinaSidor extends AnchorPane {


    @FXML
    private AnchorPane TopNav;
    @FXML
    private VBox vBox;

    public MinaSidor(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();


        TitledSection titledSectionPerson = new TitledSection("PersonUppgifter");
        titledSectionPerson.addNode(new PersonUppgifter());

        TitledSection titledSectionKonto = new TitledSection("KontoUppgifter");
        titledSectionKonto.addNode(new KontoUppgifter());




        TopNav.getChildren().add(new Navbar());
        vBox.getChildren().add(titledSectionPerson);
        vBox.getChildren().add(titledSectionKonto);



    }

    private void addEventListeners(){

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MinaSidor.fxml"));
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
