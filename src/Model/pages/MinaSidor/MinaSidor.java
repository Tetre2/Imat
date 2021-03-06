package Model.pages.MinaSidor;

import Model.HelperClasses.UpdateButtonObservable;
import Model.IMat;
import Model.components.Forms.Kontouppgifter.KontoUppgifter;
import Model.components.Forms.PersonUppgifter.PersonUppgifter;
import Model.components.Navbar.Navbar;
import Model.components.TitledSection.TitledSection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MinaSidor extends AnchorPane implements UpdateButtonObservable {


    @FXML
    private AnchorPane TopNav;
    @FXML
    private VBox vBox;
    @FXML
    private ScrollPane scrollPane;

    @FXML private PersonUppgifter personUppgifter;

    public MinaSidor(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        addEventListeners();

        TitledSection titledSectionKonto = new TitledSection("Kontouppgifter", "");
        KontoUppgifter tmpKonto = new KontoUppgifter(this, titledSectionKonto);
        titledSectionKonto.addNode(tmpKonto);

        TitledSection titledSectionPerson = new TitledSection("Personuppgifter", "");
        personUppgifter = new PersonUppgifter(this, titledSectionPerson);
        titledSectionPerson.addNode(personUppgifter);

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

    public void setNavBar(Navbar navBar){
        if(!TopNav.getChildren().contains(navBar))
        TopNav.getChildren().add(navBar);
    }

    @Override
    public void updateButton() {
        // Mmmm beautiful code :)
    }
}
