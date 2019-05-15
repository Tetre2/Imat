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

public class MainPage extends AnchorPane implements CategoryListener{


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
        showAllItems();

        leftSidebar = new LeftSidebar();
        LeftNavBar.getChildren().add(leftSidebar);
        leftSidebar.addCategoryListener(this);
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
        List<ShoppingItem> shoppingItems = new ArrayList<>();
        for(MainCategory mainCategory: MainCategory.values()) {
            List<ShoppingItem> currentItems = new ArrayList<>();
            for (Product product : mainCategory.getProducts()) {
                ShoppingItem shoppingItem = new ShoppingItem(product);
                currentItems.add(shoppingItem);
                shoppingItems.add(shoppingItem);
            }
            cachedShoppingItems.put(mainCategory,currentItems);
        }
        return shoppingItems;
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


    /**
     * Laddar startsidan med samtliga produkter + preloadar alla produkter och lägger in de i en cache.
     */
    private void showAllItems(){
        List<ShoppingItem> shoppingItems = preloadShoppingItems();
        int i = 0;
        for (ShoppingItem item: shoppingItems) {

            grid.setConstraints(item, i%4, i/4);
            grid.getChildren().add(item);
            i++;
        }
    }

    //todo - change product category name - center
    @Override
    public void categoryChanged(MainCategory mainCategory) {
            System.out.println("category: " + mainCategory.toString() + " was clicked");
            updateGrid(mainCategory);
    }
}
