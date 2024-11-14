package backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import core.PriceCalculator;
import core.Treatment;
import json.TreatmentFilehandling;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
class TreatmentServiceTest {

    @Mock
    private TreatmentFilehandling filehandling;

    @Mock
    private PriceCalculator priceCalculator;

    @InjectMocks
    private TreatmentService treatmentService;

    private Treatment treatment1;
    private Treatment treatment2;

    @BeforeEach
    void setUp() {
        treatment1 = new Treatment("Massage", 100);
        treatment2 = new Treatment("Facial", 150);
    }

    @Test
    void testGetAllTreatments() throws IOException {
       
        List<Treatment> mockTreatments = Arrays.asList(treatment1, treatment2);
        when(filehandling.readFromFile()).thenReturn(mockTreatments);

        
        List<Treatment> result = treatmentService.getAllTreatments();

        
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "There should be 2 treatments");
        assertEquals(treatment1, result.get(0), "First treatment should match");
        assertEquals(treatment2, result.get(1), "Second treatment should match");
        verify(filehandling, times(1)).readFromFile();
    }

    @Test
    void testGetAllTreatments_ThrowsIOException() throws IOException {
        
        when(filehandling.readFromFile()).thenThrow(new IOException("File not found"));

        
        IOException exception = assertThrows(IOException.class, () -> {
            treatmentService.getAllTreatments();
        }, "Expected getAllTreatments to throw IOException");

        assertEquals("File not found", exception.getMessage(), "Exception message should match");
        verify(filehandling, times(1)).readFromFile();
    }

    @Test
    void testFindByName_Found() throws IOException {
        
        List<Treatment> mockTreatments = Arrays.asList(treatment1, treatment2);
        when(filehandling.readFromFile()).thenReturn(mockTreatments);

        
        Optional<Treatment> result = treatmentService.findByName("Massage");

        
        assertTrue(result.isPresent(), "Treatment should be found");
        assertEquals(treatment1, result.get(), "Found treatment should match");
        verify(filehandling, times(1)).readFromFile();
    }

    @Test
    void testFindByName_NotFound() throws IOException {
        
        List<Treatment> mockTreatments = Arrays.asList(treatment1, treatment2);
        when(filehandling.readFromFile()).thenReturn(mockTreatments);

        
        Optional<Treatment> result = treatmentService.findByName("Haircut");

        
        assertFalse(result.isPresent(), "Treatment should not be found");
        verify(filehandling, times(1)).readFromFile();
    }

    @Test
    void testAddTreatment() throws IOException {
        
        doNothing().when(filehandling).writeToFile(treatment1);

        
        treatmentService.addTreatment(treatment1);

        
        verify(filehandling, times(1)).writeToFile(treatment1);
    }

    @Test
    void testAddTreatment_ThrowsIOException() throws IOException {
        
        doThrow(new IOException("Write failed")).when(filehandling).writeToFile(treatment1);

        
        IOException exception = assertThrows(IOException.class, () -> {
            treatmentService.addTreatment(treatment1);
        }, "Expected addTreatment to throw IOException");

        assertEquals("Write failed", exception.getMessage(), "Exception message should match");
        verify(filehandling, times(1)).writeToFile(treatment1);
    }

    @Test
    void testDeleteTreatment() throws IOException {
        
        List<Treatment> mockTreatments = Arrays.asList(treatment1, treatment2);
        List<Treatment> updatedTreatments = Arrays.asList(treatment2); // Assume treatment1 is deleted

        when(filehandling.readFromFile()).thenReturn(mockTreatments);
        doNothing().when(filehandling).reset();
        doNothing().when(filehandling).writeToFile(any(Treatment.class));

        
        treatmentService.deleteTreatment("Massage");

        
        verify(filehandling, times(1)).readFromFile();
        verify(filehandling, times(1)).reset();
        verify(filehandling, times(1)).writeToFile(treatment2);
        verify(filehandling, times(0)).writeToFile(treatment1);
    }

    @Test
    void testDeleteTreatment_ThrowsIOException_OnRead() throws IOException {
        
        when(filehandling.readFromFile()).thenThrow(new IOException("Read failed"));

        
        IOException exception = assertThrows(IOException.class, () -> {
            treatmentService.deleteTreatment("Massage");
        }, "Expected deleteTreatment to throw IOException");

        assertEquals("Read failed", exception.getMessage(), "Exception message should match");
        verify(filehandling, times(1)).readFromFile();
        verify(filehandling, times(0)).reset();
        verify(filehandling, times(0)).writeToFile(any(Treatment.class));
    }

    @Test
    void testDeleteTreatment_ThrowsIOException_OnWrite() throws IOException {
        
        List<Treatment> mockTreatments = Arrays.asList(treatment1, treatment2);
        when(filehandling.readFromFile()).thenReturn(mockTreatments);
        doNothing().when(filehandling).reset();
        doThrow(new IOException("Write failed")).when(filehandling).writeToFile(treatment2);

        
        IOException exception = assertThrows(IOException.class, () -> {
            treatmentService.deleteTreatment("Massage");
        }, "Expected deleteTreatment to throw IOException");

        assertEquals("Write failed", exception.getMessage(), "Exception message should match");
        verify(filehandling, times(1)).readFromFile();
        verify(filehandling, times(1)).reset();
        verify(filehandling, times(1)).writeToFile(treatment2);
    }

    @Test
    void testCalculateTotalPrice() throws IOException {
        
        List<Treatment> mockTreatments = Arrays.asList(treatment1, treatment2);
        when(filehandling.readFromFile()).thenReturn(mockTreatments);
        when(priceCalculator.CalculateTotalPrice(mockTreatments)).thenReturn(250.0);

        
        double totalPrice = treatmentService.calculateTotalPrice();

        
        assertEquals(250.0, totalPrice, 0.001, "Total price should match");
        verify(filehandling, times(1)).readFromFile();
        verify(priceCalculator, times(1)).CalculateTotalPrice(mockTreatments);
    }

    @Test
    void testCalculateTotalPrice_ThrowsIOException() throws IOException {
        
        when(filehandling.readFromFile()).thenThrow(new IOException("Read failed"));

        
        IOException exception = assertThrows(IOException.class, () -> {
            treatmentService.calculateTotalPrice();
        }, "Expected calculateTotalPrice to throw IOException");

        assertEquals("Read failed", exception.getMessage(), "Exception message should match");
        verify(filehandling, times(1)).readFromFile();
        verify(priceCalculator, times(0)).CalculateTotalPrice(anyList());
    }

    @Test
    void testCalculateTotalPrice_ThrowsRuntimeException_OnPriceCalculation() throws IOException {
        
        List<Treatment> mockTreatments = Arrays.asList(treatment1, treatment2);
        when(filehandling.readFromFile()).thenReturn(mockTreatments);
        when(priceCalculator.CalculateTotalPrice(mockTreatments)).thenThrow(new RuntimeException("Calculation error"));

        
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            treatmentService.calculateTotalPrice();
        }, "Expected calculateTotalPrice to throw RuntimeException");

        assertEquals("Calculation error", exception.getMessage(), "Exception message should match");
        verify(filehandling, times(1)).readFromFile();
        verify(priceCalculator, times(1)).CalculateTotalPrice(mockTreatments);
    }
}

