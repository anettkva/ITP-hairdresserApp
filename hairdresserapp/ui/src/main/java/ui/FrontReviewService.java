package ui;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Service class for handling review-related operations between the frontend and backend.
 * 
 * This class provides methods to retrieve and add user reviews by communicating with the backend API.
 */
public class FrontReviewService {
    
    /**
     * The base URL for the backend API related to reviews.
     */
    private static final String BACKEND_URL = "http://localhost:8080/api/reviews";
    
    /**
     * HTTP client used to send requests to the backend.
     */
    private final HttpClient httpClient;
    

    /**
     * Constructs a new FrontReviewService with the provided HttpClient and ObjectMapper.
     * 
     * @param httpClient the HttpClient to use for sending HTTP requests
     */
    public FrontReviewService(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Constructs a new FrontReviewService with default HttpClient instance.
     * 
     * This constructor is intended for production use where dependency injection is not required.
     */
    public FrontReviewService() {
        this(HttpClient.newHttpClient());
    }

    /**
     * Retrieves all user reviews from the backend.
     * 
     * Sends a GET request to the backend API to fetch all reviews and returns the response body as a string.
     *
     * @return a {@code String} containing all user reviews
     * @throws IOException if an I/O error occurs when sending or receiving
     * @throws InterruptedException if the operation is interrupted
     */
    public String getReviews() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    /**
     * Adds a new user review by sending it to the backend.
     * 
     * Sends a POST request with the review content to the backend API to store the new review.
     *
     * @param review the review text to add
     * @throws IOException if an I/O error occurs during the request
     * @throws InterruptedException if the operation is interrupted
     */
    public void addReview(String review) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BACKEND_URL))
                .POST(HttpRequest.BodyPublishers.ofString(review))
                .header("Content-Type", "application/json")
                .build();
        httpClient.send(request, HttpResponse.BodyHandlers.discarding());
    }
}
