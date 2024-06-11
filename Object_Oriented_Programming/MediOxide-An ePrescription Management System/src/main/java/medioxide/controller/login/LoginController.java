package medioxide.controller.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medioxide.java.Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Button createNewAccount;
    private Stage stage;
    private Scene scene;
    private FXMLLoader fxmlLoader;

    public Label loginMessage;
    @FXML
    private TextField userNameField, passwordField;
    @FXML
    protected VBox loginVBox;
    @FXML
    protected Region signinPicture;
    private String userName, password;
    @FXML
    protected void cancelButton(ActionEvent event){

        System.out.println("Cancelled");
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void setScene(ActionEvent event){
        try {
                Image icon = new Image("file:src/main/picture/icon.png");
                String fxmlName = "dashboard.fxml";
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                stage.close();
                stage = new Stage();

                URL fxmlURL = Main.class.getResource(fxmlName);

                fxmlLoader = new FXMLLoader(fxmlURL);
                scene = new Scene(fxmlLoader.load());
                stage.setScene(scene);

                stage.getIcons().add(icon);
                stage.setTitle("MediOxide!");

                stage.setMaximized(true);
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setResizable(true);
                stage.show();

                System.out.println("scene changes");
        }catch (Exception e){
            System.out.println("Scene Load Failed");
        }
    }
    @FXML
    protected void submitButton(ActionEvent event) {

        userName = userNameField.getText();
        password = passwordField.getText();



        if (userNameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            loginMessage.setText("Enter Valid Username or Password");
            loginMessage.setStyle("-fx-text-fill: red");

            System.out.println("Null Value");
            return;
        }
        passwordField.clear(); userNameField.clear();

        if (userName.equals(password)) {
            setScene(event);

        }else {
            loginMessage.setText("Enter Valid Username or Password");
            loginMessage.setStyle("-fx-text-fill: red");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginVBox.setFocusTraversable(true);
    }
    public void createNewAccount(ActionEvent event) throws IOException {
        System.out.println("This window is empty");

    }
}
