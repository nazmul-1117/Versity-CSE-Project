package medioxide.controller.prescription;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import medioxide.database.PrescriptionDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.model.prescription.PrescriptionDoctorModel;
import medioxide.model.prescription.PrescriptionPatientsModel;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Function;

public class PrescriptionDoctorController implements Initializable {


    public TextField idTextField;
    public TextField nameTextField;
    public TextField emailTextField;
    public TextField deptTextField;
    public TextField specTextField;

    private PrescriptionDoctorModel doctorModel;
    private Function<PrescriptionDoctorModel, Void> onSaved;

    public void setDoctorModel(PrescriptionDoctorModel doctorModel) {
        this.doctorModel = doctorModel;
    }
    public PrescriptionDoctorModel getDoctorModel() {
        return doctorModel;
    }

    public void init(Function<PrescriptionDoctorModel, Void> onSaved) {
        this.onSaved = onSaved;
    }


    private void setData() {
        nameTextField.setText(doctorModel.getdName());
        emailTextField.setText(doctorModel.getdEmail());
        deptTextField.setText(doctorModel.getdDept());
        specTextField.setText(doctorModel.getdSpec());
    }

    public void searchPatientsButton(ActionEvent event) {
        int id = HelperFunctions.stringToInt(idTextField.getText());
        var getDoctor = PrescriptionDBTable.geDoctorListById(id);

        if (getDoctor.size() > 0) {
            doctorModel = getDoctor.get(0);
            setData();
        }
    }

    public void okayButton(ActionEvent event) {
        event.consume();
        onSaved.apply(doctorModel);
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
