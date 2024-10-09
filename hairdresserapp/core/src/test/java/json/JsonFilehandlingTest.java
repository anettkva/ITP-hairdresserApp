package json;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import core.TimeSlot;

public class JsonFilehandlingTest {

    @Test
    public void testWriteToFile() throws IOException{
        LocalDateTime startTime = LocalDateTime.of(2024, 10, 14, 11, 0, 0);
        TimeSlot ts = new TimeSlot(startTime);

        File myFile = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        assertTrue(myFile.exists());
        
        
        assertTrue(myFile.length() == 0);

        JsonFilehandling json = new JsonFilehandling();
        json.writeToFile(ts);

        assertTrue(myFile.length() != 0);

        json.reset();
        assertTrue(myFile.length() == 0); 
    }

}


