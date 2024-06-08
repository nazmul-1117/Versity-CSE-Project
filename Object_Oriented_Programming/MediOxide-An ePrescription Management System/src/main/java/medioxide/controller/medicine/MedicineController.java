package medioxide.controller.medicine;

import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import medioxide.components.MedicineDataTableView;
import medioxide.components.PatientsDataTableListView;
import medioxide.components.TestDataTableView;
import medioxide.controller.patients.PatientsModifyController;
import medioxide.database.MedicineDBTable;
import medioxide.database.PatientsDBTable;
import medioxide.database.TestDBTable;
import medioxide.helper.OnClickListener;
import medioxide.java.Main;
import medioxide.model.medicine.MedicineMainModel;
import medioxide.model.medicine.MedicineTableViewModel;
import medioxide.model.patients.PatientsModel;
import medioxide.model.test.TestTableViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MedicineController implements Initializable, OnClickListener {
    public TextField addNameTextField;
    public JFXTextArea addDescTextField;
    public TextField addTypeTextField;
    public TextField addGenericTextField;
    public TextField addBrandsTextField;

    public TextField searchMedicineTextField;

    public AnchorPane showAllMedicine;
    public AnchorPane searchBoxAnchorPane;
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
        List list;
        String searchItem = searchMedicineTextField.getText();

        try {
            int id = Integer.parseInt(searchItem);
            list = MedicineDBTable.getPatientListById(id);

        }catch (Exception e){
            list = MedicineDBTable.getPatientListByName(searchItem);
        }

        var medicineList = FXCollections.observableList(list);
        var table = new MedicineDataTableView<>(medicineList, this);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 80.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        searchBoxAnchorPane.getChildren().add(table);

    }
    public void saveButton(ActionEvent event) {
        dataCollect();
        consoleShowData();
        MedicineDBTable.insertIntoTest(model);
        showAllButton();
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
        MedicineDBTable.deleteMedicineData(id);
        showAllButton();
    }

    @Override
    public void onEditClick(int id) {
        try {
            var list = MedicineDBTable.getModifyMedicineListById(id);

            if (!list.isEmpty()){
                var model = list.get(0);

                String fxml = "modify_medicine.fxml";
                URL fxmlURL = Main.class.getResource(fxml);
                System.out.println("URL: " + fxmlURL);

                FXMLLoader fxmlLoader  = new FXMLLoader(fxmlURL);
                Scene scene = new Scene(fxmlLoader.load());

                MedicineModifyController mpc = fxmlLoader.getController();
                mpc.setModel(model);

                Stage stage = new Stage();
                stage.setScene(scene);

                stage.initStyle(StageStyle.UNDECORATED);
                stage.show();
            }

        }catch (IOException ioException){
            System.out.println("modify_patients.fxml failed");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        showAllButton();

    }

}
