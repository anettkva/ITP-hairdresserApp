package core;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import core.TimeSlot;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeSlotTest {
    
    @Test
    void testStartTimeInPastThrowsException() {
        LocalDateTime startTime = LocalDateTime.now().minusHours(2);
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        
        assertThrows(IllegalArgumentException.class, 
            () -> new TimeSlot(startTime, endTime),
            "Starttid må være i fremtiden"
        );

    }

    @Test
    void testEndTimeBeforeStartTimeThrowsException() {
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.minusMinutes(30);

        assertThrows(IllegalArgumentException.class, 
            () -> new TimeSlot(startTime, endTime),
            "endTime kan ikke være før startTime"
        );
    }

    @Test
    void testDurationLessThan30MinutesThrowsException() {
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(20);

        assertThrows(IllegalArgumentException.class, 
            () -> new TimeSlot(startTime, endTime),
            "Timen må være minst 30 min"
        );
    }

    @Test
    void testValidTimeSlot() {
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(45);

        assertDoesNotThrow(() -> new TimeSlot(startTime, endTime));
    }

    @Test
    void testBookingSuccess() {
        
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(45);
        TimeSlot timeSlot = new TimeSlot(startTime, endTime);

        
        assertDoesNotThrow(timeSlot::book);

        
        assertTrue(timeSlot.isBooked());
    }

    @Test
    void testAlreadyBookedThrowsException() {
        
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(45);
        TimeSlot timeSlot = new TimeSlot(startTime, endTime);

        
        timeSlot.book();

    
        assertThrows(IllegalStateException.class, timeSlot::book,
            "Timen er allerede booket"
        );
    }


}