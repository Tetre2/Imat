import Model.Checkout.Checkout;
import Model.IMat;
import Model.Mainpage.MainPage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class Main extends Application {

    private MainPage mainPage;
    private Checkout checkout;

    @Override
    public void start(Stage primaryStage) throws Exception{

        IMatDataHandler.getInstance().reset();
        primaryStage.setTitle("IMat");

        mainPage = new MainPage();
        checkout = new Checkout();


        Group g = new Group();
        Scene scene = new Scene(g);
        g.getChildren().add(mainPage);


        primaryStage.setScene(scene);
        primaryStage.show();



        System.out.println("here");

    }






    @Override
    public void stop(){
        IMat.getInstance().shutDown();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
