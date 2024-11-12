package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;


public class FrontReviewService {
    private static final String BACKEND_URL = "http://localhost:8080/api/reviews";
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    
    public FrontReviewService(HttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = httpClient;
        this.objectMapper = objectMapper;
    }

    
    public FrontReviewService() {
        this(HttpClient.newHttpClient(), new ObjectMapper());
    }


    public String getReviews() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(BACKEND_URL)).GET().build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public void addReview(String review) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(BACKEND_URL))
                        .POST(HttpRequest.BodyPublishers.ofString(review)).build();
        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }
}
