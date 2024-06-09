package medioxide.controller.problem;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medioxide.components.ProblemTableView;
import medioxide.database.ProblemDBTable;
import medioxide.helper.OnClickListener;
import medioxide.java.Main;
import medioxide.model.problem.ProblemMainModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ProblemController implements Initializable, OnClickListener {


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
        var list = ProblemDBTable.getAllProblemList();
        var problemList = FXCollections.observableList(list);
        var table = new ProblemTableView<>(problemList, this);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        showAllProblem.getChildren().add(table);
    }

    public void problemSearchButton(ActionEvent event) {

        String searchItem = searchProblemTextField.getText();
        List list;

        try {
            int id = Integer.parseInt(searchItem);
            list = ProblemDBTable.getProblemListById(id);

        }catch (Exception e){
            list =ProblemDBTable.getProblemListByName(searchItem);
        }

        var problemList = FXCollections.observableList(list);
        var table = new ProblemTableView<>(problemList, this);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 80.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        searchBoxAnchorPane.getChildren().add(table);
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
        showAllProblemList();
    }
    @Override
    public void onEditClick(int id) {
        System.out.println("Edit Clicked for: " + id);

        try {
            var list = ProblemDBTable.getModifyProblemListById(id);

            if (!list.isEmpty()){
                var model = list.get(0);

                String fxml = "modify_problem.fxml";
                URL fxmlURL = Main.class.getResource(fxml);
//                System.out.println("URL: " + fxmlURL);

                FXMLLoader fxmlLoader  = new FXMLLoader(fxmlURL);
                Scene scene = new Scene(fxmlLoader.load());

                ProblemModifyController pmc = fxmlLoader.getController();
                pmc.setModel(model);

                Stage stage = new Stage();
                stage.setScene(scene);

                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }

        }catch (IOException ioException){
            System.out.println("modify_patients.fxml failed");
        }
        showAllProblemList();

    }
    @Override
    public void onDeleteClick(int id) {
        System.out.println("Delete Clicked for: " + id);
        ProblemDBTable.deleteProblemData(id);
        System.out.println("Delete Problem successful for ID " + id);
        showAllProblemList();

    }
}
