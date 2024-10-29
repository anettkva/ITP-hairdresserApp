package ui;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import core.Treatment;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TreatmentControllerTest extends ApplicationTest{

    private TreatmentController treatmentController;
    private TextField field;
    private TextArea area;

    @Override
    public void start(final Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("HairdresserApp.fxml"));
        final Parent root = loader.load();
        this.treatmentController = loader.getController();
        this.field = treatmentController.getfield();
        this.area = treatmentController.getarea();
        stage.setScene(new Scene(root));
        stage.show();
    } 



    @BeforeEach
    public void setUp() {
    // You could either load the controller via FXML or instantiate it directly.
        treatmentController.initialize(); 
    }


    @Test
    public void testRemoveTreatment() {

        treatmentController.handleLongCut();
        treatmentController.handleLongCut();

        List<Treatment> chosenTreatments = treatmentController.getChosenTreatments();
        assertFalse(chosenTreatments.contains(new Treatment("Long hair cut", 500)), "Long hair cut should not be in the list after removal.");
    } 

    @Test 
    public void testCalculatePrice() {
        treatmentController.handleShortCut();

        assertEquals("300.0", field.getText());

        treatmentController.handleWash();

        assertEquals("800.0", field.getText());

        treatmentController.handleShortCut();
        treatmentController.handleWash();
    }

    @Test
    public void testShowOverview() {
        treatmentController.handleStyling();
        treatmentController.handleStripes();

        String stylingPrice = "500";  // Replace with actual values from your code if available
        String stylingDuration = "60";
        String stripesPrice = "1500";
        String stripesDuration = "180";

        String expectedText =   " Styling" + ": " + stylingPrice + " kr, Varighet (min): " + stylingDuration + "\n" +
                                "Stripes" + ": " + stripesPrice + " kr, Varighet (min): " + stripesDuration + "\n";
    
        assertEquals(expectedText, area.getText());

        treatmentController.handleStyling();
        treatmentController.handleStripes();
    }

}
