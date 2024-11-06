package backend;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.TimeSlot;

@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "*")
public class RestBookingController {
    private static final Logger logger = LoggerFactory.getLogger(RestBookingController.class);

    
    private final BookingService bookingService;

    @Autowired
    public RestBookingController(BookingService bookingService) {
        logger.info("Startet");
        this.bookingService = bookingService;
    }


    @GetMapping
    public List<TimeSlot> getBookedSlots() throws IOException {
        logger.info("GET request received for booked slots" );
        return bookingService.getBookedSlots();
        
    }

    @PostMapping
    public void bookSlot(@RequestBody TimeSlot timeSlot)  {
        logger.info("POST request received for booked slot");
        try {
            bookingService.bookSlot(timeSlot);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
}
