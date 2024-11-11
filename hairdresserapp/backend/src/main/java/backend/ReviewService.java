package backend;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import core.ReviewFilehandling;

public class ReviewService {
    private ReviewFilehandling filehandling;


    @Autowired
    public ReviewService() {
        this.filehandling = new ReviewFilehandling();
    }
    
    public String getReviews() throws IOException {
        return filehandling.readFromFile();
        
    }

    public void addReview(String review) throws IOException {
        try {
            filehandling.writeToFile(review);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
    } 
}
