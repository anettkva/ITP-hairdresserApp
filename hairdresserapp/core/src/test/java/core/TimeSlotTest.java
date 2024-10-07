package core;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;
import core.TimeSlot;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeSlotTest {
    
    @Test
    void testStartTimeInPastThrowsException() {
        LocalDateTime startTime = LocalDateTime.now().minusHours(2);
        LocalDateTime endTime = LocalDateTime.now().plusHours(1);
        
        assertThrows(IllegalArgumentException.class, 
            () -> new TimeSlot(startTime),
            "Starttid må være i fremtiden"
        );

    }

    @Test
    void testEndTimeBeforeStartTimeThrowsException() {
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.minusMinutes(30);

        assertThrows(IllegalArgumentException.class, 
            () -> new TimeSlot(startTime),
            "endTime kan ikke være før startTime"
        );
    }

    @Test
    void testDurationLessThan30MinutesThrowsException() {
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(20);

        assertThrows(IllegalArgumentException.class, 
            () -> new TimeSlot(startTime),
            "Timen må være minst 30 min"
        );
    }

    @Test
    void testValidTimeSlot() {
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(45);

        assertDoesNotThrow(() -> new TimeSlot(startTime));
    }

    @Test
    void testBookingSuccess() throws IOException {
        
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(45);
        TimeSlot timeSlot = new TimeSlot(startTime);

        
        assertDoesNotThrow(timeSlot::book);

        
        assertTrue(timeSlot.isBooked());
    }

    @Test
    void testAlreadyBookedThrowsException() throws IOException {
        
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(45);
        TimeSlot timeSlot = new TimeSlot(startTime);

        
        timeSlot.book();

    
        assertThrows(IllegalStateException.class, timeSlot::book,
            "Timen er allerede booket"
        );
    }


}