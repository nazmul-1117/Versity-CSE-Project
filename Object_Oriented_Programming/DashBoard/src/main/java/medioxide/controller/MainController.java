package medioxide.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import medioxide.java.Main;

import java.io.IOException;

public class MainController {

    @FXML
    private JFXButton switchToHome;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private String fxmlUrl;

    public void switchToHome(ActionEvent event) {


        try {
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();

            fxmlUrl = "home.fxml";
            fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlUrl));
            scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setScene(scene);

            stage.show();
        }catch (IOException e){
            e.fillInStackTrace();
        }


    }
}