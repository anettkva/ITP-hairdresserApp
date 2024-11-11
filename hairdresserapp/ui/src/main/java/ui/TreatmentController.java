package ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import core.Treatment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;




@Controller
public class TreatmentController {


    private List<Treatment> chosenTreatments; 

    @Autowired
    private FrontTreatmentService frontService;

   
    @FXML
    public CheckBox shortHairCut, longHairCut, stripes, color, styling, wash;

    private Map<CheckBox, Treatment> treatmentMap;

    

    @FXML 
    TextField totalPriceField; 

    @FXML 
    TextArea overViewTextArea;

    
    public TreatmentController() {
       this.frontService = new FrontTreatmentService();
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
        chosenTreatments = new ArrayList<>();

        treatmentMap = new HashMap<>();
        treatmentMap.put(shortHairCut, new Treatment("shortcut", 300));
        treatmentMap.put(longHairCut, new Treatment("longcut", 500));
        treatmentMap.put(stripes, new Treatment("stripes", 1500));
        treatmentMap.put(color, new Treatment("color", 2000));
        treatmentMap.put(styling, new Treatment("styling", 500));
        treatmentMap.put(wash, new Treatment("wash", 500));

        for (CheckBox checkBox : treatmentMap.keySet()){
            checkBox.setOnAction(event -> {
                try {
                    handleCheckBoxAction(checkBox);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }


    public void handleCheckBoxAction(CheckBox checkBox) throws IOException, InterruptedException{
        Treatment treatment = treatmentMap.get(checkBox);
        if (checkBox.isSelected()) {
            addTreatment(treatment);
        }
        else if (!checkBox.isSelected()){
            deleteTreatment(treatment);
        }
        else {
            System.err.println("Fant ikke treatment for" + checkBox.getText());
        }
    }


    public void addTreatment(Treatment treatment) throws IOException, InterruptedException { 
        frontService.addTreatment(treatment);
        handleCalculatePrice();
        handleShowOverview();
    
    }

    public void deleteTreatment(Treatment treatment) throws IOException, InterruptedException {
        frontService.deleteTreatment(treatment.getName());
        handleCalculatePrice();
        handleShowOverview();
    }


    @FXML
    void handleCalculatePrice() throws IOException, InterruptedException {
        double price = frontService.calculateTotalPrice();
        String priceString = String.valueOf(price);
        totalPriceField.setText(priceString);
    }

    @FXML
    void handleShowOverview() throws IOException, InterruptedException {
        overViewTextArea.setText("");
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
