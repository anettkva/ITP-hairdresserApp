package ui;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;


import com.fasterxml.jackson.databind.ObjectMapper;

import core.Treatment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

public class FrontTreatmentServiceTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockHttpResponse;

    @Mock
    private HttpResponse<Void> mockVoidResponse;

    @InjectMocks
    private FrontTreatmentService frontTreatmentService;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        objectMapper = new ObjectMapper();
        frontTreatmentService = new FrontTreatmentService(mockHttpClient);
    }

    @Test
    public void testGetChosenTreatments() throws IOException, InterruptedException {
        
        List<Treatment> mockTreatments = Arrays.asList(
                new Treatment("shortcut", 300),
                new Treatment("color", 2000)
        );
        String jsonResponse = objectMapper.writeValueAsString(mockTreatments);

        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(mockHttpResponse);
        when(mockHttpResponse.body()).thenReturn(jsonResponse);

        
        List<Treatment> result = frontTreatmentService.getChosenTreatments();

        
        assertEquals(2, result.size());
        assertEquals("shortcut", result.get(0).getName());
        assertEquals(300, result.get(0).getPrice());
        assertEquals("color", result.get(1).getName());
        assertEquals(2000, result.get(1).getPrice());

        // Verifiser at riktig forespørsel ble sendt
        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockHttpClient).send(requestCaptor.capture(), eq(HttpResponse.BodyHandlers.ofString()));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("http://localhost:8080/api/treatments", capturedRequest.uri().toString());
        assertEquals("GET", capturedRequest.method());
    }

    @Test
    public void testAddTreatment() throws IOException, InterruptedException {
        
        Treatment treatment = new Treatment("wash", 500);
        

        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.discarding())))
                .thenReturn(mockVoidResponse);

        
        frontTreatmentService.addTreatment(treatment);

        
        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockHttpClient).send(requestCaptor.capture(), eq(HttpResponse.BodyHandlers.discarding()));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("http://localhost:8080/api/treatments", capturedRequest.uri().toString());
        assertEquals("POST", capturedRequest.method());
        assertEquals("application/json", capturedRequest.headers().firstValue("Content-Type").orElse(""));
        
    }

    @Test
    public void testDeleteTreatment() throws IOException, InterruptedException {
        
        String treatmentName = "color";
        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.discarding())))
                .thenReturn(mockVoidResponse);

        
        frontTreatmentService.deleteTreatment(treatmentName);

        
        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockHttpClient).send(requestCaptor.capture(), eq(HttpResponse.BodyHandlers.discarding()));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("http://localhost:8080/api/treatments/color", capturedRequest.uri().toString());
        assertEquals("DELETE", capturedRequest.method());
    }

    @Test
    public void testCalculateTotalPrice() throws IOException, InterruptedException {
        
        String mockPrice = "2500.0";

        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenReturn(mockHttpResponse);
        when(mockHttpResponse.body()).thenReturn(mockPrice);

        
        double totalPrice = frontTreatmentService.calculateTotalPrice();

        
        assertEquals(2500.0, totalPrice);

        // Verifiser at riktig forespørsel ble sendt
        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockHttpClient).send(requestCaptor.capture(), eq(HttpResponse.BodyHandlers.ofString()));
        HttpRequest capturedRequest = requestCaptor.getValue();
        assertEquals("http://localhost:8080/api/treatments/calculateTotalPrice", capturedRequest.uri().toString());
        assertEquals("POST", capturedRequest.method());
    }

    
}
