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
    void onHourTestThrowsException(){
        LocalDateTime starTime = LocalDateTime.now().plusHours(1).withMinute(30);
        assertThrows(IllegalArgumentException.class,
            () -> new TimeSlot(starTime),
        "startid må være på et helt klokkeslett"
        );
    }

    @Test
    void startTimeOutsideWorkingHours(){
        LocalDateTime starTimeEarly = LocalDateTime.now().plusDays(1).withHour(7).withMinute(0);
        assertThrows(IllegalArgumentException.class,
        () -> new TimeSlot(starTimeEarly),
        "Timen må være mellom 8 og 16"
        );

        LocalDateTime startTimeLate = LocalDateTime.now().withHour(16).withMinute(0);
        assertThrows(IllegalArgumentException.class,
        () -> new TimeSlot(startTimeLate),
        "Timen må være mellom 8 og 16"
        );

    }

    @Test
    void AlreadyBookedTestThrowsExeption() throws IOException{
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(9).withMinute(0);
        TimeSlot existingTimeSlot = new TimeSlot(startTime);
        existingTimeSlot.book();

        assertThrows(IllegalArgumentException.class,
        () -> new TimeSlot(startTime),
        "Timen er allerede booket"
        );
    }

    @Test
    void cancelBookingWithMoreThanTwoHoursLeft() throws IOException{
        LocalDateTime starTime = LocalDateTime.now().plusDays(1).withHour(12).withMinute(0);
        TimeSlot timeSlot = new TimeSlot(starTime);
        timeSlot.book();
        assertDoesNotThrow(timeSlot :: cancelBooking);
        assertTrue(!timeSlot.isBooked());
    }

    @Test
    void CancelBookingLessThanTwoHoursLeftThrowsExeption() throws IOException{
        LocalDateTime startTime = LocalDateTime.now().plusHours(1).withMinute(0);
        TimeSlot timeSlot = new TimeSlot(startTime);
        timeSlot.book();

        assertThrows(IllegalArgumentException.class, timeSlot :: cancelBooking,
        "Det er under to timer til timen, den kan ikke lenger kanselleres" );
    }


    @Test
    void cancelBookingNotBookedThrowsExseption() throws IOException{
        LocalDateTime startTime = LocalDateTime.now(). plusDays(1).withHour(9).withMinute(0);
        TimeSlot timeSlot = new TimeSlot(startTime);

        assertThrows(IllegalArgumentException.class, timeSlot :: cancelBooking,
        "Timen er ikke booket");
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
        LocalDateTime startTime = LocalDateTime.now().plusDays(1).withHour(9).withSecond(0);
  
        assertDoesNotThrow(() -> new TimeSlot(startTime));
    }

    @Test
    void testBookingSuccess() throws IOException {
        
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        TimeSlot timeSlot = new TimeSlot(startTime);

        
        assertDoesNotThrow(timeSlot::book);

        
        assertTrue(timeSlot.isBooked());
    }

    @Test
    void testAlreadyBookedThrowsException() throws IOException {
        
        LocalDateTime startTime = LocalDateTime.now().plusHours(1);
        TimeSlot timeSlot = new TimeSlot(startTime);

        
        timeSlot.book();

    
        assertThrows(IllegalStateException.class, timeSlot::book,
            "Timen er allerede booket"
        );
    }


}