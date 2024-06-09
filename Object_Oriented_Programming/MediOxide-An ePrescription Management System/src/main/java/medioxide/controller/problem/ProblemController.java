package medioxide.controller.problem;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import medioxide.components.MedicineDataTableView;
import medioxide.database.MedicineDBTable;
import medioxide.database.ProblemDBTable;
import medioxide.model.medicine.MedicineTableViewModel;
import medioxide.model.problem.ProblemMainModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProblemController implements Initializable {


    public TextField addNameTextField;
    public JFXTextArea addDescTextField;
    public TextField addSymptomsTextField;
    public TextField addTreatmentTextField;
    public JFXComboBox addDeptComboBox;
    public AnchorPane showAllProblem;
    public AnchorPane searchBoxAnchorPane;
    public TextField searchProblemTextField;

    private ProblemMainModel model;
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
        addDeptComboBox.setItems(list);
    }

    private void dataCollect(){
        String name = addNameTextField.getText();
        String dept = addDeptComboBox.getSelectionModel().getSelectedItem().toString();
        String disc = addDescTextField.getText();
        String symptoms = addSymptomsTextField.getText();
        String treatment = addTreatmentTextField.getText();

        model = new ProblemMainModel(name, dept, disc, symptoms, treatment);

    }

    private void consoleShowAllData(){
        System.out.println("Name: " + model.getName());
        System.out.println("Department: " + model.getDepartment());
        System.out.println("Desc: " + model.getDescription());
        System.out.println("Sympt: " + model.getSymptoms());
        System.out.println("Treatment: " + model.getTreatment());
    }


    public void saveButton(ActionEvent event) {
        dataCollect();
        consoleShowAllData();
        ProblemDBTable.insertIntoProblem(model);
    }

    public void cancelButton(ActionEvent event) {
    }

    private void showAllProblemList(){
//        var list = MedicineDBTable.getAllTestList();
//        var medicineList = FXCollections.observableList(list);
//        var table = new MedicineDataTableView<MedicineTableViewModel>(medicineList, this);
//
//        table.setLayoutX(0);
//        table.setLayoutY(80);
//        AnchorPane.setBottomAnchor(table, 0.0);
//        AnchorPane.setTopAnchor(table, 0.0);
//        AnchorPane.setLeftAnchor(table, 0.0);
//        AnchorPane.setRightAnchor(table, 0.0);
//        showAllProblem.getChildren().add(table);
    }

    public void problemSearchButton(ActionEvent event) {

        String searchItem = searchProblemTextField.getText();
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
    }
}
