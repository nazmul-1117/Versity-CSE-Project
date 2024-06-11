package medioxide.controller.home;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import medioxide.database.home.HomeDBTable;
import medioxide.model.home.HomeDoctorModel;
import medioxide.model.home.HomePatientsModel;
import medioxide.model.home.HomeTestModel;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Label patientsTotalNumber;
    public Label doctorTotalNumber11;
    public Label totalDiagnosticNumber;
    public Label doctorTotalNumber;
    public Label totalRevenueNumber;
    public Label doctorTotalNumber1;
    public Label adminTotalNumber;
    public Label nurseTotalNumber;


    HomePatientsModel patientsModel;
    HomeDoctorModel doctorModel;
    HomeTestModel testModel;


    private void setData(){

        patientsTotalNumber.setText(Integer.toString(patientsModel.getTotalPatients())+"+");
        doctorTotalNumber.setText(Integer.toString(doctorModel.getTotalDoctor())+"+");
        totalDiagnosticNumber.setText(Integer.toString(testModel.getTotalTest() )+"+");
    }

    private void getFromDatabase(){
        var patientsNumber = HomeDBTable.getPatientsNumber();
        var doctorsNumber = HomeDBTable.getDoctorNumber();
        var testsNumber = HomeDBTable.getTestNumber();

        if (patientsNumber != null && doctorsNumber != null && testsNumber != null){
            patientsModel = patientsNumber.get(0);
            doctorModel = doctorsNumber.get(0);
            testModel = testsNumber.get(0);

            setData();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getFromDatabase();

    }
}
