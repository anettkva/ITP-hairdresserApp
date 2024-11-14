package ui;



import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;





import core.Treatment;


import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


@ExtendWith(MockitoExtension.class)
public class TreatmentControllerTest {

    @InjectMocks
    private TreatmentController treatmentController;

    @Mock
    private FrontTreatmentService frontService;

    @Mock
    private CheckBox shortHairCut;

    @Mock
    private CheckBox longHairCut;

    @Mock
    private CheckBox stripes;

    @Mock
    private CheckBox color;

    @Mock
    private CheckBox styling;

    @Mock
    private CheckBox wash;

    @Mock
    private TextField totalPriceField;

    @Mock
    private TextArea overViewTextArea;

    @BeforeEach
    public void setUp() throws Exception {
         // Initialiser ekte CheckBox-instans
         shortHairCut = new CheckBox();
         longHairCut = new CheckBox();
         stripes = new CheckBox();
         color = new CheckBox();
         styling = new CheckBox();
         wash = new CheckBox();
 
         // Sett de ekte CheckBox-instansene i kontrolløren
         treatmentController.shortHairCut = shortHairCut;
         treatmentController.longHairCut = longHairCut;
         treatmentController.stripes = stripes;
         treatmentController.color = color;
         treatmentController.styling = styling;
         treatmentController.wash = wash;
         treatmentController.totalPriceField = totalPriceField;
         treatmentController.overViewTextArea = overViewTextArea;
 
         // Kall initialize-metoden manuelt
         treatmentController.initialize();
    }

    @BeforeAll
    public static void initJFX() {
        JavaFxTestInitializer.initializeJavaFX();
    }

    @Test
    public void testAddTreatmentWhenCheckBoxSelected() throws IOException, InterruptedException {
        
        shortHairCut.setSelected(true);
        Treatment treatment = new Treatment("shortcut", 300);
        when(frontService.calculateTotalPrice()).thenReturn(300.0);
        when(frontService.getChosenTreatments()).thenReturn(Arrays.asList(treatment));

        
        treatmentController.handleCheckBoxAction(shortHairCut);

        
        verify(frontService).addTreatment(argThat(t -> 
        t.getName().equals("shortcut") && t.getPrice() == 300.0
        ));
        verify(frontService).calculateTotalPrice();
        verify(frontService).getChosenTreatments();
        verify(totalPriceField).setText("300.0");
        verify(overViewTextArea).setText("");
        verify(overViewTextArea).appendText("shortcut: 300 kr, Duration (min): " + treatment.getduration() + "\n");


    }

    @Test
    public void testDeleteTreatmentWhenCheckBoxDeselected() throws IOException, InterruptedException {
        
        longHairCut.setSelected(false);
        Treatment treatment = new Treatment("longcut", 500);
        when(frontService.calculateTotalPrice()).thenReturn(0.0);
        when(frontService.getChosenTreatments()).thenReturn(Arrays.asList());

        
        treatmentController.handleCheckBoxAction(longHairCut);

        
        verify(frontService).deleteTreatment("longcut");
        verify(frontService).calculateTotalPrice();
        verify(frontService).getChosenTreatments();
        verify(totalPriceField).setText("0.0");
        verify(overViewTextArea).setText("");
    }

    @Test
    public void testHandleCalculatePrice() throws IOException, InterruptedException {
        
        when(frontService.calculateTotalPrice()).thenReturn(2000.0);

        
        treatmentController.handleCalculatePrice();

       
        verify(frontService).calculateTotalPrice();
        verify(totalPriceField).setText("2000.0");
    }

    @Test
    public void testHandleShowOverview() throws IOException, InterruptedException {
        
        Treatment treatment1 = new Treatment("color", 2000);
        Treatment treatment2 = new Treatment("wash", 500);
        List<Treatment> treatments = Arrays.asList(treatment1, treatment2);
        when(frontService.getChosenTreatments()).thenReturn(treatments);

        
        treatmentController.handleShowOverview();

        
        verify(overViewTextArea).setText("");
        verify(overViewTextArea).appendText("color: 2000 kr, Duration (min): " + treatment1.getduration() + "\n");
        verify(overViewTextArea).appendText("wash: 500 kr, Duration (min): " + treatment2.getduration() + "\n");
    }
}

    

