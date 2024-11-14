package backend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedConstruction;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import core.ReviewFilehandling;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
       
    }

    @Test
    public void testGetReviews_Success() throws IOException {
        String expectedReviews = "Great service!\nLoved it!";

        try (MockedConstruction<ReviewFilehandling> mocked = Mockito.mockConstruction(ReviewFilehandling.class,
                (mock, context) -> {
                    when(mock.readFromFile()).thenReturn(expectedReviews);
                })) {
            
            reviewService = new ReviewService(); 
            String actualReviews = reviewService.getReviews();

            assertEquals(expectedReviews, actualReviews);
            ReviewFilehandling constructedMock = mocked.constructed().get(0);
            verify(constructedMock, times(1)).readFromFile();
        }
    }

    @Test
    public void testAddReview_Success() throws IOException {
        String newReview = "Excellent experience!";

        try (MockedConstruction<ReviewFilehandling> mocked = Mockito.mockConstruction(ReviewFilehandling.class,
                (mock, context) -> {
                    doNothing().when(mock).writeToFile(newReview);
                })) {
            
            reviewService = new ReviewService();
            reviewService.addReview(newReview);
            
            ReviewFilehandling constructedMock = mocked.constructed().get(0);
            verify(constructedMock, times(1)).writeToFile(newReview);
        }
    }

}
