package ui;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import core.TimeSlot;
import core.Treatment;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {

    @InjectMocks
    private BookingController bookingController;
    
    @Mock
    private FrontBookingService frontBookingService;

    @Mock
    private FrontTreatmentService frontTreatmentService;

    @Mock
    private TextField bookingTextField;

    @Mock
    private TextArea bookingTextArea;

    @Mock
    private Treatment mockTreatment = new Treatment("haircut", 300);

    private List<Treatment> mockChosenTreatments;

    private List<TimeSlot> mockAllTimeSlots;

    private LocalDateTime desiredStartTime;

    @BeforeEach
    void setUp() throws IOException, InterruptedException {

        // Initialiser valgt behandlinger
        mockChosenTreatments = Arrays.asList(mockTreatment, mockTreatment); // Antatt 2 behandlinger
        when(frontTreatmentService.getChosenTreatments()).thenReturn(mockChosenTreatments);

        // Definer ønsket starttid som er innenfor én uke frem i tid
        desiredStartTime = createValidTimeSlot(24);

        

        // Format ønsket starttid som streng
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
        String desiredStartTimeStr = desiredStartTime.format(formatter);
        when(bookingTextField.getText()).thenReturn(desiredStartTimeStr);

        // Opprett matchende tidsluker som faktisk matcher ønsket starttid
        TimeSlot slot1 = new TimeSlot(desiredStartTime); // Første time
        TimeSlot slot2 = new TimeSlot(desiredStartTime.plusHours(1)); // Andre time
        mockAllTimeSlots = Arrays.asList(slot1, slot2);
        when(frontBookingService.getAllTimeSlots()).thenReturn(mockAllTimeSlots);

        // Mocket at ingen tidsluker er booket i oppstarten (for suksess-test)
        when(frontBookingService.getBookedSlots()).thenReturn(Arrays.asList());

        // Initialiser BookingController med mockede tjenester
        bookingController = new BookingController(frontBookingService, frontTreatmentService);
        bookingController.bookingTextField = bookingTextField;
        bookingController.bookingTextArea = bookingTextArea;
    }

    @BeforeAll
    public static void initJFX() {
        JavaFxTestInitializer.initializeJavaFX();
    }

    /**
     * Oppretter en gyldig LocalDateTime med starttid i fremtiden, på en hel time,
     * og innenfor 08:00 til 15:00.
     *
     * @param hoursAhead Antall timer fra nå til starttiden.
     * @return En gyldig TimeSlot.
     * @throws IOException Hvis TimeSlot-konstruktøren kaster en feil.
     */
    public static LocalDateTime createValidTimeSlot(int hoursAhead) throws IOException {
        LocalDateTime now = LocalDateTime.now().plusHours(hoursAhead).truncatedTo(ChronoUnit.HOURS);

        // Juster tiden til innenfor 08:00 til 15:00
        LocalTime startTime = now.toLocalTime();
        if (startTime.isBefore(LocalTime.of(8, 0))) {
            startTime = LocalTime.of(8, 0);
        } else if (startTime.isAfter(LocalTime.of(15, 0))) {
            startTime = LocalTime.of(13, 0);
        }

        LocalDateTime validStart = now.withHour(startTime.getHour()).withMinute(0).withSecond(0).withNano(0);

        // Hvis justeringen fører til at starttid er før nå, flytt til neste gyldige time
        if (validStart.isBefore(LocalDateTime.now())) {
            validStart = validStart.plusHours(1);
        }

        // Sørg for at starttid er på en hel time og innenfor 08:00 til 15:00
        return validStart;
    }

    @Test
    void testBookTimeSlot_Success() throws IOException, InterruptedException {


        // Mocket at ingen tidsluker er booket (beregnet i setUp)
        when(frontBookingService.getBookedSlots()).thenReturn(Arrays.asList());

        // Act
        bookingController.bookTimeSlot();

        // Assert
        // Verifiser at bookSlot ble kalt for hver tidsluke
        verify(frontBookingService, times(2)).bookSlot(any(TimeSlot.class));

        // Verifiser at getBookedSlots ble kalt for å oppdatere bookede slots
        verify(frontBookingService, times(1)).getBookedSlots();

        // Verifiser at bookingTextArea ble oppdatert med suksessmelding
        String expectedMessage = "Timen fra " + desiredStartTime.format(DateTimeFormatter.ofPattern("HH:mm  ")) +
                "til " + desiredStartTime.plusHours(mockChosenTreatments.size()).format(DateTimeFormatter.ofPattern("HH:mm 'den' dd-MM-yyyy")) +
                " er booket for " + mockChosenTreatments.size() + " behandling(er) :)";

        verify(bookingTextArea).setText(expectedMessage);
    }

    

    
}