package medioxide.controller.patients;

import com.jfoenix.controls.JFXRadioButton;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import medioxide.database.InsertIntoPatients;
import medioxide.database.SelectFromPatients;
import medioxide.helper.HelperFunctions;

import java.net.URL;
import java.util.ResourceBundle;

public class ModifyPatientsController implements Initializable {

    public TextField modifyNameTextField;
    public TextField modifySurnameTextField;
    public TextField modifyAgeTextField;
    public TextField modifyAddressTextField;
    public TextField modifyPhoneTextField;
    public TextField modifyEmailTextField;

    public JFXRadioButton modifyRBOther;
    public JFXRadioButton modifyRBFemale;
    public JFXRadioButton modifyRBMale;

    private String name, surname, gender, phone, email, address;
    private int age;

    private ToggleGroup genderToggleGroup = new ToggleGroup();

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
        int id = 3000;

        collectData();
        SelectFromPatients.updatePatientsIntoDatabase(id, age, name, surname, gender, phone, email, address);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGenderToggle();
    }
}
