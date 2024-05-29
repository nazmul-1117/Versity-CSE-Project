package medioxide.java;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medioxide.controller.FXMLLoadToPane;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Image icon = new Image("file:src/main/picture/icon.png");

        String fxmlUrl = "dashboard.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlUrl));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        stage.getIcons().add(icon);
        stage.setTitle("MediOxide!");

        stage.setScene(scene);
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setResizable(true);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}