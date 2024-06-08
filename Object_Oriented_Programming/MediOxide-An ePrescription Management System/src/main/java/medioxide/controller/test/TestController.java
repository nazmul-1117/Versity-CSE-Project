package medioxide.controller.test;

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

import medioxide.components.TestDataTableView;
import medioxide.database.TestDBTable;
import medioxide.helper.HelperFunctions;
import medioxide.helper.OnClickListener;
import medioxide.java.Main;
import medioxide.model.test.TestMainModel;
import medioxide.model.test.TestTableViewModel;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TestController implements Initializable, OnClickListener {

    public TextField addNormalRangeTextField;
    public TextField addTestNameTextField;
    public JFXTextArea addTestDescriptionTextField;
    public TextField addTestPriceTextField;
    public JFXComboBox addTestCategoryComboBox;
    public TextField searchTestTextField;
    public AnchorPane searchBoxAnchorPane;

    public AnchorPane showAllTest;



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
        List list;
        String searchItem = searchTestTextField.getText();

        try {
            int id = Integer.parseInt(searchItem);
            list = TestDBTable.getTestListById(id);

        }catch (Exception e){
            list = TestDBTable.getTestListByName(searchItem);
        }

        var testList = FXCollections.observableList(list);
        var table = new TestDataTableView<>(testList, this);

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

        if (testMainModel != null){
            consolePrintAllData();
            TestDBTable.insertIntoTest(testMainModel);

        }else {
            System.out.println("Data collection null for add test window");
        }

    }
    public void cancelButton(ActionEvent event) {
    }
    private void showAllButton() {
        var list = TestDBTable.getAllTestList();
        var testList = FXCollections.observableList(list);
        var table = new TestDataTableView<TestTableViewModel>(testList, this);

        table.setLayoutX(0);
        table.setLayoutY(80);
        AnchorPane.setBottomAnchor(table, 0.0);
        AnchorPane.setTopAnchor(table, 0.0);
        AnchorPane.setLeftAnchor(table, 0.0);
        AnchorPane.setRightAnchor(table, 0.0);
        showAllTest.getChildren().add(table);
    }










    @Override
    public void onDeleteClick(int id) {
        System.out.println("On Delete Clicked for ID: " + id);
    }
    @Override
    public void onEditClick(int id) {
        System.out.println("On edit clicked");

        try {
            var list = TestDBTable.getModifyTestListById(id);

            if (!list.isEmpty()){
                var model = list.get(0);

                String fxml = "modify_test.fxml";
                URL fxmlURL = Main.class.getResource(fxml);

                FXMLLoader fxmlLoader  = new FXMLLoader(fxmlURL);
                Scene scene = new Scene(fxmlLoader.load());

                TestModifyController tmc = fxmlLoader.getController();
                tmc.setTestModifyModel(model);

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

        initComboBox();
        showAllButton();
    }
}
