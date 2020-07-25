package weather;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.IOException;

public class Main extends Application {
    Scene scene;
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = loadFXML("view/Screen");
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });
        scene = new Scene(root);
        primaryStage.setTitle("Weather App");
        JMetro Jmetro = new JMetro(Style.LIGHT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Jmetro.setScene(scene);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private Parent loadFXML(String fxml) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxml+".fxml"));
        return loader.load();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
