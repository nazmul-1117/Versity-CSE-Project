package medioxide.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import medioxide.components.DataTableListView;
import medioxide.databaseConnector.InsertIntoPatients;
import medioxide.databaseConnector.SelectFromPatients;
import medioxide.helper.HelperFunctions;
import medioxide.helper.OnClickListener;
import medioxide.model.ModelPatients;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientsController implements Initializable, OnClickListener {
    public TextField searchPatientsTextField;
    public TextField addressTextField;
    public TextField ageTextField;
    public JFXRadioButton rbMale;
    public JFXRadioButton rbFemale;
    public JFXRadioButton rbOther;
    public TextField removePatientsSearchTextField;
    public AnchorPane removePatientsAnchorPane;
    public VBox previousHistoryVBox;
    public JFXCheckBox previousHistoryCheckbox;
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
        removePatientsSearchButton();
    }

    private void initGenderToggle() {
        rbMale.setToggleGroup(genderToggleGroup);
        rbFemale.setToggleGroup(genderToggleGroup);
        rbOther.setToggleGroup(genderToggleGroup);
    }

    private void showSearchButton() {
        String query = "SELECT * FROM patients;";
        ObservableList<ModelPatients> patientsList = FXCollections.emptyObservableList();

        var table = new DataTableListView<ModelPatients>(patientsList, this);

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
        var table = new DataTableListView<ModelPatients>(patientsList, this);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        sap.getChildren().add(table);
    }


    public void searchButton(ActionEvent event) {

        List list;

        String searchID = searchPatientsTextField.getText().trim();

        try {
            int id = Integer.parseInt(searchID);
//            var list = SelectFromPatients.getPatientListById(searchID);
            list = SelectFromPatients.getPatientListById(id);
            System.out.println("search by id");
        } catch (Exception e) {
            list = SelectFromPatients.getPatientListByName(searchID);
//            var list = SelectFromPatients.getPatientListByName(searchID);
            System.out.println("search by Name");
        } finally {

        }

        searchPatientsTextField.clear();

        var patientsList = FXCollections.observableList(list);


        var table = new DataTableListView<ModelPatients>(patientsList, this);

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

        if (name == null || name.trim().isEmpty()){
            firstNameTextField.setTooltip(new Tooltip("jf"));
            firstNameTextField.setStyle("-fx-text-fill: red");
            firstNameTextField.setText("Please Input valid Name");

            System.out.println("name is empty");
        }
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

    public void removePatientsSearchButton() {
        List list;

        String searchID = removePatientsSearchTextField.getText().trim();
        try {
            int id = Integer.parseInt(searchID);
            list = SelectFromPatients.getPatientListById(id);
        } catch (Exception e) {
            list = SelectFromPatients.getPatientListByName(searchID);
        }
        removePatientsSearchTextField.clear();
        var patientsList = FXCollections.observableList(list);
        var table = new DataTableListView<ModelPatients>(patientsList, this);

        table.setLayoutX(0);
        table.setLayoutY(80);
        table.maxHeight(10.0);
//        AnchorPane.setBottomAnchor(table, 70.0);
//        AnchorPane.setTopAnchor(table, 80.0);
//        AnchorPane.setLeftAnchor(table, 0.0);
//        AnchorPane.setRightAnchor(table, 0.0);

        table.setPrefWidth(1366D);
        table.setPrefHeight(50D);
        removePatientsAnchorPane.getChildren().add(table);
    }

    @Override
    public void onDeleteClick(int id) {
        InsertIntoPatients.deleteData(id);
        //showAllButton();
    }

    public void previousHistoryCheckboxAction(ActionEvent event) {

        if(previousHistoryCheckbox.isSelected()){
            System.out.println("selected");
            previousHistoryVBox.setVisible(true);
        }else{
            System.out.println("not selected");
            previousHistoryVBox.setVisible(false);
        }
    }
}
