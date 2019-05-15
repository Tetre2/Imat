package Model;

import Model.pages.Checkout.Checkout;
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

        setSceneToMainPage();
        window.show();

    }

    public static void setSceneToMinaSidor(){
        Group group = new Group();
        group.getChildren().add(minaSidor);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static void setSceneToHjalp(){
        Group group = new Group();
        group.getChildren().add(hjalp);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public static void setSceneToHistorik(){
        Group group = new Group();
        group.getChildren().add(historik);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }


    public static void setSceneToCheckout(){
        checkout.initUI();

        Group group = new Group();
        group.getChildren().add(checkout);
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
        Scene scene = new Scene(group);
        window.setScene(scene);
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
