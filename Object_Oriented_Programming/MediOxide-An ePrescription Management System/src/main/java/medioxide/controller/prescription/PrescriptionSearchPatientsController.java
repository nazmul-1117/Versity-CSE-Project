package medioxide.controller.prescription;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medioxide.controller.problem.ProblemModifyController;
import medioxide.database.PrescriptionDBTable;
import medioxide.database.ProblemDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.java.Main;
import medioxide.model.prescription.PrescriptionPatientsModel;

import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class PrescriptionSearchPatientsController implements Initializable {


    public TextField searchPatientsTextField;
    public TextField nameTextField;
    public TextField ageTextField;
    public TextField genderTextField;

    private PrescriptionPatientsModel patientsModel;

    public void setPatientsModel(PrescriptionPatientsModel patientsModel) {
        this.patientsModel = patientsModel;
    }

    public PrescriptionPatientsModel getPatientsModel() {
        return patientsModel;
    }


    private void setData(){
        nameTextField.setText(patientsModel.getName());
        ageTextField.setText(Integer.toString(patientsModel.getAge()));
        genderTextField.setText(patientsModel.getGender());
    }

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void searchPatientsButton(ActionEvent event) {
        int id = HelperFunctions.stringToInt(searchPatientsTextField.getText());
        var getPatients = PrescriptionDBTable.getPatientListById(id);

        patientsModel = getPatients.get(0);
        setData();

    }

    public PrescriptionPatientsModel okayButton(ActionEvent event) {
        PrescriptionController ps = new PrescriptionController();
        

        System.out.println("hhhhhhhhhhhhhhhhhhhhhhhhhhh");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

        return patientsModel;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
