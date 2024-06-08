package medioxide.controller.test;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import medioxide.database.PatientsDBTable;
import medioxide.database.TestDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.model.patients.PatientsModifyModel;
import medioxide.model.test.TestMainModel;
import medioxide.model.test.TestModifyModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TestModifyController implements Initializable {

    public TextField modifyNameTextField;
    public JFXComboBox modifyTestCategoryComboBox;
    public TextField modifyDescriptionTextField;
    public TextField modifyNormalRangeTextField;
    public TextField modifyPriceTextField;

    private TestModifyModel model;
    private TestModifyModel tempModel;
    private int id;


    public TestModifyController() {}

    public void setTestModifyModel(TestModifyModel tempModel) {
        this.tempModel = tempModel;
        id = tempModel.getId();
        setData();
    }
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

        modifyTestCategoryComboBox.setItems(list);
    }
    public void setData(){

        try {
            System.out.println("model is not null");

//            modifyNameTextField.setText(tempModel.getName());
//            modifyDescriptionTextField.setText(tempModel.getDescription());

            modifyNormalRangeTextField.setText(Float.toString(tempModel.getNormalRange()));
            modifyPriceTextField.setText(Float.toString(tempModel.getPrice()));

        }catch (NullPointerException e){
            System.out.println("\n----------->Null pointer<-------------------\n");
        }

        System.out.println("ID: " + tempModel.getId());
        System.out.println("Name: " + tempModel.getName());
        System.out.println("Category: " + tempModel.getCategory());
        System.out.println("Description: " + tempModel.getDescription());
        System.out.println("Normal Range: " + tempModel.getNormalRange());
        System.out.println("Price: " + tempModel.getPrice());
    }
    private void  collectData(){
        String name, description, category;
        float normalRange, price;

        name = modifyNameTextField.getText();
        category = modifyTestCategoryComboBox.getSelectionModel().getSelectedItem().toString();
        description = modifyDescriptionTextField.getText();

        normalRange = HelperFunctions.stringToFloat(modifyNormalRangeTextField.getText());
        price = HelperFunctions.stringToFloat(modifyPriceTextField.getText());

        model = new TestModifyModel(name, category, description, normalRange, price);
        System.out.println("ID: " +tempModel.getId());
    }

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void saveChangeButton(ActionEvent event) {
        collectData();

            System.out.println("Update for ID: " + id + "\n");
            System.out.println("Model Name: " + model.getName());
//            TestDBTable.updateTestIntoDatabase(model);
            System.out.println("Model is null");
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
    }
}
