package json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import core.Treatment;
import json.TreatmentFilehandling;


public class FileHandlingTest {

    TreatmentFilehandling fh = new TreatmentFilehandling();

    @Test
    void testWriteToFile() throws IOException {
        Treatment t = new Treatment("Hårføning", 100);
        fh.writeToFile(t);

        File myFile = new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json");
        assertTrue(myFile.exists());

        assertFalse(myFile.length() == 0);

        fh.reset();

        assertTrue(myFile.length() == 0);
    }
    
    @Test 
    void testLoadFromFile() throws IOException {
        Treatment t = new Treatment("Hårføning", 100);
        fh.writeToFile(t);

        List<Treatment> treatments = fh.readFromFile();

        assertEquals("Hårføning", treatments.get(0).getName());

        fh.reset();
    }

    @Test
    void testReset() throws IOException {
        Treatment t = new Treatment("Hårføning", 100);
        fh.writeToFile(t);

        File myFile = new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json");
        assertTrue(myFile.exists());

        assertFalse(myFile.length()==0);

        fh.reset();


        
    }
}
