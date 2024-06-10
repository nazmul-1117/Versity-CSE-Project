package medioxide.controller.mainController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import medioxide.controller.mainController.FXMLLoadToPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    public HBox topMenuBar;
    public VBox leftMenuBar;
    public JFXButton homeJFXButton;
    public JFXButton prescriptionJFXButton;
    public JFXButton doctorJFXButton;
    public JFXButton patientsJFXButton;
    public JFXButton testJFXButton;
    public JFXButton problemJFXButton;
    public JFXButton medicineJFXButton;

    public JFXButton minimizeButton;
    public JFXButton maximizeButton;
    public JFXButton closeButton;
    @FXML BorderPane mainDashboardBorderPane;
    public void setSceneToNewWindow(ActionEvent event, String fxmlFileName){
        System.out.println("You Clicked "+ fxmlFileName);
        FXMLLoadToPane fxmlLoader1 = new FXMLLoadToPane();
        Pane pane = fxmlLoader1.getPane(fxmlFileName);
        mainDashboardBorderPane.setCenter(pane);
    }

    private void setButtonColor(JFXButton buttonColor){
        homeJFXButton.setStyle("-fx-background-color: transparent");
        patientsJFXButton.setStyle("-fx-background-color: transparent");
        prescriptionJFXButton.setStyle("-fx-background-color: transparent");
        doctorJFXButton.setStyle("-fx-background-color: transparent");
        testJFXButton.setStyle("-fx-background-color: transparent");
        problemJFXButton.setStyle("-fx-background-color: transparent");
        medicineJFXButton.setStyle("-fx-background-color: transparent");

        buttonColor.setStyle("-fx-background-color: #00C9CF;"+"-fx-text-fill: #000000;");
    }

    public void leftMenubarButtonHandler(ActionEvent event) {

        setButtonColor((JFXButton) event.getSource());
        if (event.getSource().equals(homeJFXButton)){
            setSceneToNewWindow(event, "home.fxml");

        }else if (event.getSource().equals(prescriptionJFXButton)){
            setButtonColor(prescriptionJFXButton);
            setSceneToNewWindow(event, "prescription.fxml");

        }else if (event.getSource().equals(doctorJFXButton)){
            setSceneToNewWindow(event, "doctor.fxml");

        }else if (event.getSource().equals(patientsJFXButton)){
            setSceneToNewWindow(event, "patients.fxml");

        }else if (event.getSource().equals(testJFXButton)){
            setSceneToNewWindow(event, "test.fxml");

        }else if (event.getSource().equals(medicineJFXButton)){
            setSceneToNewWindow(event, "medicine.fxml");

        }else if (event.getSource().equals(problemJFXButton)){
            setSceneToNewWindow(event, "problem.fxml");

        }
    }

    public void titleBarButtonAction(ActionEvent event) {

        System.out.println("Button called for: " + event.toString());

        Stage stage1;

        if (event.getSource().equals(closeButton)){
            javafx.application.Platform.exit();
            System.out.println("System Closed");

        }else if (event.getSource().equals(minimizeButton)){
            stage1 = (Stage) ((Button)event.getSource()).getScene().getWindow();
            stage1.setIconified(true);
            System.out.println("System Minimize");

        }else if(event.getSource().equals(maximizeButton)){
            stage1 = (Stage) ((Button)event.getSource()).getScene().getWindow();

            stage1.setMaximized(!stage1.isMaximized());

            System.out.println("Maximized button");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}