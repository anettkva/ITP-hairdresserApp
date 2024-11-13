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

public class FrontBookingService {
    private static final String BACKEND_URL = "http://localhost:8080/api/booking";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public FrontBookingService() {
        this(HttpClient.newHttpClient(), new ObjectMapper());
    }

    public FrontBookingService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    

    public List<TimeSlot> getAllTimeSlots() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BACKEND_URL + "/allTimeSlots")).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<TimeSlot> list = objectMapper.readValue(response.body(), new TypeReference<List<TimeSlot>>() {});
        
        return list;
    }

    public List<TimeSlot> getBookedSlots() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BACKEND_URL)).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<TimeSlot> list = objectMapper.readValue(response.body(), new TypeReference<List<TimeSlot>>() {});
        
        return list;
    }

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
