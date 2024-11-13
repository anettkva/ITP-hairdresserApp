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

/**
 * REST controller for handling booking-related API requests.
 * This class provides endpoints to fetch all available time slots,
 * retrieve already booked time slots, and book new time slots.
 */
@RestController
@RequestMapping("/api/booking")
@CrossOrigin(origins = "*")
public class RestBookingController {

    /**
     * Logger for RestBookingController class.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestBookingController.class);

    /**
     * Service class for booking operations.
     */
    private final BookingService bookingService;

    /**
     * Default constructor that initializes BookingService.
     *
     * @throws IOException If an I/O error occurs during initialization of BookingService.
     */
    @Autowired
    public RestBookingController() throws IOException {
        this.bookingService = new BookingService();
    }

    /**
     * Handles GET requests to retrieve all possible time slots one week ahead.
     *
     * @return A list of all available {@link TimeSlot} objects.
     * @throws IOException If an I/O error occurs while fetching time slots.
     */
    @GetMapping("/allTimeSlots")
    public List<TimeSlot> getAllTimeSlots() throws IOException {
        logger.info("GET request received for all time slots" );
        return bookingService.getAllTimeSlots();
        
    }

    /**
     * Handles GET requests to retrieve all already booked time slots.
     *
     * @return A list of all booked {@link TimeSlot} objects.
     * @throws IOException If an I/O error occurs while fetching booked time slots.
     */
    @GetMapping
    public List<TimeSlot> getBookedSlots() throws IOException {
        logger.info("GET request received for booked slots" );
        return bookingService.getBookedSlots();
        
    }

    /**
     * Handles POST requests to book a new time slot.
     *
     * @param timeSlot The time slot to be booked, received in the request body.
     */
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
