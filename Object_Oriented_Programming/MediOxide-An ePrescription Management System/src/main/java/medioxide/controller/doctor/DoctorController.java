package medioxide.controller.doctor;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medioxide.components.DoctorDataTableView;
import medioxide.components.PatientsDataTableListView;
import medioxide.controller.patients.PatientsModifyController;
import medioxide.database.DoctorDBTable;
import medioxide.database.PatientsDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.helper.OnClickListener;
import medioxide.java.Main;
import medioxide.model.doctor.DoctorMainModel;
import medioxide.model.doctor.DoctorTableViewModel;
import medioxide.model.patients.PatientsModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DoctorController implements Initializable, OnClickListener {

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
    public AnchorPane showAllDoctor;


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

        String licence = addLicenceTextField.getText();
        String hospital = addHospitalTextField.getText();
        String dept = addDeptComboBox.getSelectionModel().getSelectedItem().toString();

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

    private void showAllDoctorListData() {
        var list =DoctorDBTable.getAllDoctorList();
        var doctorList = FXCollections.observableList(list);
        var table = new DoctorDataTableView<DoctorTableViewModel>(doctorList, this);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        showAllDoctor.getChildren().add(table);
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
    public void onDeleteClick(int id) {
        System.out.println("Delete Clicked for ID: " + id);
    }

    @Override
    public void onEditClick(int id) {
        System.out.println("Edit Clicked for ID: " + id);
        try {
            var list = DoctorDBTable.getModifyDoctorListById(id);

            if (!list.isEmpty()){
                var model = list.get(0);

                String fxml = "modify_doctor.fxml";
                URL fxmlURL = Main.class.getResource(fxml);
//                System.out.println("URL: " + fxmlURL);

                FXMLLoader fxmlLoader  = new FXMLLoader(fxmlURL);
                Scene scene = new Scene(fxmlLoader.load());

                DoctorModifyController dmc = fxmlLoader.getController();
                dmc.setModel(model);

                Stage stage = new Stage();
                stage.setScene(scene);

                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }

        }catch (IOException ioException){
            System.out.println("modify_patients.fxml failed");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
        initGenderToggle();
        showAllDoctorListData();

    }
}
