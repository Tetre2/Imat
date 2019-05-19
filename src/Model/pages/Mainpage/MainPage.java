package Model.pages.Mainpage;

import Model.IMat;
import Model.components.LeftSidebar.LeftSidebar;
import Model.components.LeftSidebar.LeftSidebarCategory.CategoryListener;
import Model.components.LeftSidebar.LeftSidebarCategory.MainCategory;
import Model.components.Navbar.Navbar;
import Model.components.RightSidebar.RightSidebar;
import Model.components.ShoppingItem.ShoppingItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import se.chalmers.cse.dat216.project.Product;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPage extends AnchorPane implements CategoryListener {


    @FXML
    GridPane grid;
    @FXML
    AnchorPane LeftNavBar;
    @FXML
    AnchorPane varukorg;
    @FXML
    AnchorPane topNavBar;
    @FXML
    Label currentCategoryLabel;

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
        //topNavBar.getChildren().add(navbar);

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
    private Map<Product, ShoppingItem> cachedShoppingItems = new HashMap<>();


    /**
     * If a product already has a shoppingItem connected to itself - return that object
     * else - create a new shoppingItem from the selected product and then return that object.
     * @param product
     * @return a shopping item for the product
     */
    private ShoppingItem getShoppingItem(Product product){
        if(cachedShoppingItems.containsKey(product)){
            return cachedShoppingItems.get(product);
        }
        ShoppingItem shoppingItem = new ShoppingItem(product);
        cachedShoppingItems.put(product, shoppingItem);
        return shoppingItem;

    }

    /**
     *
     * @param mainCategory
     * @return a list of items that has mainCategory as "parent"
     */
    private List<ShoppingItem> getShoppingItems(MainCategory mainCategory){

        List<ShoppingItem> shoppingItems = new ArrayList<>();

        // Generate shopping items and store them in our Map as cache.
        for (int i = 0; i < mainCategory.getProducts().size(); i++) {
            ShoppingItem shoppingItem = getShoppingItem(mainCategory.getProducts().get(i));
            shoppingItems.add(shoppingItem);
        }

        return shoppingItems;
    }


    /**
     * This method shall only run once. It shall run when application is initialized.
     * @return
     */
    public List<ShoppingItem> preloadShoppingItems(){
        List<ShoppingItem> shoppingItems = new ArrayList<>();

        //Loop through each category and cache each item to the specific category
        for(Product product : IMat.getInstance().getProducts()) {
            //getShoppingItem - if shoppingItem has already been created - use the cached object, else - create new object and cache
            ShoppingItem shoppingItem = getShoppingItem(product);
            shoppingItems.add(shoppingItem);                                //shoppingItems is the list that will be returned

        }
        return shoppingItems;
    }

    /**
     * Update the "main grid" with shoppingItems from a selected category
     * @param mainCategory
     */
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

   /* private void favoriteGrid(){
        grid.getChildren().clear();
        System.out.println("test funktion");


        List<String> products = new ArrayList<>();

        for (int i = 0; i < IMat.getInstance().getFavorites().size(); i++){
            System.out.println("test in loop");
            products.add(IMat.getInstance().getFavorites().get(i).toString());
            System.out.println(products.get(i));

        }

    } */ //denna verkar inte hitta några items i IMat.getInstance().getFavorites().size() även om det finns faovriter.


    /**
     * Laddar startsidan med samtliga produkter + preloadar alla produkter och lägger in de i en cache.
     */
    public void showAllItems(){
        List<ShoppingItem> shoppingItems = preloadShoppingItems();
        int i = 0;
        for (ShoppingItem item: shoppingItems) {
            if(!grid.getChildren().contains(item)) {
                grid.setConstraints(item, i % 4, i / 4);
                grid.getChildren().add(item);
                i++;
            }
        }
    }

    @Override
    public void categoryChanged(MainCategory mainCategory) {
            System.out.println("category: " + mainCategory.toString() + " was clicked");
            updateGrid(mainCategory);
            currentCategoryLabel.setText(mainCategory.toString());
    }

    public void setNavBar(Navbar navBar){
        if(!topNavBar.getChildren().contains(navBar))
            topNavBar.getChildren().add(navBar);
    }
}
