package medioxide.controller;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import medioxide.components.DataTableListView;
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
    public JFXRadioButton rbMale;
    public JFXRadioButton rbFemale;
    public JFXRadioButton rbOther;
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

    private String name, surname, gender, phone, email, address;
    private int age;

    @FXML
    private AnchorPane s;

    @FXML
    private AnchorPane sap;

    private final ToggleGroup genderToggleGroup = new ToggleGroup();

    @FXML
    private void printName(ActionEvent event) {
        String fName = firstNameTextField.getText();
        System.out.println(fName);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        showAllButton();

        showSearchButton();

        initGenderToggle();

    }

    private void initGenderToggle() {
        rbMale.setToggleGroup(genderToggleGroup);
        rbFemale.setToggleGroup(genderToggleGroup);
        rbOther.setToggleGroup(genderToggleGroup);

    }

    private void showSearchButton() {
        String query = "SELECT * FROM patients;";
        ObservableList<ModelPatients> patientsList = FXCollections.emptyObservableList();

        var table = new DataTableListView<ModelPatients>(patientsList);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 80.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        s.getChildren().add(table);
    }

    private void showAllButton() {
        String query = "SELECT * FROM patients;";
        var list = SelectFromPatients.getAllPatientList();
        var patientsList = FXCollections.observableList(list);
        var table = new DataTableListView<ModelPatients>(patientsList);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        sap.getChildren().add(table);
    }


    public void searchButton(ActionEvent event) {
        String searchID = searchPatientsTextField.getText();
        searchPatientsTextField.clear();
        var list = SelectFromPatients.getPatientListById(searchID);
        var patientsList = FXCollections.observableList(list);


        var table = new DataTableListView<ModelPatients>(patientsList);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 80.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        s.getChildren().add(table);
    }

    private void addPatientsCreateAccountDataCollect() {
        name = firstNameTextField.getText();
        surname = surnameTextField.getText();
        age = HelperFunctions.stringToInt(ageTextField.getText());
        gender = ((JFXRadioButton) genderToggleGroup.getSelectedToggle()).getText();
        phone = phoneTextField.getText();
        email = emailTextField.getText();
        address = addressTextField.getText();
    }

    private void displayAllData() {
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
