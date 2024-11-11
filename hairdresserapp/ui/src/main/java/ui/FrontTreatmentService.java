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
@Service
public class FrontTreatmentService {
    private static final String BACKEND_URL = "http://localhost:8080/api/treatments";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    // Injekt HttpClient via konstruktør
    public FrontTreatmentService(HttpClient httpClient) {
        this.httpClient = httpClient;
        this.objectMapper = new ObjectMapper();
    }

    // Standard konstruktør for produksjon (uten injeksjon)
    public FrontTreatmentService() {
        this(HttpClient.newHttpClient());
    }

 
    public List<Treatment> getChosenTreatments() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BACKEND_URL)).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        List<Treatment> list = objectMapper.readValue(response.body(), new TypeReference<List<Treatment>>() {});
        System.out.println(list);
        return list;
    }

    public void addTreatment(Treatment treatment) throws IOException, InterruptedException {
        String json = objectMapper.writeValueAsString(treatment);
        HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BACKEND_URL)).header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(json)).build();
        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }
    


    public void deleteTreatment(String name) throws IOException, InterruptedException{
        String url = BACKEND_URL + "/" + name;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }


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
