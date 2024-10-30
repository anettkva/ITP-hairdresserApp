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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TreatmentControllerTest extends ApplicationTest{

    private TreatmentController treatmentController;
    private TextField totalPriceField;
    private TextArea overViewTextArea;
    private CheckBox shortHairCut, longHairCut, stripes, color, styling, wash;

    @Override
    public void start(final Stage stage) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("HairdresserApp.fxml"));
        final Parent root = loader.load();
        this.treatmentController = loader.getController();
        this.totalPriceField = treatmentController.getTotalPriceField();
        this.overViewTextArea = treatmentController.getOverViewTextArea();

        this.shortHairCut = (CheckBox) root.lookup("#shortHairCut");
        this.longHairCut = (CheckBox) root.lookup("#longHairCut");
        this.stripes = (CheckBox) root.lookup("#stripes");
        this.color = (CheckBox) root.lookup("#color");
        this.styling = (CheckBox) root.lookup("#styling");
        this.wash = (CheckBox) root.lookup("#wash");

        stage.setScene(new Scene(root));
        stage.show();
    } 



    @BeforeEach
    public void setUp() {
        if (treatmentController != null) {
            treatmentController.initialize();
        } else {
            throw new IllegalStateException("TreatmentController ble ikke initilialisert riktig");
        } 
    }


    @Test
    public void testRemoveTreatment() {

        assertNotNull(longHairCut, "longHaircut checkbox should be properly loaded from FXML");

        longHairCut.setSelected(true);
        longHairCut.fire();
        longHairCut.setSelected(false);
        longHairCut.fire();

        List<Treatment> chosenTreatments = treatmentController.getChosenTreatments();
        assertFalse(chosenTreatments.contains(new Treatment("Long hair cut", 500)), "Long hair cut should not be in the list after removal.");
    } 

    @Test 
    public void testCalculatePrice() {

        assertNotNull(shortHairCut, "shortHairCut checkbox should be properly loaded from FXML");

        clickOn("#shortHairCut");
        treatmentController.handleCalculatePrice();

        assertEquals("300.0", totalPriceField.getText());

        clickOn("#wash");
        treatmentController.handleCalculatePrice();

        assertEquals("800.0", totalPriceField.getText());

        clickOn("#wash");

        clickOn("#shortHairCut");
        
    }

    @Test
    public void testShowOverview() throws IOException {
        clickOn("#styling");
        clickOn("#stripes");

        treatmentController.handleShowOverview();

        String stylingPrice = "500";
        String stylingDuration = "60";
        String stripesPrice = "1500";
        String stripesDuration = "60";

        String expectedText =   " Styling" + ": " + stylingPrice + " kr, Varighet (min): " + stylingDuration + "\n" +
                                "Stripes" + ": " + stripesPrice + " kr, Varighet (min): " + stripesDuration + "\n";
    
        assertEquals(expectedText, overViewTextArea.getText());

        clickOn("#styling");
        clickOn("#stripes");
    }

}
