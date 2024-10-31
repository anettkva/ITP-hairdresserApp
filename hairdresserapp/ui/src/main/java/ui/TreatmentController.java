package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.PriceCalculator;
import core.Treatment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import json.TreatmentFilehandling;

import backend.RestTreatmentController;





public class TreatmentController {

    private PriceCalculator calculator;

    private TreatmentFilehandling filehandling;

    private List<Treatment> chosenTreatments; 

    private RestTreatmentController restcontroller;

   
    @FXML
    private CheckBox shortHairCut, longHairCut, stripes, color, styling, wash;

    private Map<CheckBox, Treatment> treatmentMap;

    

    @FXML 
    TextField totalPriceField; 

    @FXML 
    TextArea overViewTextArea;


    @FXML
    protected void initialize(){
        calculator = new PriceCalculator();
        filehandling = new TreatmentFilehandling();
        chosenTreatments = new ArrayList<>();
        restcontroller = new RestTreatmentController();

        treatmentMap = new HashMap<>();
        treatmentMap.put(shortHairCut, new Treatment("short haircut", 300));
        treatmentMap.put(longHairCut, new Treatment("Long haircut", 500));
        treatmentMap.put(stripes, new Treatment("Stripes", 1500));
        treatmentMap.put(color, new Treatment("Color", 2000));
        treatmentMap.put(styling, new Treatment("Styling", 500));
        treatmentMap.put(wash, new Treatment("Wash", 500));

        for (CheckBox checkBox : treatmentMap.keySet()){
            checkBox.setOnAction(event -> {
                try {
                    handleChecBoxAction(checkBox);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

    }

    private void handleChecBoxAction(CheckBox checkBox) throws IOException{
        Treatment treatment = treatmentMap.get(checkBox);
        if (treatment != null){
            handleTreatment(treatment);
        } 
        else {
            System.err.println("Fant ikke treatment for" + checkBox.getText());
        }
    }

    public TextField getfield() {
        return this.totalPriceField;
    }

    public TextArea getarea() {
        return this.overViewTextArea;
    }



    private void updateFile() throws IOException {
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

    private void handleTreatment(Treatment treatment) throws IOException {
        List<Treatment> chosenTreatments = restcontroller.getChosenTreatments();
        if (chosenTreatments.contains(treatment)) {
            restcontroller.deleteTreatment(treatment.getName());
        }else {
            restcontroller.addTreatment(treatment);
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
    void handleCalculatePrice() {
        double price = calculator.CalculateTotalPrice(chosenTreatments);
        String priceString = String.valueOf(price);
        totalPriceField.setText(priceString);
        System.out.println("Total price calculated: " + priceString);
    }

    @FXML
    void handleShowOverview() throws IOException {
        overViewTextArea.setText(" ");
        List<Treatment> fileTreatments = filehandling.readFromFile();
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
