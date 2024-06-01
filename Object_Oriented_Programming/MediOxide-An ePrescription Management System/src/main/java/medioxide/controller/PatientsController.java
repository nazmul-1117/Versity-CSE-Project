package medioxide.controller;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import medioxide.components.DataTableListView;
import medioxide.databaseConnector.InsertIntoPatients;
import medioxide.databaseConnector.SelectFromPatients;
import medioxide.helper.HelperFunctions;
import medioxide.helper.OnClickListener;
import medioxide.model.ModelPatients;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PatientsController implements Initializable, OnClickListener {

    public TextField addNameTextField;
    public TextField addSurnameTextField;
    public TextField addAgeTextField;
    public TextField addPhoneTextField;
    public TextField addEmailTextField;
    public TextField addDeptTextField;
    public TextField addDiseasesTextField;
    public TextField addAddressTextField;
    public TextField addWeightTextField;
    public TextField addSystolicBPTextField;
    public TextField addDiastolicTextField;
    public TextField addBodyTempTextField;
    public TextField addFamilyProblemTextField;

    public JFXRadioButton rbMale;
    public JFXRadioButton rbFemale;
    public JFXRadioButton rbOther;
    public TextField removePatientsSearchTextField;
    public AnchorPane removePatientsAnchorPane;
    public VBox previousHistoryVBox;
    public JFXCheckBox previousHistoryCheckbox;
    public TextField modifySearchTextField;
    public Label yourID;
    public TextField searchPatientsTextField;


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
        System.out.println( addNameTextField.getText());
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

    private boolean dataCollect() {
        name = addNameTextField.getText();
        surname = addSurnameTextField.getText();
        age = HelperFunctions.stringToInt(addAgeTextField.getText());
        gender = ((JFXRadioButton) genderToggleGroup.getSelectedToggle()).getText();
        phone = addPhoneTextField.getText();
        email = addEmailTextField.getText();
        address = addAddressTextField.getText();
        System.out.println("data collected");

        if (name.trim().isEmpty()){
            addNameTextField.clear();
            addNameTextField.setStyle("-fx-prompt-text-fill: red");
            addNameTextField.setPromptText("Please Input valid Name");
            System.out.println("name is empty");
            return false;
        }

        return true;
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
        System.out.println("create button called");

        if (!dataCollect()){
            System.out.println("Data cannot be inserted");
            return;
        }

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

    public void modifySearchButton(ActionEvent event) {
        try {
            int id = Integer.parseInt(modifySearchTextField.getText().trim());

            if (SelectFromPatients.searchById(id))
                yourID.setText(Integer.toString(id));
            else
                yourID.setText("ID not found");

        }catch (Exception e){
            yourID.setText("Enter Valid input");
        }

    }
}
