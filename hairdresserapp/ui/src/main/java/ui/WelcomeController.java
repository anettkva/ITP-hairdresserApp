package ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller class for the Welcome view.
 * 
 * This class handles user interactions from the Welcome screen,
 * such as navigating to the Hairdresser application or the Reviews section.
 */
public class WelcomeController {

    /**
     * Handles the action when the primary button is clicked.
     * 
     * This method loads the "HairdresserApp.fxml" layout, creates a new stage,
     * sets the scene, and displays the new window.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    void handleButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("HairdresserApp.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    /**
     * Handles the action when the review button is clicked.
     * 
     * This method loads the "Reviews.fxml" layout, creates a new stage,
     * sets the scene, and displays the new window.
     *
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    void handleReviewButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Reviews.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
