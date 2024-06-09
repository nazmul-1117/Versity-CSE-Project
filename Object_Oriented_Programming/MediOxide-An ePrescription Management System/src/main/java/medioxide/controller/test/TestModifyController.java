package medioxide.controller.test;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import medioxide.database.TestDBTable;
import medioxide.helper.HelperFunctions;
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


    public TestModifyController() {}

    public void setTestModifyModel(TestModifyModel model) {
        this.model = model;
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

        modifyNameTextField.setText(model.getName());
        modifyDescriptionTextField.setText(model.getDescription());
        modifyNormalRangeTextField.setText(Float.toString(model.getNormalRange()));
        modifyPriceTextField.setText(Float.toString(model.getPrice()));
    }
    private void  collectData(){
        String name, description, category;
        float normalRange, price;

        int id = model.getId();
        name = modifyNameTextField.getText();
        category = modifyTestCategoryComboBox.getSelectionModel().getSelectedItem().toString();
        description = modifyDescriptionTextField.getText();

        normalRange = HelperFunctions.stringToFloat(modifyNormalRangeTextField.getText());
        price = HelperFunctions.stringToFloat(modifyPriceTextField.getText());

        model = null;
        model = new TestModifyModel(id, name, category, description, normalRange, price);
    }
    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void saveChangeButton(ActionEvent event) {
        collectData();
        TestDBTable.updateTestIntoDatabase(model);
    }





    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboBox();
    }
}
