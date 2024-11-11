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


@RestController
@RequestMapping("/api/reviews")
public class RestReviewController {
    private static final Logger logger = LoggerFactory.getLogger(RestBookingController.class);

    
    private final ReviewService reviewService;

    @Autowired
    public RestReviewController() {
        this.reviewService = new ReviewService();
    }

    @GetMapping
    public String getReviews() throws IOException {
        logger.info("GET request received for reviews" );
        return this.reviewService.getReviews();
        
    }

    @PostMapping
    public void addReview(@RequestBody String review)  {
        logger.info("POST request received for review");
        try {
            this.reviewService.addReview(review);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
