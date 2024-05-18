package medioxide.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import medioxide.java.Main;

import java.io.IOException;
import java.net.URL;

public class FXMLLoadToPane {
    private Pane pane;
    public Pane getPane(String fxmlFileName){
       try {
           URL fxmlURL = Main.class.getResource(fxmlFileName);
           System.out.println("FXML URL: " + fxmlURL);
           FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
           pane = fxmlLoader.load();
       }catch (IOException ioException) {
           ioException.printStackTrace();
       }

       return pane;
    }
}
