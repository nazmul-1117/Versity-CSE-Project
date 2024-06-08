module medioxide {
    requires javafx.controls;
    requires com.jfoenix;
    requires java.sql;
    requires javafx.fxml;

    opens medioxide.controller to javafx.fxml;
    opens medioxide.model.patients to javafx.base;
    opens medioxide.controller.patients to javafx.fxml;


    exports medioxide.java;
    exports medioxide.controller.patients;

}