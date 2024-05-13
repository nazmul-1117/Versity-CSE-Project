package medioxide.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import medioxide.java.Main;

import java.io.IOException;
import java.net.URL;

public class FXMLLoadToPane {
    private Pane pane;
    public Pane getPane(String fxmlFileName){
       try {
           URL fxmlURL = Main.class.getResource(fxmlFileName);
           FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
           pane = fxmlLoader.load();
       }catch (IOException ioException) {
           ioException.printStackTrace();
       }

       return pane;
    }
}
