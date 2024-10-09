package core;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.Clock;
import java.time.LocalDateTime;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TimeSlotTest {
    
    @Test
    void StartTimeInPastThrowsExceptionTest() {
        LocalDateTime startTime = LocalDateTime.now().minusHours(2);
        
        assertThrows(IllegalArgumentException.class, 
            () -> extracted(startTime),
            "Starttid må være i fremtiden"
        );

    }

    @Test
    void NotOnWholeHourThrowsExseption(){
        LocalDateTime startTime = LocalDateTime.now().plusMinutes(30);

        assertThrows(IllegalArgumentException.class,
        () -> extracted(startTime),
        "Starttid må være på et helt klokkeslett" );
    }



    //må se på etterpå
    @Test
    void startTimeBeforOpeningHoursThrowsException() {
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(7).withMinute(0).withSecond(0).withNano(0);

        
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, 
            () -> new TimeSlot(startTime));

        
        assertEquals("Timer kan ikke starte før 8 eller slutte etter 16", exception.getMessage());
    }

    

    private TimeSlot extracted(LocalDateTime startTime) throws IOException {
        return new TimeSlot(startTime);
    }

    @Test
    void startTimeAfterClosingHoursThrowsExseption(){
        LocalDateTime startTime = LocalDateTime.now().plusHours(1).withHour(16).withMinute(0).withSecond(0);
        

        assertThrows(IllegalArgumentException.class,
        () -> extracted(startTime),
        "Timen må være mellom 8 og 16" );

    }


    @Test
    void EndTimeBeforeStartTimeThrowsExceptionTest() {
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.minusMinutes(30);

        assertThrows(IllegalArgumentException.class, 
            () -> extracted(startTime),
            "endTime kan ikke være før startTime"
        );
    }

    @Test
    void testDurationLessThan30MinutesThrowsException() {
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        LocalDateTime endTime = startTime.plusMinutes(20);

        assertThrows(IllegalArgumentException.class, 
            () -> extracted(startTime),
            "Timen må være minst 30 min"
        );
    }

    @Test
    void validStartTimeCreatesTimeSlotSuccessfully() {
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);

        assertDoesNotThrow(() -> {
            TimeSlot timeSlot = new TimeSlot(startTime);
            assertEquals(startTime, timeSlot.getStartTime());
            assertEquals(startTime.plusHours(1), timeSlot.getEndTime());
            assertFalse(timeSlot.isBooked());
        });
    }

    @Test
    void bookingSuccessTest() throws IOException {
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);


        TimeSlot timeSlot = new TimeSlot(startTime);


        assertFalse(timeSlot.isBooked());


        assertDoesNotThrow(() -> {
            timeSlot.book();
        });

        assertTrue(timeSlot.isBooked());
    }

    @Test
    void alreadyBookedThrowsException() throws IOException {
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(8).withMinute(0).withSecond(0).withNano(0);

        TimeSlot timeSlot = new TimeSlot(startTime);

        timeSlot.book();
        
        assertTrue(timeSlot.isBooked());

        IllegalStateException exception = assertThrows(IllegalStateException.class, timeSlot::book);
        
        assertEquals("Timen er allerede booket", exception.getMessage());
    }

    @Test
    void cancelBookingWhenNotBookedThrowsExseption() throws IOException{
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(10).withMinute(0).withSecond(0).withNano(0);
        TimeSlot timeSlot = new TimeSlot(startTime);

        IllegalStateException exeption = assertThrows(IllegalStateException.class, timeSlot::cancelBooking );
        assertEquals("Timen er ikke booket", exeption.getMessage());

    }

    
     

    
    


}