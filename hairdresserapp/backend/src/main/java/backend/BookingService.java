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

/**
 * Backend service class for handling booking operations.
 * This class manages available time slots, retrieves booked time slots,
 * and handles booking of new time slots by communicating with the filesystem.
 */
@Service
public class BookingService {

    /**
     * Logger for BookingService class.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestBookingController.class);

    /**
     * Helper class for handling JSON file operations.
     */
    private JsonFilehandling filehandling;

    /**
     * Class that produces possible timeslots one week ahead in time.
     */
    private WeeklyTimeSlots weeklyTimeSlots;


    /**
     * Default constructor that initializes JsonFilehandling and WeeklyTimeSlots.
     *
     * @throws IOException If an I/O error occurs during initialization.
     */
    @Autowired
    public BookingService() throws IOException {
        this(new JsonFilehandling(), new WeeklyTimeSlots());
    }

    /**
     * Constructor that initializes JsonFilehandling and WeeklyTimeSlots.
     *
     * @param filehandling    The JsonFilehandling instance.
     * @param weeklyTimeSlots The WeeklyTimeSlots instance.
     */
    @Autowired
    public BookingService(JsonFilehandling filehandling, WeeklyTimeSlots weeklyTimeSlots) {
        this.filehandling = filehandling;
        this.weeklyTimeSlots = weeklyTimeSlots;
    }

    /**
     * Retrieves all available time slots.
     *
     * @return A list of all available {@link TimeSlot} objects.
     */
    public List<TimeSlot> getAllTimeSlots() {
        return this.weeklyTimeSlots.getAllTimeSlots();
    }

    /**
     * Retrieves all already booked time slots.
     *
     * @return A list of all booked {@link TimeSlot} objects.
     * @throws IOException If an I/O error occurs during reading from the file.
     */
    public List<TimeSlot> getBookedSlots() throws IOException {
        return filehandling.readFromFile();
        
    }

    /**
     * Books a specific time slot by updating its status and writing it to the file.
     *
     * @param timeSlot The time slot to be booked.
     * @throws IOException If an I/O error occurs during writing to the file.
     */
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
