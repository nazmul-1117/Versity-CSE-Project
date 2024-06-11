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
import java.util.function.Function;

public class PrescriptionSearchPatientsController implements Initializable {


    public TextField searchPatientsTextField;
    public TextField nameTextField;
    public TextField ageTextField;
    public TextField genderTextField;

    private PrescriptionPatientsModel patientsModel;

    private Function<PrescriptionPatientsModel, Void> onSaved;

    public void setPatientsModel(PrescriptionPatientsModel patientsModel) {
        this.patientsModel = patientsModel;
    }

    public PrescriptionPatientsModel getPatientsModel() {
        return patientsModel;
    }

    public void init(Function<PrescriptionPatientsModel, Void> onSaved) {
        this.onSaved = onSaved;
    }


    private void setData() {
        nameTextField.setText(patientsModel.getName());
        ageTextField.setText(Integer.toString(patientsModel.getAge()));
        genderTextField.setText(patientsModel.getGender());
    }

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void searchPatientsButton(ActionEvent event) {
        int id = HelperFunctions.stringToInt(searchPatientsTextField.getText());
        var getPatients = PrescriptionDBTable.getPatientListById(id);
        if (getPatients.size() > 0) {
            patientsModel = getPatients.get(0);
            setData();
        }
    }

    public void okayButton(ActionEvent event) {
        event.consume();
        onSaved.apply(patientsModel);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
