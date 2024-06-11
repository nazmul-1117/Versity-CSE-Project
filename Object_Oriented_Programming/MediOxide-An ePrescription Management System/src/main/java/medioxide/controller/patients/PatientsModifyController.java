package medioxide.controller.patients;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medioxide.database.PatientsDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.java.Main;
import medioxide.model.patients.PatientsModifyModel;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientsModifyController implements Initializable {

    public TextField modifyNameTextField;
    public TextField modifySurnameTextField;
    public TextField modifyAgeTextField;
    public TextField modifyAddressTextField;
    public TextField modifyPhoneTextField;
    public TextField modifyEmailTextField;

    public JFXRadioButton modifyRBOther;
    public JFXRadioButton modifyRBFemale;
    public JFXRadioButton modifyRBMale;

    private PatientsModifyModel patientsModel;
    private String name, surname, gender, phone, email, address;
    private int age;


    private ToggleGroup genderToggleGroup = new ToggleGroup();
    public PatientsModifyController() {}
    public void setModelPatients(PatientsModifyModel patientsModel) {
        this.patientsModel = patientsModel;

        setData();
    }
    private void  setData(){
        modifyNameTextField.setText(patientsModel.getName());
        modifySurnameTextField.setText(patientsModel.getSurname());
        modifyAgeTextField.setText(Integer.toString(patientsModel.getAge()));

        modifyPhoneTextField.setText(patientsModel.getPhone());
        modifyEmailTextField.setText(patientsModel.getEmail());
        modifyAddressTextField.setText(patientsModel.getAddress());

        switch (patientsModel.getGender().toLowerCase()){
            case "male":
                modifyRBMale.setSelected(true);
                break;

            case "female":
                modifyRBFemale.setSelected(true);
                break;

            default:
                modifyRBOther.setSelected(true);
        }
    }
    private void initGenderToggle() {
        modifyRBOther.setToggleGroup(genderToggleGroup);
        modifyRBFemale.setToggleGroup(genderToggleGroup);
        modifyRBMale.setToggleGroup(genderToggleGroup);
    }
    private void  collectData(){
        name = modifyNameTextField.getText();
        surname = modifySurnameTextField.getText();
        age = HelperFunctions.stringToInt(modifyAgeTextField.getText());
        gender = ((JFXRadioButton) genderToggleGroup.getSelectedToggle()).getText();

        phone = modifyPhoneTextField.getText();
        email = modifyEmailTextField.getText();
        address = modifyAddressTextField.getText();
    }

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void saveChangeButton(ActionEvent event) {
        collectData();
        PatientsDBTable.updatePatientsIntoDatabase(patientsModel.getId(), age, name, surname, gender, phone, email, address);

        cancelButton(event);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGenderToggle();
    }
}
