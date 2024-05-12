package medioxide.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image("file:src/main/picture/icon.png");

        String fxmlUrl = "home.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlUrl));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.getIcons().add(icon);
        stage.setTitle("MediOxide!");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}