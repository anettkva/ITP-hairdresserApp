package app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class TreatmentController {

    private PriceCalculator calculator;

    private Filehandling filehandling;

    private List<Treatment> chosenTreatments; 

    private Treatment longHairCut = new Treatment("Long hair cut", 500, 90);

    private Treatment shortHairCut = new Treatment("Short hair cut", 300, 60);

    private Treatment stripes = new Treatment("Stripes", 1500, 180);

    private Treatment color = new Treatment("Color", 2000, 180);

    private Treatment styling = new Treatment("Styling", 500, 60);

    private Treatment wash = new Treatment("Wash", 500, 30);

    @FXML 
    TextField totalPriceField; 

    @FXML 
    TextArea overViewTextArea;



    @FXML
    private void initialize() {
        calculator = new PriceCalculator();
        filehandling = new Filehandling();
        chosenTreatments = new ArrayList<>();

    }

    private void addToList(Treatment treatment) {
        chosenTreatments.add(treatment);
    }

    private void removeFromList(Treatment treatment) {
        chosenTreatments.remove(treatment);
    }

    private void updateFile() {
        filehandling.reset();
        for (Treatment t : chosenTreatments) {
            try {
                filehandling.writeToFile(t);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



    @FXML
    void handleLongCut() {
        if (!chosenTreatments.contains(longHairCut)) {
            addToList(longHairCut);
        }
        else {
            removeFromList(longHairCut);
        }
        updateFile();
        
    }

    @FXML
    void handleShortCut() {
        if (!chosenTreatments.contains(shortHairCut)) {
            addToList(shortHairCut);
        }
        else {
            removeFromList(shortHairCut);
        }
        updateFile();
    }

    @FXML
    void handleStripes() {
        if (!chosenTreatments.contains(stripes)) {
            addToList(stripes);
        }
        else {
            removeFromList(stripes);
        }
        updateFile();
    }

    @FXML
    void handleColor() {
        if (!chosenTreatments.contains(color)) {
            addToList(color);
        }
        else {
            removeFromList(color);
        }
        updateFile();

    }

    @FXML
    void handleStyling() {
        if (!chosenTreatments.contains(styling)) {
            addToList(styling);
        }
        else {
            removeFromList(styling);
        }
        updateFile();
    }

    @FXML
    void handleWash() {
        if (!chosenTreatments.contains(wash)) {
            addToList(wash);
        }
        else {
            removeFromList(wash);
        }
        updateFile();
    }

    @FXML
    void handleCalculatePrice() {
        double price = calculator.CalculateTotalPrice(chosenTreatments);
        String priceString = String.valueOf(price);
        totalPriceField.setText(priceString);
    }

    @FXML
    void handleShowOverview() throws IOException {
        List<Treatment> fileTreatments = filehandling.loadFromFile();
        for (Treatment t : fileTreatments) {
            overViewTextArea.setText(t.getName() + ": " + t.getPrice() + " kr\\n" );
        }
    }

    


    
}
