package core;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of time slots for the upcoming week.
 * 
 * This class initializes a list of {@link TimeSlot} objects for each day in the next seven days,
 * with each day containing hourly time slots from 09:00 to 15:00.
 */
public class WeeklyTimeSlots {
    
    /**
     * List of all time slots for the upcoming week.
     */
    private List<TimeSlot> allTimeSlots;

    /**
     * Constructs a new WeeklyTimeSlots instance and initializes the list of time slots.
     * 
     * The constructor creates time slots for each day starting from tomorrow for the next seven days.
     * Each day includes hourly time slots from 09:00 to 15:00.
     * 
     * @throws IOException if an I/O error occurs during initialization
     */
    public WeeklyTimeSlots() throws IOException {
        this.allTimeSlots = new ArrayList<>();

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(15, 0);
        LocalDate today = LocalDate.now();

        for (int day = 1; day < 8; day++) {
            LocalDate currentDay = today.plusDays(day);

            for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusHours(1)) {
                allTimeSlots.add(new TimeSlot(LocalDateTime.of(currentDay, time)));
            }
        }
    }

    /**
     * Retrieves the list of all time slots.
     * 
     * @return a {@code List<TimeSlot>} containing all the time slots for the upcoming week
     */
    public List<TimeSlot> getAllTimeSlots() {
        return allTimeSlots;
    } 

}




