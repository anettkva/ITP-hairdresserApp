package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import core.PriceCalculator;
import core.Filehandling;
import core.Treatment;


/**
 * JavaFX App
 */
public class HairdresserApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("HairdresserApp.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();

        stage.setOnCloseRequest(event -> {
            Filehandling filehandling = new Filehandling();
            filehandling.reset();
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

