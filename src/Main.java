import Model.IMat;
import Model.Mainpage.mainPage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import se.chalmers.cse.dat216.project.IMatDataHandler;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        IMatDataHandler.getInstance().reset();

        //Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setTitle("Hello World");

        Group g = new Group();
        Scene scene = new Scene(g);
        g.getChildren().add(new mainPage());


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
