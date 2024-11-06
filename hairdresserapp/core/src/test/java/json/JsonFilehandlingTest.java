package json;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;

import core.TimeSlot;


public class JsonFilehandlingTest {

    @Test
    public void testWriteToFile() throws IOException{
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
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

    @Test
    void testLoadFromFile() throws IOException {
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);
        TimeSlot ts = new TimeSlot(startTime);

        File myFile = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        assertTrue(myFile.exists());
        
        
        assertTrue(myFile.length() == 0);

        JsonFilehandling json = new JsonFilehandling();
        json.writeToFile(ts);

        List<TimeSlot> timeslots = json.readFromFile();


        assertEquals(timeslots.get(0).getStart(), ts.getStart());

        json.reset();
    }

}


