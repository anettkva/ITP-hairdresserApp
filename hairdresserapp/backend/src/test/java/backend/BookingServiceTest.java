package backend;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import core.TimeSlot;
import core.WeeklyTimeSlots;
import json.JsonFilehandling;

class BookingServiceTest {

    @Mock
    private JsonFilehandling filehandling;

    @Mock
    private WeeklyTimeSlots weeklyTimeSlots;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    public static TimeSlot createValidTimeSlot(int hoursAhead) throws IOException {
        LocalDateTime now = LocalDateTime.now().plusHours(hoursAhead).truncatedTo(ChronoUnit.HOURS);
        
        // Juster tiden til innenfor 08:00 til 15:00
        LocalTime startTime = now.toLocalTime();
        if (startTime.isBefore(LocalTime.of(8, 0))) {
            startTime = LocalTime.of(8, 0);
        } else if (startTime.isAfter(LocalTime.of(15, 0))) {
            startTime = LocalTime.of(15, 0);
        }
        
        LocalDateTime validStart = now.withHour(startTime.getHour()).withMinute(0).withSecond(0).withNano(0);
        
        // Hvis justeringen fører til at starttid er før nå, flytt til neste gyldige time
        if (validStart.isBefore(LocalDateTime.now())) {
            validStart = validStart.plusHours(1);
        }
        
        // Sørg for at starttid er på en hel time og innenfor 08:00 til 15:00
        return new TimeSlot(validStart);
    }

    @Test
    void testGetAllTimeSlots() throws IOException {
        
        TimeSlot slot1 = createValidTimeSlot(24);
        TimeSlot slot2 = createValidTimeSlot(25);
        List<TimeSlot> timeSlots = Arrays.asList(slot1, slot2);
        when(weeklyTimeSlots.getAllTimeSlots()).thenReturn(timeSlots);

        
        List<TimeSlot> result = bookingService.getAllTimeSlots();

        
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "There should be 2 time slots");
        assertEquals(slot1, result.get(0), "First time slot should match");
        assertEquals(slot2, result.get(1), "Second time slot should match");
        verify(weeklyTimeSlots, times(1)).getAllTimeSlots();
    }

    @Test
    void testGetBookedSlots() throws IOException {
        
        TimeSlot bookedSlot1 = createValidTimeSlot(48);
        bookedSlot1.book();
        TimeSlot bookedSlot2 = createValidTimeSlot(49);
        bookedSlot2.book();
        List<TimeSlot> mockBookedSlots = Arrays.asList(bookedSlot1, bookedSlot2);
        when(filehandling.readFromFile()).thenReturn(mockBookedSlots);

        
        List<TimeSlot> result = bookingService.getBookedSlots();

        
        assertNotNull(result, "The result should not be null");
        assertEquals(2, result.size(), "There should be 2 booked time slots");
        assertEquals(bookedSlot1, result.get(0), "First booked time slot should match");
        assertEquals(bookedSlot2, result.get(1), "Second booked time slot should match");
        verify(filehandling, times(1)).readFromFile();
    }

    @Test
    void testGetBookedSlots_ThrowsIOException() throws IOException {
        
        when(filehandling.readFromFile()).thenThrow(new IOException("File not found"));

        
        IOException exception = assertThrows(IOException.class, () -> {
            bookingService.getBookedSlots();
        }, "Expected getBookedSlots to throw IOException");

        assertEquals("File not found", exception.getMessage(), "Exception message should match");
        verify(filehandling, times(1)).readFromFile();
    }

    @Test
    void testBookSlot() throws IOException {
        
        TimeSlot slotToBook = createValidTimeSlot(24);
        // Assume that book() sets some internal state, e.g., isBooked = true
        // For simplicity, we won't test the internal state here

        
        bookingService.bookSlot(slotToBook);

    
        // Capture the argument passed to writeToFile
        ArgumentCaptor<TimeSlot> slotCaptor = ArgumentCaptor.forClass(TimeSlot.class);
        verify(filehandling, times(1)).writeToFile(slotCaptor.capture());
        TimeSlot capturedSlot = slotCaptor.getValue();
        assertEquals(slotToBook, capturedSlot, "The booked slot should be written to the file");
    }

    @Test
    void testBookSlot_ThrowsIOException() throws IOException {
        
        TimeSlot slotToBook = createValidTimeSlot(36);
        doThrow(new IOException("Write failed")).when(filehandling).writeToFile(slotToBook);

        
        IOException exception = assertThrows(IOException.class, () -> {
            bookingService.bookSlot(slotToBook);
        }, "Expected bookSlot to throw IOException");

        assertEquals("Write failed", exception.getMessage(), "Exception message should match");
        verify(filehandling, times(1)).writeToFile(slotToBook);
    }
}
