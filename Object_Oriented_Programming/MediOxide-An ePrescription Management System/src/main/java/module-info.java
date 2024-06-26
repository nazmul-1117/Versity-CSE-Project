module medioxide {
    requires javafx.controls;
    requires com.jfoenix;
    requires java.sql;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires javafx.base;
    requires io;
    requires kernel;
    requires layout;


    opens medioxide.model.patients to javafx.base;
    opens medioxide.model.test to javafx.base;
    opens medioxide.model.problem to javafx.base;

    opens medioxide.controller.patients to javafx.fxml;
    opens medioxide.controller.test to javafx.fxml;
    opens medioxide.controller.medicine to javafx.fxml;
    opens medioxide.controller.problem to javafx.fxml;
    opens medioxide.controller.doctor to javafx.fxml;
    opens medioxide.controller.prescription to javafx.fxml;
    opens medioxide.controller.others to javafx.fxml;
    opens medioxide.controller.login to javafx.fxml;
    opens medioxide.controller.mainController to javafx.fxml;
    opens medioxide.controller.home to javafx.fxml;



    exports medioxide.java;
    exports medioxide.controller.patients;
}