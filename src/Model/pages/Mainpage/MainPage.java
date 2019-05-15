package Model.pages.Mainpage;

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
import se.chalmers.cse.dat216.project.Product;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        //visa alla produkter
        //showProductsGrid(iMat.getProducts());

        leftSidebar = new LeftSidebar();
        LeftNavBar.getChildren().add(leftSidebar);
        addCategoriesToLeftSideBar(leftSidebar);
        RightSidebar rightSidebar = new RightSidebar();
        varukorg.getChildren().add(rightSidebar);

        Navbar navbar = new Navbar();
        topNavBar.getChildren().add(navbar);

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

    private void addCategoriesToLeftSideBar(LeftSidebar leftSidebar){
        for(MainCategory category : MainCategory.values()){
            CategoryItem categoryItem = new CategoryItem(category);
            categoryItem.setOnMouseClicked(event -> testEvent(category));
            leftSidebar.addCategory(categoryItem);
        }
    }

    private void testEvent(MainCategory mainCategory){
        System.out.println("category: " + mainCategory.toString() + " was clicked");
        updateGrid(mainCategory);
        setMainCategoryFocused(mainCategory);
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

    //cache used for storing shopping items in order to not generate new ones.
    //Creating new objects all the time consumes a high amount of ram because of the product-image.
    private Map<MainCategory, List<ShoppingItem>> cachedShoppingItems = new HashMap<>();

    private List<ShoppingItem> getShoppingItems(MainCategory mainCategory){

        List<ShoppingItem> shoppingItems = new ArrayList<>();

        if(cachedShoppingItems.containsKey(mainCategory)){          // Reuse shopping items that has already been generated.
            shoppingItems = cachedShoppingItems.get(mainCategory);

        }else{                                                      // Generate shopping items and store them in our Map as cache.
            for (int i = 0; i < mainCategory.getProducts().size(); i++) {
                ShoppingItem shoppingItem = new ShoppingItem(mainCategory.getProducts().get(i));
                shoppingItems.add(shoppingItem);
                cachedShoppingItems.put(mainCategory, shoppingItems);
            }
        }
        return shoppingItems;
    }

    public List<ShoppingItem> preloadShoppingItems(){
       // List<ShoppingItem> shoppingItems
        for(MainCategory mainCategory: MainCategory.values()){

        }
    }

    public void updateGrid(MainCategory mainCategory){

        //Clear current grid of shoppingitems
        grid.getChildren().clear();

        //Get shopping items, either from cache or generate from scratch.
        List<ShoppingItem> shoppingItems = getShoppingItems(mainCategory);

        //Display shopping items
        for(int i = 0; i < shoppingItems.size(); i++) {
            grid.setConstraints(shoppingItems.get(i), i % 4, i / 4);
            grid.getChildren().add(shoppingItems.get(i));
        }
    }


    private void showProductsGrid(List<Product> products){

        for (int i = 0; i < products.size(); i++) {
            ShoppingItem shoppingItem = new ShoppingItem(products.get(i));

            grid.setConstraints(shoppingItem, i%4, i/4);
            grid.getChildren().add(shoppingItem);

        }
    }

}
