package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.Treatment;
import json.TreatmentFilehandling;


/**
 * JavaFX App
 */
public class HairdresserApp extends Application {

    private static final List<Treatment> selectedTreatments = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("HairdresserApp.fxml"));
        Parent parent = fxmlLoader.load();
        stage.setScene(new Scene(parent));
        stage.show();

        stage.setOnCloseRequest(event -> {
            TreatmentFilehandling filehandling = new TreatmentFilehandling();
            try {
                filehandling.reset();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
     
    }

    

    public static void main(String[] args) {
        Application.launch(args);
    }

    
}

