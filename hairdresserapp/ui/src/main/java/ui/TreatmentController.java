package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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





public class TreatmentController {

    private TreatmentFilehandling filehandling;

    private List<Treatment> chosenTreatments; 

    private FrontService frontService;

   
    @FXML
    private CheckBox shortHairCut, longHairCut, stripes, color, styling, wash;

    private Map<CheckBox, Treatment> treatmentMap;

    

    @FXML 
    TextField totalPriceField; 

    @FXML 
    TextArea overViewTextArea;

    public TreatmentController() {
        this.frontService = new FrontService();
    }

    public TextField getfield() {
        return this.totalPriceField;
    }

    public TextArea getarea() {
        return this.overViewTextArea;
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

    @FXML
    protected void initialize(){
        filehandling = new TreatmentFilehandling();
        chosenTreatments = new ArrayList<>();

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
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }


    private void handleChecBoxAction(CheckBox checkBox) throws IOException, InterruptedException{
        Treatment treatment = treatmentMap.get(checkBox);
        if (treatment != null){
            handleTreatment(treatment);
        } 
        else {
            System.err.println("Fant ikke treatment for" + checkBox.getText());
        }
    }

/* 
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
    }*/

    private void handleTreatment(Treatment treatment) throws IOException, InterruptedException {
        List<Treatment> chosenTreatments = frontService.getChosenTreatments();
        if (chosenTreatments.contains(treatment)) {
            frontService.deleteTreatment(treatment.getName());
        }else {
            frontService.addTreatment(treatment);
        }
        //updateFile();
        handleCalculatePrice();
        try {
            handleShowOverview();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @FXML
    void handleCalculatePrice() throws IOException, InterruptedException {
        double price = frontService.calculateTotalPrice();
        String priceString = String.valueOf(price);
        totalPriceField.setText(priceString);
        System.out.println("Total price calculated: " + priceString);
    }

    @FXML
    void handleShowOverview() throws IOException, InterruptedException {
        overViewTextArea.setText(" ");
        List<Treatment> fileTreatments = frontService.getChosenTreatments();
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


}
