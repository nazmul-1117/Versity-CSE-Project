package medioxide.controller.problem;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import medioxide.database.ProblemDBTable;
import medioxide.model.problem.ProblemMainModel;
import medioxide.model.problem.ProblemModifyModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProblemModifyController implements Initializable {


    public TextField modifyNameTextField;
    public JFXComboBox modifyDeptComboBox;
    public TextField modifySymptomsTextField;
    public TextField modifyTreatmentTextField;
    public TextField modifyDescTextField;

    private ProblemModifyModel model;








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

    public void setModel(ProblemModifyModel model) {
        this.model = model;

        setData();
    }
    private void setData(){
        modifyNameTextField.setText(model.getName());
        modifyDescTextField.setText(model.getDescription());

        modifySymptomsTextField.setText(model.getSymptoms());
        modifyTreatmentTextField.setText(model.getTreatment());
    }
    private void dataCollect(){
        int id = model.getId();
        String name = modifyNameTextField.getText();
        String dept = modifyDeptComboBox.getSelectionModel().getSelectedItem().toString();
        String disc = modifyDescTextField.getText();
        String symptoms = modifySymptomsTextField.getText();
        String treatment = modifyTreatmentTextField.getText();

        model = new ProblemModifyModel(id, name, dept, disc, symptoms, treatment);
    }





    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void saveChangeButton(ActionEvent event) {
        dataCollect();
        ProblemDBTable.updateProblemData(model);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
            initComboBox();
    }
}
