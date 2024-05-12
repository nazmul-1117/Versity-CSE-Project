package medioxide.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medioxide.java.Main;

import java.io.IOException;

public class PrescriptionController {
     private Stage stage;
     private Scene scene;
     private FXMLLoader fxmlLoader;
     private String fxmlUrl;
     @FXML public void homeButtonClicked(ActionEvent event) throws IOException {
        String clickedOption = event.toString();
        System.out.println("home Button Clicked..!\n"+clickedOption);

        fxmlUrl = "home.fxml";
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlUrl));
        scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);

        stage.show();
    }
}
