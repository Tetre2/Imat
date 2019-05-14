package Model;

import javafx.scene.image.Image;
import se.chalmers.cse.dat216.project.*;

import java.util.ArrayList;
import java.util.List;

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

    public Image getImage(Product p){
        return dataHandler.getFXImage(p);
    }

    public void addFavorite(Product p){
        dataHandler.addFavorite(p);
    }

    public void addProduct(Product p){
        dataHandler.addProduct(p);
    }

    public List findProducts(String s){
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

    public void placeOrder(){
        dataHandler.placeOrder();
    }

    public void placeOrder(boolean clearCart){
        dataHandler.placeOrder(clearCart);
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
}
