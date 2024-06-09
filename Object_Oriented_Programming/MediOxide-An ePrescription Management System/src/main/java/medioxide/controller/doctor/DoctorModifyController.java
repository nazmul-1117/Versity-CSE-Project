package medioxide.controller.doctor;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import medioxide.database.DoctorDBTable;
import medioxide.model.doctor.DoctorModifyModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DoctorModifyController implements Initializable {

    public TextField modifyNameTextField;
    public TextField modifyPhoneTextField;
    public TextField modifyEmailTextField;
    public TextField modifySpecialityTextField;
    public TextField modifyRoomNoTextField;
    public TextField modifyDegreeTextField;
    public JFXComboBox modifyDeptComboBox;

    private DoctorModifyModel model;



    public void setModel(DoctorModifyModel model) {
        this.model = model;

        setData();
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
        modifyDeptComboBox.setItems(list);
    }

    private void setData(){
        modifyNameTextField.setText(model.getName());
        modifyPhoneTextField.setText(model.getPhone());
        modifyEmailTextField.setText(model.getEmail());
//        modifyDeptComboBox
        modifySpecialityTextField.setText(model.getSpeciality());
        modifyRoomNoTextField.setText(model.getRoomNo());
        modifyDegreeTextField.setText(model.getDegree());
    }

    private void dataCollect(){
        int id = model.getId();
        String name = modifyNameTextField.getText();
        String phone = modifyPhoneTextField.getText();
        String email = modifyEmailTextField.getText();
        String dept = modifyDeptComboBox.getSelectionModel().getSelectedItem().toString();

        String speciality = modifySpecialityTextField.getText();
        String roomNo = modifyRoomNoTextField.getText();
        String degree = modifyDegreeTextField.getText();

        model = new DoctorModifyModel(id, name, phone, email, dept, speciality, roomNo, degree);
    }

    private void consoleShowAllData(){
        System.out.println("Name: " + model.getName());
        System.out.println("Phone: " + model.getPhone());
        System.out.println("Email: " + model.getEmail());
    }

    public void saveButton(ActionEvent event) {
        dataCollect();
        consoleShowAllData();
        DoctorDBTable.updateDoctorData(model);
        System.out.println("Saved Changed");
    }
    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();

    }

}
