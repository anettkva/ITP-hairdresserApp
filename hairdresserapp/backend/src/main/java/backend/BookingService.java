package backend;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.TimeSlot;
import core.WeeklyTimeSlots;
import json.JsonFilehandling;

@Service
public class BookingService {

    
    private static final Logger logger = LoggerFactory.getLogger(RestBookingController.class);

    private JsonFilehandling filehandling;
    private WeeklyTimeSlots weeklyTimeSlots;


    @Autowired
    public BookingService() throws IOException {
        this.filehandling = new JsonFilehandling();
        this.weeklyTimeSlots = new WeeklyTimeSlots();
    }

    public List<TimeSlot> getAllTimeSlots() {
        return this.weeklyTimeSlots.getAllTimeSlots();
    }
    
    public List<TimeSlot> getBookedSlots() throws IOException {
        return filehandling.readFromFile();
        
    }

    public void bookSlot(TimeSlot timeSlot) throws IOException {
        logger.info("Prøver å booke TimeSlot: {}", timeSlot);
        timeSlot.book();
        try {
            filehandling.writeToFile(timeSlot);
            logger.info("TimeSlot booket og skrevet til fil: {}", timeSlot);
        } catch (IOException e) {
            logger.error("Feil under skriving til fil: {}", e.getMessage());
            throw e;
        }
    } 

 
}
