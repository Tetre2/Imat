package Model.components.LeftSidebar;

import Model.IMat;
import Model.Main;
import Model.pages.Mainpage.MainPage;
import Model.components.LeftSidebar.LeftSidebarCategory.CategoryItem;
import Model.components.LeftSidebar.LeftSidebarCategory.MainCategory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import se.chalmers.cse.dat216.project.ProductCategory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeftSidebar extends AnchorPane  {

    @FXML
    private Button testingButton;
    @FXML
    private FlowPane categoryPane;
    private List<CategoryItem> categoryItems;
    public LeftSidebar(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        addEventListeners();
        categoryItems = new ArrayList<CategoryItem>();


    }


    public void addCategory(CategoryItem categoryItem){
        categoryItems.add(categoryItem);
        categoryPane.getChildren().add(categoryItem);
    }


    private void addEventListeners() {
        testingButton.setOnAction(e -> Main.setSceneToTesting());

    }

    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("LeftSidebar.fxml"));
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

    public List<CategoryItem> getCategories(){
        return categoryItems;
    }


}
