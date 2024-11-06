package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.TimeSlot;

public class FrontBookingService {
    private static final String BACKEND_URL = "http://localhost:8080/api/booking";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public FrontBookingService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<TimeSlot> getBookedSlots() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BACKEND_URL)).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<TimeSlot> list = objectMapper.readValue(response.body(), new TypeReference<List<TimeSlot>>() {});
        
        return list;
    }

    public void bookSlot(TimeSlot timeSlot) throws IOException, InterruptedException {
        String json = objectMapper.writeValueAsString(timeSlot);
        HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BACKEND_URL))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json)).build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200 && response.statusCode() != 201) {
            throw new IOException("Feilet å booke slot, statuskode: " + response.statusCode());
        }
    }
    
}
