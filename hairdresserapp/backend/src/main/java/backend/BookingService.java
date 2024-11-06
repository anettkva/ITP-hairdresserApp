package backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.TimeSlot;

@Service
public class BookingService {

    
    private static final Logger logger = LoggerFactory.getLogger(RestBookingController.class);

    @Autowired
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService() {
        this.bookingRepository = new JsonBookingRepository();
    }
    
    public List<TimeSlot> getBookedSlots() throws IOException {
        return bookingRepository.getBookedSlots();
    }

    /*public void bookSlot(TimeSlot timeSlot) throws IOException {
        timeSlot.book();
        try {
            filehandling.writeToFile(timeSlot);
        } catch (IOException e) {
            logger.error("Feil under skriving til fil: {}", e.getMessage());
            throw e;
        }
    } */

    public void bookSlot(TimeSlot timeSlot) throws IOException {
    logger.info("Prøver å booke TimeSlot: {}", timeSlot);
    timeSlot.book();
    try {
        bookingRepository.bookSlot(timeSlot);
        logger.info("TimeSlot booket og skrevet til fil: {}", timeSlot);
    } catch (IOException e) {
        logger.error("Feil under skriving til fil: {}", e.getMessage());
        throw e;
    }
    
}
}
