package medioxide.controller.others;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ChipSingleItemController implements Initializable {
    @FXML
    private Label imgIcon;
    @FXML
    private Label lblTitle;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void init(String title, Runnable action) {
        lblTitle.setText(title);
        imgIcon.setOnMouseClicked(event -> action.run());
    }

    public void setLabelMaxWidth(double width) {
        lblTitle.setMaxWidth(width);
    }
}
