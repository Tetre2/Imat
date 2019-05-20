package Model;

import Model.components.LeftSidebar.LeftSidebar;
import Model.components.LeftSidebar.LeftSidebarCategory.MainCategory;
import Model.components.Navbar.Navbar;
import Model.pages.Checkout.Checkout;
import Model.pages.Favoriter.Favoriter;
import Model.pages.Historik.Historik;
import Model.pages.Hjalp.Hjalp;
import Model.pages.Mainpage.MainPage;
import Model.pages.MinaSidor.MinaSidor;
import Model.pages.Testing.Testing;
import Model.pages.Testing2.Testing2;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class Main extends Application {

    private static Stage window;

    private static MainPage mainPage;
    private static Checkout checkout;
    private static Testing testing;
    private static Testing2 testing2;
    private static Historik historik;
    private static MinaSidor minaSidor;
    private static Hjalp hjalp;
    private static Favoriter favoriter;

    private static Navbar navbar;


    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        //IMatDataHandler.getInstance().reset();
        primaryStage.setTitle("IMat");

        mainPage = new MainPage();
        checkout = new Checkout();
        testing = new Testing();
        testing2 = new Testing2();
        historik = new Historik();
        minaSidor = new MinaSidor();
        hjalp = new Hjalp();
        favoriter = new Favoriter();

        navbar = new Navbar();

        if(IMat.getInstance().isFirstRun()){
            navbar.goToHjalp();
        }else {
            navbar.goToMainPage();
        }


        window.show();

    }

   /* public static void setShowAll(){
        mainPage.showAllItems();
    }
    */

    public static void changeCategory(MainCategory mc){
        mainPage.categoryChanged(mc);
    }



    public static void setSceneToMinaSidor(){
        Group group = new Group();
        minaSidor = new MinaSidor();
        group.getChildren().add(minaSidor);
        minaSidor.setNavBar(navbar);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static void setSceneToFavoriter(){
        Group group = new Group();
        group.getChildren().add(favoriter);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static void setSceneToHjalp(){
        Group group = new Group();
        group.getChildren().add(hjalp);
        hjalp.setNavBar(navbar);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static void setSceneToHistorik(){
        Group group = new Group();
        group.getChildren().add(historik);
        historik.setNavBar(navbar);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }


    public static void setSceneToCheckout(){
        Group group = new Group();
        checkout = new Checkout();
        group.getChildren().add(checkout);
        checkout.setNavBar(navbar);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static void setSceneToTesting(){
        Group group = new Group();
        group.getChildren().add(testing);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static void setSceneToTesting2(){
        Group group = new Group();
        group.getChildren().add(testing2);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static void setSceneToMainPage(){
        Group group = new Group();
        group.getChildren().add(mainPage);
        mainPage.setNavBar(navbar);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static Historik getHistorik() {
        return historik;
    }

    public static Navbar getNavbar() {
        return navbar;
    }

    public static MainPage getMainPage(){
        return mainPage;
    }

    public static Scene getCurrentScene(){
        return window.getScene();
    }



    @Override
    public void stop(){
        IMat.getInstance().shutDown();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
