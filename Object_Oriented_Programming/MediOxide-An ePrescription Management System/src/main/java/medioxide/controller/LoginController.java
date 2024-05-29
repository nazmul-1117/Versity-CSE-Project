package medioxide.controller;

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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    }

    @FXML
    protected void submitButton(ActionEvent event) throws IOException {

        userName = userNameField.getText();
        password = passwordField.getText();

        if (userNameField.getText().isEmpty() || passwordField.getText().isEmpty()){
            loginMessage.setText("Enter Valid Username or Password");
            loginMessage.setStyle("-fx-text-fill: red");

            System.out.println("Null Value");
            return;
        }
        passwordField.clear(); userNameField.clear();

        if(userName.equals(password)){
            String fxmlName = "dashboard.fxml";
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();

            fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);

            System.out.println("scene changes");
        }else{
            System.out.println("wrong password");
        }

        System.out.println(fxmlLoader.toString());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginVBox.setFocusTraversable(true);
    }
    public void createNewAccount(ActionEvent event) throws IOException {
        String fxmlName = "create-Account.fxml";

        fxmlLoader = new FXMLLoader(getClass().getResource(fxmlName));
        Parent root = fxmlLoader.load();
        scene = new Scene(root);

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);

        System.out.println("Scene Created");

    }
}
