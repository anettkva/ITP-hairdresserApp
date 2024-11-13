package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.TreatmentFilehandling;

/**
 * JavaFX Application for the Hairdresser Management System.
 * 
 * This class serves as the entry point for the Hairdresser application.
 * It initializes the primary stage with the Welcome view and sets up
 * necessary event handlers for application closure.
 */
public class HairdresserApp extends Application {

    /**
     * Starts the JavaFX application by loading the Welcome.fxml layout,
     * setting the scene, and displaying the primary stage.
     * 
     * Additionally, it sets an event handler to perform cleanup actions
     * (resetting treatment data) when the application window is closed.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the Welcome.fxml layout
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Welcome.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();

        stage.setOnCloseRequest(event -> {
            TreatmentFilehandling filehandling = new TreatmentFilehandling();
            try {
                filehandling.reset();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * The main entry point for the application.
     * 
     * Launches the JavaFX application by invoking the {@link Application#launch(String...)}
     * method with the provided command-line arguments.
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

}

