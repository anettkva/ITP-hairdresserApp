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

/**
 * Controller class for managing treatments in the application.
 * 
 * This class handles user interactions related to selecting treatments,
 * calculating total prices, displaying overviews, and navigating to the booking screen.
 */
@Controller
public class TreatmentController {

    /**
     * List of treatments chosen by the user.
     */
    private List<Treatment> chosenTreatments; 

    /**
     * Service for handling treatment-related operations.
     */
    @Autowired
    private FrontTreatmentService frontService;

    /**
     * CheckBoxes representing different treatment options.
     */
    @FXML
    public CheckBox shortHairCut, longHairCut, stripes, color, styling, wash;

    /**
     * Mapping between CheckBoxes and their corresponding Treatment objects.
     */
    private Map<CheckBox, Treatment> treatmentMap;

    /**
     * TextField displaying the total price of selected treatments.
     */
    @FXML 
    TextField totalPriceField; 

    /**
     * TextArea displaying an overview of selected treatments.
     */
    @FXML 
    TextArea overViewTextArea;

    /**
     * Constructs a new TreatmentController and initializes the FrontTreatmentService.
     */
    public TreatmentController() {
       this.frontService = new FrontTreatmentService();
    }

    /**
     * Retrieves the list of chosen treatments.
     * 
     * @return a list of selected Treatment objects
     */
    public List<Treatment> getChosenTreatments() {
        return chosenTreatments;
    }
    
    /**
     * Retrieves the TextField for displaying the total price.
     * 
     * @return the totalPriceField TextField
     */
    public TextField getTotalPriceField() {
        return totalPriceField;
    }
    
    /**
     * Retrieves the TextArea for displaying the overview of treatments.
     * 
     * @return the overViewTextArea TextArea
     */
    public TextArea getOverViewTextArea() {
        return overViewTextArea;
    }

    /**
     * Initializes the controller after its root element has been completely processed.
     * 
     * Sets up the treatment mappings and assigns event handlers to each CheckBox.
     */
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

    /**
     * Handles the action when a treatment CheckBox is toggled.
     * 
     * Adds or removes the corresponding Treatment based on the CheckBox state,
     * then updates the total price and overview.
     * 
     * @param checkBox the CheckBox that was toggled
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public void handleCheckBoxAction(CheckBox checkBox) throws IOException, InterruptedException{
        Treatment treatment = treatmentMap.get(checkBox);
        if (checkBox.isSelected()) {
            addTreatment(treatment);
        }
        else if (!checkBox.isSelected()){
            deleteTreatment(treatment);
        }
        else {
            System.err.println("Did not find treatment for " + checkBox.getText());
        }
    }

    /**
     * Adds a Treatment to the chosen treatments list and updates the UI.
     * 
     * @param treatment the Treatment to add
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public void addTreatment(Treatment treatment) throws IOException, InterruptedException { 
        frontService.addTreatment(treatment);
        handleCalculatePrice();
        handleShowOverview();
    
    }

    /**
     * Removes a Treatment from the chosen treatments list and updates the UI.
     * 
     * @param treatment the Treatment to remove
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    public void deleteTreatment(Treatment treatment) throws IOException, InterruptedException {
        frontService.deleteTreatment(treatment.getName());
        handleCalculatePrice();
        handleShowOverview();
    }

    /**
     * Calculates the total price of selected treatments and updates the totalPriceField.
     * 
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @FXML
    void handleCalculatePrice() throws IOException, InterruptedException {
        double price = frontService.calculateTotalPrice();
        String priceString = String.valueOf(price);
        totalPriceField.setText(priceString);
    }

    /**
     * Displays an overview of selected treatments in the overViewTextArea.
     * 
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the operation is interrupted
     */
    @FXML
    void handleShowOverview() throws IOException, InterruptedException {
        overViewTextArea.setText("");
        List<Treatment> fileTreatments = frontService.getChosenTreatments();
        for (Treatment t : fileTreatments) {
            overViewTextArea.appendText(t.getName() + ": " + t.getPrice() + " kr, Duration (min): " + t.getduration() + "\n" );
        }
    }
    
    /**
     * Handles the action when the booking button is clicked.
     * 
     * Loads the Booking.fxml layout, creates a new stage, sets the scene,
     * and displays the booking window.
     * 
     * @throws IOException if the FXML file cannot be loaded
     */
    @FXML
    void handleBookingButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Booking.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

}

