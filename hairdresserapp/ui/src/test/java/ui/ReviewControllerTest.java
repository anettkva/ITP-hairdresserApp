package ui;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.control.TextArea;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;

    @Mock
    private FrontReviewService reviewService;

    @Mock
    private TextArea overviewArea;

    @Mock
    private TextArea textField;

    @BeforeAll
    static void initJFX() {
        JavaFxTestInitializer.initializeJavaFX();
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Sett mockReviewService i reviewController
        reviewController.setReviewService(reviewService);

        // Sett mock TextArea komponenter
        reviewController.overviewArea = overviewArea;
        reviewController.textField = textField;

        // Kall initialize manuelt
        try {
            reviewController.initialize();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            fail("Initialize method threw an exception");
        }
    }

    @Test
    void testLoadReviews_Success() throws IOException, InterruptedException {
        
        String mockReviews = "Review 1: Great service!\nReview 2: Good job!";
        when(reviewService.getReviews()).thenReturn(mockReviews);

        
        reviewController.loadReviews();

        
        verify(reviewService, times(2)).getReviews();
        verify(overviewArea, times(2)).setText("");
        verify(overviewArea, times(1)).setText(mockReviews);
    }

    

    @Test
    void testSendReview_Success() throws IOException, InterruptedException {
        
        String reviewText = "Excellent service!";
        when(textField.getText()).thenReturn(reviewText);
        doNothing().when(reviewService).addReview(reviewText);
        when(reviewService.getReviews()).thenReturn("Review 1: Excellent service!");

        
        reviewController.sendReview();

        
        verify(textField, times(1)).getText();
        verify(reviewService, times(1)).addReview(reviewText);
        verify(reviewService, times(2)).getReviews();
        verify(overviewArea, times(2)).setText(""); // En gang i initialize, en gang etter sendReview
        verify(overviewArea, times(3)).setText(anyString()); // En gang i initialize og en gang her
        verify(overviewArea).setText("Review 1: Excellent service!");
    }

    
}

