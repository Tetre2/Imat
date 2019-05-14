package Model.Mainpage;

import Model.IMat;
import Model.components.LeftSidebar.LeftSidebar;
import Model.components.LeftSidebar.LeftSidebarCategory.CategoryItem;
import Model.components.LeftSidebar.LeftSidebarCategory.MainCategory;
import Model.components.Navbar.Navbar;
import Model.components.RightSidebar.RightSidebar;
import Model.components.ShoppingItem.ShoppingItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
<<<<<<< HEAD
import se.chalmers.cse.dat216.project.ProductCategory;
=======
import se.chalmers.cse.dat216.project.Product;
>>>>>>> 85dcfea664a835fc17b81558cb734c9ec7b9d3ee

import java.io.IOException;
import java.util.List;

public class MainPage extends AnchorPane{


    @FXML
    GridPane grid;
    @FXML
    AnchorPane LeftNavBar;
    @FXML
    AnchorPane varukorg;
    @FXML
    AnchorPane topNavBar;

    private IMat iMat;
    private LeftSidebar leftSidebar;


    public MainPage(){
        iMat = IMat.getInstance();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        showProductsGrid(iMat.getProducts());

        leftSidebar = new LeftSidebar();
        LeftNavBar.getChildren().add(leftSidebar);
        addCategoriesToLeftSideBar(leftSidebar);
        RightSidebar rightSidebar = new RightSidebar();
        varukorg.getChildren().add(rightSidebar);

        Navbar navbar = new Navbar();
        topNavBar.getChildren().add(navbar);




    }





    private void testEvent(MainCategory mainCategory){
        System.out.println("category: " + mainCategory.toString() + " was clicked");
        updateGrid(mainCategory);
        setMainCategoryFocused(mainCategory);
    }





    private FXMLLoader initFXML() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainPage.fxml"));
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

<<<<<<< HEAD

    private void addCategoriesToLeftSideBar(LeftSidebar leftSidebar){
        for(MainCategory category : MainCategory.values()){
            CategoryItem categoryItem = new CategoryItem(category);
            categoryItem.setOnMouseClicked(event -> testEvent(category));
            leftSidebar.addCategory(categoryItem);
        }
    }

    private void setMainCategoryFocused(MainCategory selectedCategory){
        for(CategoryItem categoryItem: leftSidebar.getCategories()){
                System.out.println("removing style");
                categoryItem.getStyleClass().clear();
                //In this way you're sure you have no styles applied to your object button
                if(categoryItem.getCategory().equals(selectedCategory)){
                    categoryItem.getStyleClass().add("anchor-container-focused");
                }else{
                    categoryItem.getStyleClass().add("anchor-container-normal");
                }

                //then you specify the class you would give to the button

        }
    }

    public void updateGrid(MainCategory mainCategory){
        grid.getChildren().clear();
        for (int i = 0; i < mainCategory.getProducts().size(); i++) {
            ShoppingItem shoppingItem = new ShoppingItem(mainCategory.getProducts().get(i));

            grid.setConstraints(shoppingItem, i%4, i/4);
            grid.getChildren().add(shoppingItem);

        }
    }

    private void creatGrid(){
=======
    private void showProductsGrid(List<Product> products){
>>>>>>> 85dcfea664a835fc17b81558cb734c9ec7b9d3ee

        for (int i = 0; i < products.size(); i++) {
            ShoppingItem shoppingItem = new ShoppingItem(products.get(i));

            grid.setConstraints(shoppingItem, i%4, i/4);
            grid.getChildren().add(shoppingItem);

        }
    }

}
