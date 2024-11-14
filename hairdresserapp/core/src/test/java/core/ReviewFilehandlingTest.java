package core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;



public class ReviewFilehandlingTest {

    private ReviewFilehandling reviewFilehandling;
    private final String filePath = "../../hairdresserapp/Reviews.txt";
    private final String testReview = "veldig fornøyd, steikje det va bra kundeservice. Følt me velde lækker etterpå. Abefales !";

    @BeforeEach
    public void setUp() {
        
        reviewFilehandling = new ReviewFilehandling();
    }

    @Test
    public void testFileIsNotEmptyBeforeAndLengthIncreasesAfterAddingReview() throws IOException {
        // Sjekk at filen ikke er tom før vi legger til en anmeldelse
        long initialLength = new File(filePath).length();
        assertTrue(initialLength > 0, "The file should not be empty before adding a review.");

        // Legg til en anmeldelse
        reviewFilehandling.writeToFile(testReview);

        // Sjekk at lengden på filen har økt etter at anmeldelsen er lagt til
        long newLength = new File(filePath).length();
        assertTrue(newLength > initialLength, "The file length should increase after adding a review.");
    }

    
}
