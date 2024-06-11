package medioxide.controller.mainController;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medioxide.controller.mainController.FXMLLoadToPane;
import medioxide.java.Main;

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

    private void setScene(ActionEvent event){
        try {
            Image icon = new Image("file:src/main/picture/icon.png");

            Stage stage;
            FXMLLoader fxmlLoader;
            Scene scene;

            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.close();
            stage = new Stage();

            String fxmlName = "login.fxml";
            URL fxmlURL = Main.class.getResource(fxmlName);

            fxmlLoader = new FXMLLoader(fxmlURL);
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            stage.getIcons().add(icon);
            stage.setTitle("MediOxide!");

//            stage.setMaximized(true);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setResizable(true);
            stage.show();

            System.out.println("scene changes");
        }catch (Exception e){
            System.out.println("Scene Load Failed");
        }
    }

    public void logOutButton(ActionEvent event) {
        System.out.println("Logout Button Clicked");
        setScene(event);
        System.out.println("Logout Done");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}