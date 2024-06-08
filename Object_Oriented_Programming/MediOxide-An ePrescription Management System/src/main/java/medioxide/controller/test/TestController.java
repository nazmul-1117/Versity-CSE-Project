package medioxide.controller.test;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import medioxide.database.TestDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.model.test.TestMainModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TestController implements Initializable {

    public TextField addNormalRangeTextField;
    public TextField addTestNameTextField;
    public JFXTextArea addTestDescriptionTextField;
    public TextField addTestPriceTextField;
    public JFXComboBox addTestCategoryComboBox;

    private String name, category, description;
    private float normalRange, price;

    private TestMainModel testMainModel = null;

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

        addTestCategoryComboBox.setItems(list);
    }

    private void dataCollect(){

        name = addTestNameTextField.getText();
        category = addTestCategoryComboBox.getSelectionModel().getSelectedItem().toString();
        description = addTestDescriptionTextField.getText();

        normalRange = HelperFunctions.stringToFloat(addNormalRangeTextField.getText());
        price = HelperFunctions.stringToFloat(addTestPriceTextField.getText());

        testMainModel = new TestMainModel(name, category, description, normalRange, price);


    }

    private void consolePrintAllData(){
        System.out.println("Name: " + name);
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
        System.out.println("Normal Range: " + normalRange);
        System.out.println("Price: " + price);
    }
    public void testSearchButton(ActionEvent event) {
    }
    public void saveButton(ActionEvent event) {
        dataCollect();

        if (testMainModel != null){
            consolePrintAllData();
            TestDBTable.insertIntoTest(testMainModel);

        }else {
            System.out.println("Data collection null for add test window");
        }

    }

    public void cancelButton(ActionEvent event) {
    }










    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initComboBox();
    }

}
