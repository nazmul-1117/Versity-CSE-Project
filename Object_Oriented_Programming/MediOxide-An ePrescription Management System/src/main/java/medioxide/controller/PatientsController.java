package medioxide.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import medioxide.databaseConnector.DatabaseConnector;
import medioxide.databaseConnector.InsertIntoPatients;
import medioxide.databaseConnector.SelectFromPatients;
import medioxide.helper.HelperFunctions;
import medioxide.model.ModelPatients;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientsController implements Initializable {
    public TextField searchPatientsTextField;
    public TextField addressTextField;
    public TextField ageTextField;
    public ToggleGroup tgGender;
    @FXML
    private TextField emailTextField;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField phoneTextField;

    @FXML
    private TextField surnameTextField;


    @FXML
    private TableColumn<ModelPatients, String> callGender;
    @FXML
    private TableColumn<ModelPatients, String> collAddress;
    @FXML
    private TableColumn<ModelPatients, Integer> collAge;
    @FXML
    private TableColumn<ModelPatients, Integer> collID;
    @FXML
    private TableColumn<ModelPatients, String> collName;
    @FXML
    private TableColumn<ModelPatients, String> collPhone;
    @FXML
    private TableView<ModelPatients> tableView;
    private final ObservableList<ModelPatients> patientsList = FXCollections.observableArrayList();


    private String name, surname, gender, phone, email, address;
    private int age;





    @FXML private void printName(ActionEvent event){
        String fName = firstNameTextField.getText();
        System.out.println(fName);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellTable();
        SelectFromPatients.showAllDataFromDatabase(patientsList, tableView);
    }
    void setCellTable() {
        try {
            collID.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
            collName.setCellValueFactory(new PropertyValueFactory<>("name"));
            collAge.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());

            callGender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            collPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
            collAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        }catch (Exception e){
            System.out.println("Set Column Error"+e.getMessage());
        }
    }

    public void searchButton(ActionEvent event) {
            String searchID = searchPatientsTextField.getText();
            tableView.getItems().clear();
            SelectFromPatients.searchByID(patientsList, tableView, searchID);
    }

    private void addPatientsCreateAccountDataCollect(){
        name = firstNameTextField.getText(); surname = surnameTextField.getText();
        age = HelperFunctions.stringToInt(ageTextField.getText());
        gender = ((JFXRadioButton)tgGender.getSelectedToggle()).getText();
        phone = phoneTextField.getText();
        email = emailTextField.getText();
        address = addressTextField.getText();
    }

    private void displayAllData(){
        System.out.println("First Name: " + name);
        System.out.println("Surname: " + surname);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }

    public void cancelButton(ActionEvent event) {
    }

    public void createButton(ActionEvent event) {
        addPatientsCreateAccountDataCollect();
        name = name + " " + surname;
        InsertIntoPatients.insertData(this.age, name, gender, phone, address);
        displayAllData();
        System.out.println("Data successfully inserted\nThank You....!");
    }
}
