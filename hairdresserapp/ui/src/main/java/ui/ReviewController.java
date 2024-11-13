package ui;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

/**
 * Controller class for managing user reviews in the application.
 * 
 * This class handles loading existing reviews, displaying them,
 * and allowing users to submit new reviews.
 */
@Controller
public class ReviewController {

    /**
     * Service for handling review-related operations.
     */
    @Autowired
    private FrontReviewService reviewService;

    /**
     * TextArea for displaying the overview of all reviews.
     */
    @FXML
    TextArea overviewArea;

    /**
     * TextArea for users to input their reviews.
     */
    @FXML
    TextArea textField;

    /**
     * Initializes the controller after its root element has been completely processed.
     * 
     * Sets up the review service and loads existing reviews.
     *
     * @throws IOException if an I/O error occurs during initialization
     * @throws InterruptedException if the operation is interrupted
     */
    @FXML
    public void initialize() throws IOException, InterruptedException {
        if (this.reviewService == null) {
            this.reviewService = new FrontReviewService();
        }
        loadReviews();
    }

    /**
     * Sets the FrontReviewService instance for this controller.
     * 
     * This method allows dependency injection of the review service.
     *
     * @param reviewService the FrontReviewService to set
     */
    public void setReviewService(FrontReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Loads and displays all existing reviews in the overviewArea TextArea.
     * 
     * Retrieves reviews from the FrontReviewService and sets them in the UI.
     *
     * @throws IOException if an I/O error occurs while loading reviews
     * @throws InterruptedException if the operation is interrupted
     */
    @FXML
    public void loadReviews() throws IOException, InterruptedException {
        String text = "";
        try {
            text = this.reviewService.getReviews();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.overviewArea.setText("");
        this.overviewArea.setText(text);
    }

    /**
     * Sends a new review entered by the user.
     * 
     * Retrieves the text from the textField, adds it as a new review via the FrontReviewService,
     * and reloads the reviews to reflect the new entry.
     *
     * @throws IOException if an I/O error occurs while sending the review
     * @throws InterruptedException if the operation is interrupted
     */
    @FXML
    public void sendReview() throws IOException, InterruptedException {
        String text = this.textField.getText();
        this.reviewService.addReview(text);
        loadReviews();
        this.textField.setText("");
    }
}

