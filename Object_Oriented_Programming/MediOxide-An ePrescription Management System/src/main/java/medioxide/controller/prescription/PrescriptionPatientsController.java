package medioxide.controller.prescription;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import medioxide.database.PrescriptionDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.model.prescription.PrescriptionPatientsModel;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class PrescriptionPatientsController implements Initializable {


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

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
