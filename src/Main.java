
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
       FXMLLoader loader=new FXMLLoader(getClass().getResource("main.fxml"));
       Scene scene=new Scene(loader.load(),1200,800);
       primaryStage.setTitle("Data Visualization Tool");
       primaryStage.setScene(scene);
       primaryStage.setMaximized(true);
       primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
