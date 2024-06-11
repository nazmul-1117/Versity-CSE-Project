package medioxide.controller.prescription;

import com.jfoenix.controls.JFXRadioButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import medioxide.model.prescription.PrescriptionMedicineModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;

public class PrescriptionMedicineController implements Initializable {


    public ComboBox medicineTypeComboBox;
    public TextField medicineName;
    public TextField medicinePower;
    public TextField medicineMorningTime;
    public TextField medicineNoonTime;
    public TextField medicineNightTime;
    public JFXRadioButton medicineBeforeEatRB;
    public JFXRadioButton medicineAfterEatRB;
    public TextField beforeAfterEatTime;

    private final ToggleGroup beforeAfterEat = new ToggleGroup();

    private PrescriptionMedicineModel model;

    private Function<PrescriptionMedicineModel, Void> onSaved;


    public void init(Function<PrescriptionMedicineModel, Void> onSaved) {
        this.onSaved = onSaved;

    }

    private void initGenderToggle() {
        medicineBeforeEatRB.setToggleGroup(beforeAfterEat);
        medicineAfterEatRB.setToggleGroup(beforeAfterEat);
    }

    private void initComboBox() {
        ObservableList<String> list = FXCollections.observableList(new ArrayList<>());
        list.add("Tablet");
        list.add("Syrup");
        list.add("Capsule");

        //comboBox.setItems(list);
        medicineTypeComboBox.setItems(list);
    }

    private void dataCollect() {
        String type = medicineTypeComboBox.getSelectionModel().getSelectedItem().toString();
        String name = medicineName.getText();
        String power = medicinePower.getText();

        String dose = medicineMorningTime.getText() + " - " + medicineNoonTime.getText() + " - " +
                medicineNightTime.getText();


        String beforeAfterEat = ((JFXRadioButton) this.beforeAfterEat.getSelectedToggle()).getText();

        String beforeAfterTime = beforeAfterEatTime.getText();

        model = new PrescriptionMedicineModel(type, name, power, dose, beforeAfterEat, beforeAfterTime);
    }


    public void saveButton(ActionEvent event) {
        dataCollect();

        onSaved.apply(model);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void cancelButton(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initGenderToggle();
        initComboBox();
    }
}
