package json;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import core.TimeSlot

import java.io.File;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class JsonFilehandlingTest {

    @Test
    public void testWriteToFile() {
        LocalDateTime startTime = LocalDateTime.now();
        TimeSlot ts = new TimeSlot(startTime);

        assertTrue(new File("TimeSlotOverview.json").length() == 0);

        JsonFilehandling json = new JsonFilehandling();
        json.writeToFile(ts);

        assertFalse(new File("TimeSlotOverview.json").length() == 0);

        json.reset();
        assertTrue(new File("TimeSlotOverview.json").length() == 0);
    }
}
