package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import core.TimeSlot;

/**
 * Service class for handling booking-related operations with the backend.
 * This class communicates with the backend API to fetch all available time slots,
 * retrieve booked time slots, and book new time slots.
 */
public class FrontBookingService {

    /**
     * URL to the backend API for booking operations.
     */
    private static final String BACKEND_URL = "http://localhost:8080/api/booking";
    
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    /**
     * Default constructor that initializes HttpClient and ObjectMapper with default settings.
     */
    public FrontBookingService() {
        this(HttpClient.newHttpClient(), new ObjectMapper());
    }

    /**
     * Constructor to initialize FrontBookingService with specific HttpClient and ObjectMapper.
     * This is useful for testing with mock objects.
     *
     * @param httpClient   HTTP client for sending requests.
     * @param objectMapper Mapper for converting JSON to objects and vice versa.
     */
    public FrontBookingService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    
    /**
     * Retrieves all available time slots from the backend with GET request.
     *
     * @return A list of all available {@link TimeSlot} objects.
     * @throws IOException              If an I/O error occurs during communication with the backend.
     * @throws InterruptedException     If the thread is interrupted while waiting for a response.
     */
    public List<TimeSlot> getAllTimeSlots() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BACKEND_URL + "/allTimeSlots")).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<TimeSlot> list = objectMapper.readValue(response.body(), new TypeReference<List<TimeSlot>>() {});
        
        return list;
    }

    /**
     * Retrieves all booked time slots from the backend with GET request.
     *
     * @return A list of all booked {@link TimeSlot} objects.
     * @throws IOException              If an I/O error occurs during communication with the backend.
     * @throws InterruptedException     If the thread is interrupted while waiting for a response.
     */
    public List<TimeSlot> getBookedSlots() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BACKEND_URL)).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<TimeSlot> list = objectMapper.readValue(response.body(), new TypeReference<List<TimeSlot>>() {});
        
        return list;
    }

    /**
     * Books a specific time slot by sending a POST request to the backend.
     *
     * @param timeSlot The time slot to be booked.
     * @throws IOException              If an I/O error occurs during communication with the backend.
     * @throws InterruptedException     If the thread is interrupted while waiting for a response.
     */
    public void bookSlot(TimeSlot timeSlot) throws IOException, InterruptedException {
        String json = objectMapper.writeValueAsString(timeSlot);
        System.out.println(json);
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BACKEND_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json)).build();
        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }
    
}
