module medioxide {
    requires javafx.controls;
    requires com.jfoenix;
    requires java.sql;
    requires javafx.fxml;

    opens medioxide.controller to javafx.fxml;
    opens medioxide.model to javafx.base;

    exports medioxide.java;
}