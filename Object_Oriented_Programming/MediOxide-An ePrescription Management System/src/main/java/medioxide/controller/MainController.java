package medioxide.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import medioxide.java.Main;

import java.io.IOException;

public class MainController {
    public HBox topMenuBar;
    public VBox leftMenuBar;
    public JFXButton homeJFXButton;
    public JFXButton prescriptionJFXButton;
    public JFXButton doctorJFXButton;
    public JFXButton patientsJFXButton;
    public JFXButton testJFXButton;
    public JFXButton dragJFXButton;
    public JFXButton problemJFXButton;
    public JFXButton medicineJFXButton;

    @FXML
    BorderPane mainDashboardBorderPane;
    @FXML
    private JFXButton switchToHome;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private String fxmlUrl;

    public void setSceneToNewWindow(ActionEvent event, String fxmlFileName){
        System.out.println("you clicked "+ fxmlFileName);
        FXMLLoadToPane fxmlLoader1 = new FXMLLoadToPane();
        Pane pane = fxmlLoader1.getPane(fxmlFileName);
        mainDashboardBorderPane.setCenter(pane);
    }
    public void leftMenubarButtonHandler(ActionEvent event) {

        if (event.getSource().equals(homeJFXButton)){
            setSceneToNewWindow(event, "home.fxml");

        }else if (event.getSource().equals(prescriptionJFXButton)){
            setSceneToNewWindow(event, "blankScene.fxml");

        }else if (event.getSource().equals(doctorJFXButton)){
            setSceneToNewWindow(event, "home.fxml");

        }else if (event.getSource().equals(patientsJFXButton)){
            setSceneToNewWindow(event, "home.fxml");

        }else if (event.getSource().equals(testJFXButton)){
            setSceneToNewWindow(event, "home.fxml");

        }else if (event.getSource().equals(dragJFXButton)){
            setSceneToNewWindow(event, "home.fxml");

        }else if (event.getSource().equals(problemJFXButton)){
            setSceneToNewWindow(event, "home.fxml");

        }
    }

    public void buttonSwitch(ActionEvent event) {
    }
}