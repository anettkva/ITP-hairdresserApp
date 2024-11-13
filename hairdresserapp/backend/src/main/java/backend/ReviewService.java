package backend;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.ReviewFilehandling;

/**
 * Service class for managing user reviews.
 * 
 * This class provides methods to retrieve all reviews and add new reviews.
 * It interacts with the {@link ReviewFilehandling} class to handle file operations
 * related to storing and retrieving reviews.
 */
@Service
public class ReviewService {
    
    /**
     * Handles file operations related to reviews.
     */
    private ReviewFilehandling filehandling;

    /**
     * Constructs a new {@link ReviewService} instance and initializes the
     * {@link ReviewFilehandling}.
     * 
     * This constructor is annotated with {@link Autowired} to allow Spring to
     * manage its dependencies. It initializes the {@link ReviewFilehandling}
     * used for reading and writing review data.
     */
    @Autowired
    public ReviewService() {
        this.filehandling = new ReviewFilehandling();
    }
    
    /**
     * Retrieves all reviews from the storage.
     * 
     * This method reads all reviews using the {@link ReviewFilehandling} class.
     *
     * @return a {@link String} containing all user reviews
     * @throws IOException if an I/O error occurs during reading from the file
     */
    public String getReviews() throws IOException {
        return filehandling.readFromFile();
    }

    /**
     * Adds a new review to the storage.
     * 
     * This method writes the provided review text to the storage using the
     * {@link ReviewFilehandling} class. It handles any {@link IOException}
     * that may occur during the writing process by printing the stack trace.
     *
     * @param review the review text to add
     * @throws IOException if an I/O error occurs during writing to the file
     */
    public void addReview(String review) throws IOException {
        try {
            filehandling.writeToFile(review);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    } 
}

