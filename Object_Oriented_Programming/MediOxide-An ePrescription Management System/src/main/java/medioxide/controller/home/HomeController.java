package medioxide.controller.home;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import medioxide.database.home.HomeDBTable;
import medioxide.model.home.HomeModel;

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


    HomeModel model;

    private void setData(){
        patientsTotalNumber.setText(Integer.toString(model.getTotalPatients())+"+");
    }

    private void getFromDatabase(){
        var patientsNumber = HomeDBTable.getPatientsNumber();

        if (patientsNumber != null){
            model= patientsNumber.get(0);
            setData();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getFromDatabase();

    }
}
