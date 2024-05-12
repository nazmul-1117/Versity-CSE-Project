package medioxide.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import medioxide.java.Main;

import java.io.IOException;

public class HomeController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private String fxmlUrl;
    public void buttonSwitch(ActionEvent event) {
    }

    @FXML public void prescriptionButtonClicked(ActionEvent event){
        String clickedOption = event.getSource().toString();
        System.out.println("Prescription Button Clicked..!\n"+clickedOption);

        fxmlUrl = "prescription.fxml";
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlUrl));
        try {
            scene = new Scene(fxmlLoader.load());
        }catch (IOException ioException){
            System.out.println("FXML Load Fail\n"+ioException);
        }finally {
            stage.setScene(scene);
            stage.show();
        }

        System.out.println("prescription exicute");
    }
}
