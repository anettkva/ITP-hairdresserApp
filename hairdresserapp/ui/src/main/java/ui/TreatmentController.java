package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.Filehandling;
import core.PriceCalculator;
import core.Treatment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;





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

    public TextField getfield() {
        return this.totalPriceField;
    }

    public TextArea getarea() {
        return this.overViewTextArea;
    }



    @FXML
    protected void initialize() {
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

    private void handleTreatment(Treatment treatment) {
        if (!chosenTreatments.contains(treatment)) {
            addToList(treatment);
        } else {
            removeFromList(treatment);
        }
        updateFile();
        handleCalculatePrice();
        try {
            handleShowOverview();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    @FXML
    void handleLongCut() {
        handleTreatment(longHairCut);
        
    }

    @FXML
    void handleShortCut() {
        handleTreatment(shortHairCut);
    }

    @FXML
    void handleStripes() {
        handleTreatment(stripes);
    }

    @FXML
    void handleColor() {
        handleTreatment(color);

    }

    @FXML
    void handleStyling() {
        handleTreatment(styling);
    }

    @FXML
    void handleWash() {
        handleTreatment(wash);
    }

    @FXML
    void handleCalculatePrice() {
        double price = calculator.CalculateTotalPrice(chosenTreatments);
        String priceString = String.valueOf(price);
        totalPriceField.setText(priceString);
    }

    @FXML
    void handleShowOverview() throws IOException {
        overViewTextArea.setText(" ");
        List<Treatment> fileTreatments = filehandling.loadFromFile();
        for (Treatment t : fileTreatments) {
            overViewTextArea.appendText(t.getName() + ": " + t.getPrice() + " kr, Varighet (min): " + t.getduration() + "\n" );
        }
    }
    
    @FXML 
    void handleBookingButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Booking.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public List<Treatment> getChosenTreatments() {
        return chosenTreatments;
    }
    
    public TextField getTotalPriceField() {
        return totalPriceField;
    }
    
    public TextArea getOverViewTextArea() {
        return overViewTextArea;
    }

}
