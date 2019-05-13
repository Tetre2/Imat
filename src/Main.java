import Model.Checkout.Checkout;
import Model.IMat;
import Model.Mainpage.MainPage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class Main extends Application {

    private Stage window;

    private MainPage mainPage;
    private Checkout checkout;


    @Override
    public void start(Stage primaryStage) throws Exception{

        window = primaryStage;

        IMatDataHandler.getInstance().reset();
        primaryStage.setTitle("IMat");

        mainPage = new MainPage();
        checkout = new Checkout();

        setSceneToMainPage();
        window.show();


    }

    public void setSceneToCheckout(){
        Group group = new Group();
        group.getChildren().add(checkout);
        Scene scene = new Scene(group);
        window.setScene(scene);
    }

    public void setSceneToMainPage(){
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
