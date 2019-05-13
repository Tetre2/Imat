package Model;

import Model.Checkout.Checkout;
import Model.Mainpage.MainPage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class Main extends Application {

    private static Stage window;

    private static MainPage mainPage;
    private static Checkout checkout;


    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        IMatDataHandler.getInstance().reset();
        primaryStage.setTitle("Model.IMat");

        mainPage = new MainPage();
        checkout = new Checkout();

        setSceneToMainPage();
        window.show();


    }

    public static void setSceneToCheckout(){
        Group group = new Group();
        group.getChildren().add(checkout);
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
