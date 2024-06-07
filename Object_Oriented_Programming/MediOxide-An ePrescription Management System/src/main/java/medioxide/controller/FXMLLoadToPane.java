package medioxide.controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import medioxide.java.Main;

import java.io.IOException;
import java.net.URL;

public class FXMLLoadToPane {
    private Pane pane = null;

    public Pane getPane(String fxmlFileName) {
        try {
            URL fxmlURL = Main.class.getResource(fxmlFileName);
            System.out.println("FXML URL: " + fxmlURL);

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlURL);
            System.out.println("FXML Loader: " + fxmlLoader);

            pane = fxmlLoader.load();
            System.out.println("Pane = " + pane);

        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("Pane load fail for: " + fxmlFileName);
        }
        if (pane == null) {
            System.out.println("null pane " + fxmlFileName);
        }
        System.out.println("\n");

        return pane;
    }
}
