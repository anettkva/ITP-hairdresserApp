package backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import core.TimeSlot;
import json.JsonFilehandling;

@Repository
public class JsonBookingRepository implements BookingRepository{
    private JsonFilehandling filehandling;

    @Autowired
    public JsonBookingRepository() {
        this.filehandling = new JsonFilehandling();
    }

    public List<TimeSlot> getBookedSlots() throws IOException {
        List<TimeSlot> list = filehandling.readFromFile();
        if (list != null && !list.isEmpty()) {
            return list;
        }
        return new ArrayList<>();   
    }

    public void bookSlot(TimeSlot timeSlot) throws IOException {
    
    try {
        filehandling.writeToFile(timeSlot);
    } catch (IOException e) {
        throw e;
    }
    }
}
