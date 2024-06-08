package medioxide.controller.medicine;

import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import medioxide.components.MedicineDataTableView;
import medioxide.components.TestDataTableView;
import medioxide.database.MedicineDBTable;
import medioxide.database.TestDBTable;
import medioxide.helper.OnClickListener;
import medioxide.model.medicine.MedicineMainModel;
import medioxide.model.medicine.MedicineTableViewModel;
import medioxide.model.test.TestTableViewModel;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicineController implements Initializable, OnClickListener {
    public TextField addNameTextField;
    public JFXTextArea addDescTextField;
    public TextField addTypeTextField;
    public TextField addGenericTextField;
    public TextField addBrandsTextField;
    public AnchorPane showAllMedicine;

    private MedicineMainModel model;



    private void dataCollect(){
        String name = addNameTextField.getText();
        String type = addTypeTextField.getText();
        String generic = addGenericTextField.getText();
        String brands = addBrandsTextField.getText();
        String description = addDescTextField.getText();

        model = new MedicineMainModel(name, type, generic, brands, description);
    }

    private void consoleShowData(){
        System.out.println("\nName: " + model.getName());
        System.out.println("Types: " + model.getTypes());
        System.out.println("Generic: " + model.getGeneric());
        System.out.println("Brands: " + model.getBrands());
        System.out.println("Description: " + model.getDescription());
        System.out.println();
    }


    public void medicineSearchButton(ActionEvent event) {
    }
    public void saveButton(ActionEvent event) {
        dataCollect();
        consoleShowData();
        MedicineDBTable.insertIntoTest(model);
    }
    public void cancelButton(ActionEvent event) {
    }

    private void showAllButton() {
        var list = MedicineDBTable.getAllTestList();
        var medicineList = FXCollections.observableList(list);
        var table = new MedicineDataTableView<MedicineTableViewModel>(medicineList, this);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        showAllMedicine.getChildren().add(table);
    }








    @Override
    public void onDeleteClick(int id) {
        System.out.println("On delete clicked. ID: " + id);

    }

    @Override
    public void onEditClick(int id) {
        System.out.println("On edit clicked. ID: " + id);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showAllButton();

    }

}
