package medioxide.controller.medicine;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import medioxide.database.MedicineDBTable;
import medioxide.model.medicine.MedicineModifyModel;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicineModifyController implements Initializable {


    public TextField modifyNameTextField;
    public TextField modifyTypeTextField;
    public TextField modifyGenericTextField;
    public TextField modifyBrandTextField;
    public TextField modifyDescTextField;


    private MedicineModifyModel model;


    public void setModel(MedicineModifyModel model) {
        this.model = model;

        setData();
    }
    private void setData(){
        modifyNameTextField.setText(model.getName());
        modifyTypeTextField.setText(model.getTypes());
        modifyGenericTextField.setText(model.getGeneric());
        modifyBrandTextField.setText(model.getBrands());
        modifyDescTextField.setText(model.getDescription());
    }
    private void collectData(){
        int id = model.getId();
        String name = modifyNameTextField.getText();
        String type = modifyTypeTextField.getText();
        String generic = modifyGenericTextField.getText();
        String brand = modifyBrandTextField.getText();
        String desc = modifyDescTextField.getText();

        model = null;
        model = new MedicineModifyModel(id, name, type, generic, brand, desc);
    }
    private void consoleShowAllData(){

        if (model != null){
            System.out.println("Name: " + model.getName());
            System.out.println("Type: " + model.getTypes());
            System.out.println("Generic: " + model.getGeneric());
            System.out.println("Brands: " + model.getBrands());
            System.out.println("Desc: " + model.getDescription());
        }

    }
    public void saveChangeButton(ActionEvent event) {
        collectData();
        consoleShowAllData();
        MedicineDBTable.updateMedicineData(model);

    }

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }







    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
