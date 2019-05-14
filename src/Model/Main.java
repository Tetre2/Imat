package Model;

import Model.Checkout.Checkout;
import Model.Mainpage.MainPage;
import Model.Testing.Testing;
import Model.Testing2.Testing2;
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


    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        IMatDataHandler.getInstance().reset();
        primaryStage.setTitle("IMat");

        mainPage = new MainPage();
        checkout = new Checkout();
        testing = new Testing();
        testing2 = new Testing2();

        setSceneToTesting2();
        window.show();


    }

    public static void setSceneToCheckout(){
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




    @Override
    public void stop(){
        IMat.getInstance().shutDown();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
