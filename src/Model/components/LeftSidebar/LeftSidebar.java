package Model.components.LeftSidebar;

import Model.Main;
import Model.components.LeftSidebar.LeftSidebarCategory.CategoryListener;
import Model.components.LeftSidebar.LeftSidebarCategory.CategoryItem;
import Model.components.LeftSidebar.LeftSidebarCategory.MainCategory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Paint;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LeftSidebar extends AnchorPane  {

    @FXML
    private Button testingButton;
    @FXML
    private FlowPane categoryPane;
    private List<CategoryItem> categoryItems;
    private ArrayList<CategoryListener> listeners = new ArrayList();
    public LeftSidebar(){
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);
        addEventListeners();
        categoryItems = new ArrayList<CategoryItem>();
        addCategoriesToLeftSideBar();

    }

    private void addCategoriesToLeftSideBar(){
        for(MainCategory category : MainCategory.values()){
            CategoryItem categoryItem = new CategoryItem(category);
            categoryItem.setOnMouseClicked(event -> fireCategoryChanged(category));
            addCategory(categoryItem);
        }
    }

    public void fireCategoryChanged(MainCategory mainCategory) {
        setMainCategoryFocused(mainCategory);
        Iterator var4 = this.listeners.iterator();
        while (var4.hasNext()) {
            CategoryListener scl = (CategoryListener) var4.next();
            scl.categoryChanged(mainCategory);
        }
    }





    private void setMainCategoryFocused(MainCategory selectedCategory){
        for(CategoryItem categoryItem: getCategories()){
            System.out.println("removing style");
            categoryItem.getStyleClass().clear();
            //In this way you're sure you have no styles applied to your object button
            if(categoryItem.getCategory().equals(selectedCategory)){
                categoryItem.getStyleClass().add("anchor-container-focused");
                categoryItem.getCategoryName().setTextFill(Paint.valueOf("white"));

            }else{
                categoryItem.getStyleClass().add("anchor-container-normal");
                categoryItem.getCategoryName().setTextFill(Paint.valueOf("#194b42"));
            }

            //then you specify the class you would give to the button

        }
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
    public void addCategoryListener(CategoryListener scl) {
        this.listeners.add(scl);
    }

    public void removeCategoryListener(CategoryListener scl) {
        this.listeners.remove(scl);
    }





}
