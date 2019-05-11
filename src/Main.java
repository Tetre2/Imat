import Model.IMat;
import Model.mainPage;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
        primaryStage.setTitle("Hello World");

        Group g = new Group();
        Scene scene = new Scene(g);
        g.getChildren().add(new mainPage());


        primaryStage.setScene(scene);
        primaryStage.show();



    }

    @Override
    public void stop(){
        IMat.getInstance().shutDown();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
