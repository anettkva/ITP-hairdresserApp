package app;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;

public class TreatmentController {

    private PriceCalculator calculator;

    private Filehandling filehandling;

    private List<Treatment> chosenTreatments; 

    private Treatment longHairCut = new Treatment(500, 90);

    private Treatment shortHairCut = new Treatment(300, 60);

    private Treatment stripes = new Treatment(1500, 180);

    private Treatment color = new Treatment(2000, 180);

    private Treatment styling = new Treatment(500, 60);

    private Treatment wash = new Treatment(500, 30);



    public TreatmentController() {
        calculator = new PriceCalculator();
        filehandling = new Filehandling();
        chosenTreatments = new ArrayList<>();

    }

    private void addToList(Treatment treatment) {
        chosenTreatments.add(treatment));
    }

    private void removeFromList(Treatment treatment) {
        chosenTreatments.remove(treatment);
    }

    @FXML
    void handleLongCut() {
        addToList(longHairCut);
    }

    @FXML
    void handleShortCut() {
        addToList(longHairCut);
    }

    @FXML
    void handle() {
        addToList(longHairCut);
    }

    @FXML
    void handleLongCut() {
        addToList(longHairCut);
    }

    @FXML
    void handleLongCut() {
        addToList(longHairCut);
    }

    @FXML
    void handleLongCut() {
        addToList(longHairCut);
    }

    @FXML
    void handleLongCut() {
        addToList(longHairCut);
    }


    
}
