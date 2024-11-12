package ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.TimeSlot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class FrontBookingServiceTest {

    @Mock
    private HttpClient mockHttpClient;

    @Mock
    private HttpResponse<String> mockHttpResponse;

    @Mock
    private HttpResponse<Void> mockVoidResponse;

    @Mock
    private ObjectMapper mockObjectMapper;

    @InjectMocks
    private FrontBookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingService = new FrontBookingService(mockHttpClient, mockObjectMapper);
    }

     public static TimeSlot createValidTimeSlot(int hoursAhead) throws IOException {
        LocalDateTime now = LocalDateTime.now().plusHours(hoursAhead).truncatedTo(ChronoUnit.HOURS);
        
        // Juster tiden til innenfor 08:00 til 15:00
        LocalTime startTime = now.toLocalTime();
        if (startTime.isBefore(LocalTime.of(8, 0))) {
            startTime = LocalTime.of(8, 0);
        } else if (startTime.isAfter(LocalTime.of(15, 0))) {
            startTime = LocalTime.of(15, 0);
        }
        
        LocalDateTime validStart = now.withHour(startTime.getHour()).withMinute(0).withSecond(0).withNano(0);
        
        // Hvis justeringen fører til at starttid er før nå, flytt til neste gyldige time
        if (validStart.isBefore(LocalDateTime.now())) {
            validStart = validStart.plusHours(1);
        }
        
        // Sørg for at starttid er på en hel time og innenfor 08:00 til 15:00
        return new TimeSlot(validStart);
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetBookedSlots_Success() throws IOException, InterruptedException {
         // Arrange
        String jsonResponse = "[" +
        "{\"start\":\"2024-12-31T10:00\",\"end\":\"2024-12-31T11:00\",\"booked\":false}," +
        "{\"start\":\"2024-12-31T12:00\",\"end\":\"2024-12-31T13:00\",\"booked\":false}" +
        "]";

        TimeSlot timeSlot1 = createValidTimeSlot(24); // 24 timer frem
        timeSlot1.setBooked(false);
        TimeSlot timeSlot2 = createValidTimeSlot(25); // 25 timer frem
        timeSlot2.setBooked(false);
        List<TimeSlot> expectedTimeSlots = Arrays.asList(timeSlot1, timeSlot2);

        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
            .thenReturn(mockHttpResponse);
        when(mockHttpResponse.body()).thenReturn(jsonResponse);
        when(mockObjectMapper.readValue(eq(jsonResponse), any(TypeReference.class)))
            .thenReturn(expectedTimeSlots);

        // Act
        List<TimeSlot> actualTimeSlots = bookingService.getBookedSlots();

        // Assert
        assertNotNull(actualTimeSlots);
        assertEquals(2, actualTimeSlots.size());
        assertEquals(expectedTimeSlots, actualTimeSlots);

        // Verifiserer at de riktige metodene ble kalt
        verify(mockHttpClient).send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString()));
        verify(mockObjectMapper).readValue(eq(jsonResponse), any(TypeReference.class));
        }

    @Test
    void testGetBookedSlots_HttpClientThrowsIOException() throws IOException, InterruptedException {
        // Arrange
        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
            .thenThrow(new IOException("Network error"));

        // Act & Assert
        IOException exception = assertThrows(IOException.class, () -> {
            bookingService.getBookedSlots();
        });

        assertEquals("Network error", exception.getMessage());

        // Verifiserer at send ble kalt, og readValue ikke ble kalt
        verify(mockHttpClient).send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString()));
        
    }

    @SuppressWarnings("unchecked")
    @Test
    void testGetBookedSlots_ObjectMapperThrowsJsonProcessingException() throws IOException, InterruptedException {
        // Arrange
        String jsonResponse = "Invalid JSON";
        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
            .thenReturn(mockHttpResponse);
        when(mockHttpResponse.body()).thenReturn(jsonResponse);
        when(mockObjectMapper.readValue(eq(jsonResponse), any(TypeReference.class)))
            .thenThrow(new JsonProcessingException("JSON parsing error") {});

        // Act & Assert
        JsonProcessingException exception = assertThrows(JsonProcessingException.class, () -> {
            bookingService.getBookedSlots();
        });

        assertEquals("JSON parsing error", exception.getMessage());

        // Verifiserer at de riktige metodene ble kalt
        verify(mockHttpClient).send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString()));
        verify(mockObjectMapper).readValue(eq(jsonResponse), any(TypeReference.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    void testBookSlot_Success() throws IOException, InterruptedException {
        // Arrange
        TimeSlot timeSlot = createValidTimeSlot(24); // 24 timer frem
        timeSlot.setBooked(false);
        String jsonRequest = "{\"start\":\"" + timeSlot.getStart() + "\"," +
                             "\"end\":\"" + timeSlot.getEnd() + "\"," +
                             "\"booked\":" + timeSlot.isBooked() + "}";

        when(mockObjectMapper.writeValueAsString(timeSlot)).thenReturn(jsonRequest);
        when(mockHttpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.discarding())))
                .thenReturn(mock(HttpResponse.class)); // send() returnerer HttpResponse<Void>, men vi kan returnere mockHttpResponse

        // Act
        bookingService.bookSlot(timeSlot);

        // Assert
        // Verifiserer at writeValueAsString ble kalt med riktig objekt
        verify(mockObjectMapper).writeValueAsString(timeSlot);

        // Capture HttpRequest for å verifisere innholdet
        ArgumentCaptor<HttpRequest> requestCaptor = ArgumentCaptor.forClass(HttpRequest.class);
        verify(mockHttpClient).send(requestCaptor.capture(), eq(HttpResponse.BodyHandlers.discarding()));
        HttpRequest capturedRequest = requestCaptor.getValue();

        assertEquals("http://localhost:8080/api/booking", capturedRequest.uri().toString());
        assertEquals("POST", capturedRequest.method());

        // Verifiserer at Content-Type header er satt til application/json
        assertTrue(capturedRequest.headers().map().containsKey("Content-Type"));
        assertTrue(capturedRequest.headers().map().get("Content-Type").contains("application/json"));

    }

    

    
}