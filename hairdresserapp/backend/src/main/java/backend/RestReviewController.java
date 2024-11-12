package backend;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing user reviews.
 * 
 * This controller provides endpoints to retrieve all reviews and add new reviews.
 * It interacts with the {@link ReviewService} to handle business logic related to reviews.
 */
@RestController
@RequestMapping("/api/reviews")
public class RestReviewController {
    
    /**
     * Logger instance for logging information and debugging purposes.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestBookingController.class);

    /**
     * Service class for handling review-related operations.
     */
    private final ReviewService reviewService;

    /**
     * Constructs a new {@link RestReviewController} instance and initializes the
     * {@link ReviewService}.
     * 
     * This constructor is annotated with {@link Autowired} to allow Spring to manage its dependencies.
     */
    @Autowired
    public RestReviewController() {
        this.reviewService = new ReviewService();
    }

    /**
     * Handles GET requests to retrieve all reviews.
     * 
     * This endpoint responds to GET requests at {@code /api/reviews} by returning a string
     * containing all user reviews.
     * 
     * @return a {@link String} containing all user reviews
     * @throws IOException if an I/O error occurs during retrieval of reviews
     */
    @GetMapping
    public String getReviews() throws IOException {
        logger.info("GET request received for reviews");
        return this.reviewService.getReviews();
    }

    /**
     * Handles POST requests to add a new review.
     * 
     * This endpoint responds to POST requests at {@code /api/reviews} by adding a new
     * review to the storage.
     * 
     * @param review the review text to add, received in the request body
     */
    @PostMapping
    public void addReview(@RequestBody String review) {
        logger.info("POST request received for review");
        try {
            this.reviewService.addReview(review);
        } catch (IOException e) {
            logger.error("Error adding review: {}", e.getMessage());
        }
    }
}

