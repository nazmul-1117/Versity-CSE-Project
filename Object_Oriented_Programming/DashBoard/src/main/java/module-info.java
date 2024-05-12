module medioxide.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.jfoenix;


    opens medioxide.java to javafx.fxml;
    exports medioxide.java;
    exports medioxide.controller;
    opens medioxide.controller to javafx.fxml;
}