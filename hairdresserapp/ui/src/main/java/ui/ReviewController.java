package ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ReviewController {

    @Autowired
    private FrontReviewService reviewService;

    @FXML
    TextArea overviewArea;

    @FXML
    TextArea textField;

    @FXML
    public void initialize() throws IOException, InterruptedException {
        if (this.reviewService == null) {
            this.reviewService = new FrontReviewService();
        }
        loadReviews();
    }

    public void setReviewService(FrontReviewService reviewService) {
        this.reviewService = reviewService;
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
        this.overviewArea.setText("");
        this.overviewArea.setText(text);
    }

    @FXML
    public void sendReview() throws IOException, InterruptedException {
        String text = this.textField.getText();
        this.reviewService.addReview(text);;
        loadReviews();
        this.textField.setText("");
    }
}
