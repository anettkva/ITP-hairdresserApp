package core;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class WeeklyTimeSlotsTest {
    
    private WeeklyTimeSlots weeklyTimeSlots;

    @BeforeEach
    public void setUp() throws IOException {
        weeklyTimeSlots = new WeeklyTimeSlots();
    }

    @Test
    public void testTimeSlotsListSize() throws IOException {
        List<TimeSlot> timeSlots = weeklyTimeSlots.getAllTimeSlots();

        assertEquals(42, timeSlots.size(), "Det skal være 42 gyldige timer på en uke");
    }

    @Test
    public void testTimeSlotsFirstSlot() throws IOException {
        List<TimeSlot> timeSlots = weeklyTimeSlots.getAllTimeSlots();

        TimeSlot firstTimeSlot = timeSlots.get(0);
        LocalDateTime expectedFirstStartTime = LocalDateTime.of(LocalDate.now().plusDays(1), LocalTime.of(9, 0));

        assertEquals(expectedFirstStartTime, firstTimeSlot.getStartTime(), "Første time skal starte kl.09.00 i morgen");
    }

    @Test
    public void testTimeSlotsLastSlot() throws IOException {
        List<TimeSlot> timeSlots = weeklyTimeSlots.getAllTimeSlots();

        TimeSlot lastTimeSlot = timeSlots.get(0);
        LocalDateTime expectedFirstStartTime = LocalDateTime.of(LocalDate.now().plusDays(7), LocalTime.of(15, 0));

        assertEquals(expectedFirstStartTime, lastTimeSlot.getStartTime(), "Siste time skal starte klokken 15:00 på den syvende dagen");
    }

    @Test
    public void testTimeSlotsAreInFuture() throws IOException {
        List<TimeSlot> timeSlots = weeklyTimeSlots.getAllTimeSlots();

        for (TimeSlot slot : timeSlots) {
            assertTrue(slot.getStartTime().isAfter(LocalDateTime.now()), "Alle tidspunkter skal være i fremtiden.");
        }
    }

    @Test
    public void testEachDayHasSixSlots() throws IOException {
        List<TimeSlot> timeSlots = weeklyTimeSlots.getAllTimeSlots();
        
        for (int day = 0; day < 7; day++) {
            for (int hour = 0; hour < 6; hour++) {
                TimeSlot slot = timeSlots.get(day * 6 + hour);
                LocalTime expectedTime = LocalTime.of(9, 0).plusHours(hour);
                assertEquals(expectedTime, slot.getStartTime().toLocalTime(), "Tidslottet skal stemme overens med timeintervallet.");
            }
        }
    }
}
