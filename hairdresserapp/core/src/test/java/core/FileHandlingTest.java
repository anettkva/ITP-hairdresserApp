package core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;


public class FileHandlingTest {

    Filehandling fh = new Filehandling();

    @Test
    void testWriteToFile() throws IOException {
        Treatment t = new Treatment("Hårføning", 100);
        fh.writeToFile(t);

        File myFile = new File("./TreatmentsAndPrices.txt");
        assertTrue(myFile.exists());

        assertFalse(myFile.length() == 0);

        fh.reset();

        assertTrue(myFile.length() == 0);
    }
    
    @Test 
    void testLoadFromFile() throws IOException {
        Treatment t = new Treatment("Hårføning", 100);
        fh.writeToFile(t);

        List<Treatment> treatments = fh.loadFromFile();

        assertEquals("Hårføning", treatments.get(0).getName());

        fh.reset();
    }

    @Test
    void testReset() throws IOException {
        Treatment t = new Treatment("Hårføning", 100);
        fh.writeToFile(t);

        File myFile = new File("./TreatmentsAndPrices.txt");
        assertTrue(myFile.exists());

        assertFalse(myFile.length()==0);

        fh.reset();

        assertFalse(myFile.exists());

        
    }
}
