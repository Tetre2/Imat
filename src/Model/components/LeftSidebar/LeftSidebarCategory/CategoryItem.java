package Model.components.LeftSidebar.LeftSidebarCategory;

import Model.Main;
import Model.pages.Mainpage.MainPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.io.IOException;

public class CategoryItem extends AnchorPane {

    @FXML
    private Label categoryName;
    @FXML
    private FlowPane categoryPane;

    private MainCategory category;
    public CategoryItem(MainCategory category){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        this.category = category;
        this.categoryName.setText(category.name);
        addEventListeners();


    }


    private void addEventListeners() {
        //categoryPane.setOnMouseClicked(e -> mainPage.updateGrid(category));

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CategoryItem.fxml"));
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

    public MainCategory getCategory(){
        return category;
    }


}