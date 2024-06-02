package medioxide.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ProblemController implements Initializable {


    public JFXComboBox comboBox;

    public void modifySearchButton(ActionEvent event) {
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

        comboBox.setItems(list);
//        addDeptComboBox.setItems(list);
    }
    public void comboMethod(ActionEvent event) {
        var item = comboBox.getSelectionModel().getSelectedItem();


        String getitem = comboBox.getSelectionModel().getSelectedItem().toString();
        System.out.println(getitem);
        comboBox.setStyle("-fx-prompt-text-fill: red");
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
                initComboBox();
    }

}
