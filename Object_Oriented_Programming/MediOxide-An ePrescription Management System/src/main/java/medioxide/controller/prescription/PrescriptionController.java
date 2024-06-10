package medioxide.controller.prescription;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import medioxide.controller.patients.PatientsModifyController;
import medioxide.controller.problem.ProblemModifyController;
import medioxide.database.PatientsDBTable;
import medioxide.database.PrescriptionDBTable;
import medioxide.database.ProblemDBTable;
import medioxide.java.Main;
import medioxide.model.prescription.PrescriptionPatientsModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrescriptionController implements Initializable {

    public TextField patientsID;
    public TextField patientsName;
    public TextField patientsAge;
    public TextField patientsGender;

    public TextField doctorID;
    public TextField doctorName;
    public TextField doctorDept;
    public TextField doctorSpec;

    public JFXButton addPatientsButtonID;
    public JFXButton addDoctorButtonID;

    private PrescriptionPatientsModel patientsModel;


    private void setData(){
        patientsID.clear();
        patientsID.setText(Integer.toString(patientsModel.getId()));
        patientsName.setText(patientsModel.getName());
        patientsAge.setText(Integer.toString(patientsModel.getAge()));
        patientsGender.setText(patientsModel.getGender());
    }
    public void addPatientsButtonClicked() {
        int id=3000;
        var patientsList = PrescriptionDBTable.getPatientListById(id);

        if (patientsList != null){
            patientsModel = patientsList.get(0);
        }

        try {
            var list = PrescriptionDBTable.getPatientListById(id);

            if (!list.isEmpty()){
                var model = list.get(0);

                String fxml = "prescription_patients_search.fxml";
                URL fxmlURL = Main.class.getResource(fxml);

                FXMLLoader fxmlLoader  = new FXMLLoader(fxmlURL);
                Scene scene = new Scene(fxmlLoader.load());

                PrescriptionSearchPatientsController pspc = fxmlLoader.getController();
                patientsModel = pspc.getPatientsModel();

                Stage stage = new Stage();
                stage.setScene(scene);

                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }

        }catch (IOException ioException){
            System.out.println("modify_patients.fxml failed");
        }
    }
    public void addDoctorButtonClicked(ActionEvent event) {
    }
    public void onTapNextVisit(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //addPatientsButtonClicked();

    }

    public void addNewMedicine(ActionEvent event) {
        try {
                String fxml = "prescription_problem.fxml";
                URL fxmlURL = Main.class.getResource(fxml);
                System.out.println("URL: " + fxmlURL);

                FXMLLoader  fxmlLoader  = new FXMLLoader(fxmlURL);
                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = new Stage();
                stage.setScene(scene);

                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();

        }catch(IOException ioException){
            System.out.println("modify_patients.fxml failed");
        }
    }
}
