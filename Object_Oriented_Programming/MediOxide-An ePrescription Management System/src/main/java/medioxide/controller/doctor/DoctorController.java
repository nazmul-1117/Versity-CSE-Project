package medioxide.controller.doctor;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import medioxide.database.DoctorDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.model.doctor.DoctorMainModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DoctorController implements Initializable {

    public TextField addNameTextField;
    public TextField addSurnameTextField;
    public JFXRadioButton rbMale;
    public JFXRadioButton rbFemale;
    public TextField addPhoneTextField;
    public TextField adEmailTextField;
    public TextField addNIDTextField;

    public TextField addLicenceTextField;
    public TextField addHospitalTextField;
    public TextField addSpecialityTextField;
    public JFXComboBox addDeptComboBox;
    public TextField addRoomTextField;
    public TextField addExperienceTextField;
    public JFXTextArea addDegreeTextField;

    public TextField searchDoctorTextField;


    private ToggleGroup genderToggleGroup = new ToggleGroup();
    private DoctorMainModel model;




    private void initGenderToggle() {
        rbMale.setToggleGroup(genderToggleGroup);
        rbFemale.setToggleGroup(genderToggleGroup);
    }
    private void initComboBox(){
        ObservableList<String> list = FXCollections.observableList(new ArrayList<>()) ;
        list.add("Burn Plastic Surgery");
        list.add("Cardiology & CCU");
        list.add("Gastroenterology");
        list.add("Gynae & Obstetrics");
        list.add("Medicine");
        list.add("Neurology");
        list.add("Orthopedic Surgery");
        list.add("Pediatrics");
        list.add("Psychiatry");
        list.add("Radiology & Imaging");

        //comboBox.setItems(list);
        addDeptComboBox.setItems(list);
    }


    private void collectData(){

        String name = addNameTextField.getText();
        String surname = addSurnameTextField.getText();
        String gender = ((JFXRadioButton) genderToggleGroup.getSelectedToggle()).getText();;

        String phone = addPhoneTextField.getText();
        String email = adEmailTextField.getText();
        int nid = HelperFunctions.stringToInt(addNIDTextField.getText());

        String licence = addLicenceTextField.getId();
        String hospital = addHospitalTextField.getText();
        String dept = addDeptComboBox.getSelectionModel().getSelectedItem().toString();;

        String speciality = addSpecialityTextField.getText();
        String room = addRoomTextField.getText();
        String degree = addDegreeTextField.getText();
        int experience = HelperFunctions.stringToInt(addExperienceTextField.getText());

        model = new DoctorMainModel(name, surname, gender, phone, email, nid, licence, hospital, dept, speciality, room, degree, experience);

    }
    private void consoleShowAllData(){
        System.out.println("Name: " + model.getName());
        System.out.println("Gender: " + model.getGender());
        System.out.println("Dept: " + model.getDept());
    }


    public void saveButton(ActionEvent event) {
        System.out.println("Save Button");

        collectData();
        consoleShowAllData();
        DoctorDBTable.insertDataIntoDoctor(model);
        System.out.println("Data successfully inserted");
    }
    public void cancelButton(ActionEvent event) {
        System.out.println("Cancel Button");
    }
    public void searchButton(ActionEvent event) {
        System.out.println("Search Button");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
        initGenderToggle();

    }
}
