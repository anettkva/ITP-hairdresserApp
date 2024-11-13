package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Treatment;

/**
 * Service class for handling treatment-related operations between the frontend and backend.
 * 
 * This class provides methods to retrieve, add, delete treatments, and calculate the total price
 * by communicating with the backend API.
 */
@Service
public class FrontTreatmentService {
    
    /**
     * The base URL for the backend API related to treatments.
     */
    private static final String BACKEND_URL = "http://localhost:8080/api/treatments";
    
    /**
     * HTTP client used to send requests to the backend.
     */
    private final HttpClient httpClient;
    
    /**
     * Object mapper for JSON serialization and deserialization.
     */
    private final ObjectMapper objectMapper;

    /**
     * Constructs a new FrontTreatmentService with the provided HttpClient.
     * 
     * @param httpClient the HttpClient to use for sending HTTP requests
     */
    public FrontTreatmentService(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Constructs a new FrontTreatmentService with a default HttpClient.
     * 
     * This constructor is intended for production use where dependency injection is not required.
     */
    // Standard konstruktør for produksjon (uten injeksjon)
    public FrontTreatmentService() {
        this(HttpClient.newHttpClient());
    }

    /**
     * Retrieves the list of chosen treatments from the backend.
     * 
     * Sends a GET request to the backend API to fetch all treatments and parses the response into a list.
     *
     * @return a list of {@link Treatment} objects representing the chosen treatments
     * @throws IOException if an I/O error occurs when sending or receiving
     * @throws InterruptedException if the operation is interrupted
     */
    public List<Treatment> getChosenTreatments() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Treatment> list = objectMapper.readValue(response.body(), new TypeReference<List<Treatment>>() {});
        System.out.println(list);
        return list;
    }

    /**
     * Adds a new treatment by sending it to the backend.
     * 
     * Serializes the provided {@link Treatment} object into JSON and sends a POST request to the backend API.
     *
     * @param treatment the {@link Treatment} object to add
     * @throws IOException if an I/O error occurs during serialization or sending the request
     * @throws InterruptedException if the operation is interrupted
     */
    public void addTreatment(Treatment treatment) throws IOException, InterruptedException {
        String json = objectMapper.writeValueAsString(treatment);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(json))
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }
    
    /**
     * Deletes a treatment by its name by sending a DELETE request to the backend.
     * 
     * Constructs the appropriate URL with the treatment name and sends a DELETE request to remove it.
     *
     * @param name the name of the treatment to delete
     * @throws IOException if an I/O error occurs when sending the request
     * @throws InterruptedException if the operation is interrupted
     */
    public void deleteTreatment(String name) throws IOException, InterruptedException {
        String url = BACKEND_URL + "/" + name;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }

    /**
     * Calculates the total price of all selected treatments by communicating with the backend.
     * 
     * Sends a POST request to the backend API endpoint responsible for calculating the total price.
     *
     * @return the total price as a {@code double}
     * @throws IOException if an I/O error occurs when sending or receiving
     * @throws InterruptedException if the operation is interrupted
     */
    public double calculateTotalPrice() throws IOException, InterruptedException {
        String url = BACKEND_URL + "/calculateTotalPrice";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return Double.parseDouble(response.body());
    }
}

