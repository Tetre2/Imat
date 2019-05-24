package Model.pages.Mainpage;

import Model.IMat;
import Model.components.LeftSidebar.LeftSidebar;
import Model.components.LeftSidebar.LeftSidebarCategory.CategoryListener;
import Model.components.LeftSidebar.LeftSidebarCategory.MainCategory;
import Model.components.Navbar.Navbar;
import Model.components.RightSidebar.RightSidebar;
import Model.components.ShoppingItem.ShoppingItem;
import Model.components.ShoppingItem.ShoppingItemListener;
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

public class MainPage extends AnchorPane implements CategoryListener, ShoppingItemListener {


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
    @FXML
    AnchorPane noFavorites;

    private IMat iMat;
    private LeftSidebar leftSidebar;


    public MainPage(){
        iMat = IMat.getInstance();
        FXMLLoader fxmlLoader = initFXML();
        tryToLoadFXML(fxmlLoader);

        //visa alla produkter
        //showAllItems();
        //preloadShoppingItems();
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

    public void clearAllProducts() {
        cachedShoppingItems.clear();
        grid.getChildren().clear();
    }

    /**
     * If a product already has a shoppingItem connected to itself - return that object
     * else - create a new shoppingItem from the selected product and then return that object.
     * @param product
     * @return a shopping item for the product
     */
    public ShoppingItem getShoppingItem(Product product){
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
            shoppingItem.addShoppingItemListener(this);
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
    public void updateGrid(MainCategory mainCategory, String currentCatigory){
        //Get shopping items, either from cache or generate from scratch.
        List<ShoppingItem> shoppingItems = getShoppingItems(mainCategory);

        //Display shopping items
        showShoppingItemToGrid(shoppingItems, currentCatigory);
    }

    public void showProductsToGrid(List<Product> products, String currentCatigory){
        leftSidebar.resetCategoryFocused();
        ArrayList<ShoppingItem> arr = new ArrayList<>();

        for (Product p : products) {//Hämtar shoppingitems för produkterna
            arr.add(getShoppingItem(p));
        }
            showShoppingItemToGrid(arr, currentCatigory);
    }


    private void showShoppingItemToGrid(List<ShoppingItem> arr, String currentCatigory){
        //Clear current grid of shoppingitems
        grid.getChildren().clear();
        currentCategoryLabel.setText(currentCatigory);

        //Display shopping items
        for(int i = 0; i < arr.size(); i++) {
            grid.setConstraints(arr.get(i), i % 4, i / 4);
            grid.getChildren().add(arr.get(i));
        }

        if(grid.getChildren().size() == 0 && leftSidebar.getCurrentCategory() != null && leftSidebar.getCurrentCategory().equals(MainCategory.FAVORIT)){
            noFavorites.setVisible(true);
        }else {
            noFavorites.setVisible(false);
        }

    }

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
        currentCategoryLabel.setText("Alla varor");
    }

    @Override
    public void categoryChanged(MainCategory mainCategory) {
            System.out.println("category: " + mainCategory.toString() + " was clicked");
            updateGrid(mainCategory, mainCategory.toString());
            //currentCategoryLabel.setText(mainCategory.toString());
    }

    public void setNavBar(Navbar navBar){
        if(!topNavBar.getChildren().contains(navBar))
            topNavBar.getChildren().add(navBar);
    }

    @Override
    public void shoppingItemClicked(ShoppingItem shoppingItem) {
        if(leftSidebar.getCurrentCategory() == MainCategory.FAVORIT){//ifall vi befinner oss på favoritsidan och tar bot ett item ska sidan uppdateras
            updateGrid(leftSidebar.getCurrentCategory(), leftSidebar.getCurrentCategory().toString());
        }
    }

    public LeftSidebar getLeftSidebar() {
        return leftSidebar;
    }
}
