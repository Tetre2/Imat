package Model;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IMat {

    private IMatDataHandler dataHandler;
    private static IMat instance = null;


    public static IMat getInstance() {
        if (instance == null) {
            instance = new IMat();
            instance.init();
        }

        return instance;
    }


    private IMat() {

    }

    private void init(){
        dataHandler = IMatDataHandler.getInstance();
        ArrayList arr = new ArrayList();
        for (Product p: getProducts()) {
            arr.add(p);
        }

        System.out.println("Products loaded: " + arr.size());
        System.out.println(getOrders().size());


    }


    public void setSceneToMainPage(){
        Main.setSceneToMainPage();
    }

    public void setSceneToCheckout(){
        Main.setSceneToCheckout();
    }

    public void setSceneToHelp(){
        Main.setSceneToHjalp();
    }

    Map<Product, Image> images = new HashMap<>();


    public Image getImage(Product p){
        if(images.containsKey(p)){
            return images.get(p);
        }
        Image image = dataHandler.getFXImage(p);
        images.put(p, image);
        return image;
    }

    public void addFavorite(Product p){
        dataHandler.addFavorite(p);
    }

    public void addProduct(Product p){
        dataHandler.addProduct(p);
    }

    public List<Product> findProducts(String s){
        return dataHandler.findProducts(s);
    }

    public CreditCard getCreditCard(){
        return dataHandler.getCreditCard();
    }

    public Customer getCustomer(){
        return dataHandler.getCustomer();
    }

    public List<Order> getOrders(){
        return dataHandler.getOrders();
    }

    public List<Product> getProducts(ProductCategory pc){
        return dataHandler.getProducts(pc);
    }

    public List<Product> getProducts(){
        return dataHandler.getProducts();
    }

    public Product getProduct(int idNbr){
        return dataHandler.getProduct(idNbr);
    }

    public ShoppingCart getShoppingCart(){
        return dataHandler.getShoppingCart();
    }

    public boolean shoppingCartContainsProduct(Product product){
        for(ShoppingItem addedItem : getShoppingCartItems()){
            if(addedItem.getProduct().getName().equals(product.getName())){
                return true;
            }
        }
        return false;
    }

    public ShoppingItem getShoppingCartItem(Product product){
        for(ShoppingItem addedItem : getShoppingCartItems()){
            if(addedItem.getProduct().getName().equals(product.getName())){
                return addedItem;
            }
        }
        return new ShoppingItem(product);
    }

    public boolean isShoppingCartEmpty() {
        return dataHandler.getShoppingCart().getItems().size() == 0;
    }

    public User getUser(){
        return dataHandler.getUser();
    }

    public void removeFavorite(Product p){
        dataHandler.removeFavorite(p);
    }

    public void removeShopingCartItem(ShoppingItem sp){
        dataHandler.getShoppingCart().removeItem(sp);
    }

    public List<ShoppingItem> getShoppingCartItems(){
        return dataHandler.getShoppingCart().getItems();
    }

    public boolean hasImage(Product p){
        return dataHandler.hasImage(p);
    }

    public boolean isCustomerComplete(){
        return dataHandler.isCustomerComplete();
    }

    public boolean isFavorite(Product p){
        return dataHandler.isFavorite(p);
    }

    public Order placeOrder(){
        return dataHandler.placeOrder(true);

        /*for (int i = 0; i < dataHandler.getShoppingCart().getItems().size(); i++) {
            ShoppingItem shoppingItem = dataHandler.getShoppingCart().getItems().get(i);
            shoppingItem.setAmount(0);
            dataHandler.getShoppingCart().fireShoppingCartChanged(shoppingItem, true);
        }
*/
    }

    public boolean isFirstRun(){
        return dataHandler.isFirstRun();
    }

    public void shutDown(){
        dataHandler.shutDown();
    }

    public List<Product> getFavorites(){
        return dataHandler.favorites();
    }

    public IMatDataHandler getDataHandler() {
        return dataHandler;
    }

    public boolean favoritesContainsProduct(Product product) {
        for(Product favorite : getFavorites()){
            if(favorite.getName().equals(product.getName())) {
                return true;
            }
        }
        return false;
    }
}
