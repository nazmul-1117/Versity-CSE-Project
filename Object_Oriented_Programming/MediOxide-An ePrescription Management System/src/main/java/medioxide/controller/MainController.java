package medioxide.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

    @FXML
    BorderPane mainDashboardBorderPane;
    @FXML
    private JFXButton switchToHome;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;
    private String fxmlUrl;

    public void setSceneToNewWindow(ActionEvent event, String fxmlFileName){
        System.out.println("You Clicked "+ fxmlFileName);
        FXMLLoadToPane fxmlLoader1 = new FXMLLoadToPane();
        Pane pane = fxmlLoader1.getPane(fxmlFileName);
        mainDashboardBorderPane.setCenter(pane);
    }
    public void leftMenubarButtonHandler(ActionEvent event) {

        if (event.getSource().equals(homeJFXButton)){
            setSceneToNewWindow(event, "home.fxml");

        }else if (event.getSource().equals(prescriptionJFXButton)){
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

            if (stage1.isMaximized()){
                stage1.setMaximized(false);
            }else {
                stage1.setMaximized(true);
            }

            System.out.println("Maximized button");

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}