package ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.FileNotFoundException;
import java.io.IOException;


public class ReviewController {
    private FrontReviewService reviewService;

    @FXML
    TextArea overveiwArea;

    @FXML
    TextArea textField;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        this.reviewService = new FrontReviewService();
        loadReviews();
    }

    @FXML
    public void loadReviews() throws IOException, InterruptedException {
        String text = "";
        try {
            text = this.reviewService.getReviews();
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.overveiwArea.setText("");
        this.overveiwArea.setText(text);
    }

    @FXML
    public void sendReview() throws IOException, InterruptedException {
        String text = this.textField.getText();
        this.reviewService.addReview(text);;
        loadReviews();
    }
}
