package ui;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.Treatment;

public class TreatmentControllerTest {

    private TreatmentController treatmentController;

    @BeforeEach
    public void setUp() {
        treatmentController = new TreatmentController();
        treatmentController.initialize(); 
    }

    @Test
    public void testRemoveTreatment() {
        
        treatmentController.handleLongCut();
        treatmentController.handleLongCut();

        List<Treatment> chosenTreatments = treatmentController.getChosenTreatments();
        assertFalse(chosenTreatments.contains(new Treatment("Long hair cut", 500, 90)), "Long hair cut should not be in the list after removal.");
    }
}
