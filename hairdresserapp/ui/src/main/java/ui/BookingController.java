package ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import core.TimeSlot;
import core.Treatment;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


/**
 * Controller class for handling booking functionality in the user interface.
 * This class manages the display of available time slots, booking of time slots,
 * and updating of booking statuses.
 */
public class BookingController {

    private List<TimeSlot> allTimeSlots;
    private List<Treatment> chosenTreatments;

    private FrontBookingService frontBookingService;
    private FrontTreatmentService frontTreatmentService;


    @FXML TextField bookingTextField;

    @FXML TextArea bookingTextArea;

    /**
     * Default constructor for production.
     *
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     */
    public BookingController() throws IOException, InterruptedException {
        this(new FrontBookingService(), new FrontTreatmentService());
    }

    /**
     * Constructor for testing with injected dependencies.
     *
     * @param frontBookingService   Service for booking-related operations.
     * @param frontTreatmentService Service for treatment-related operations.
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     */
    public BookingController(FrontBookingService frontBookingService, FrontTreatmentService frontTreatmentService) throws IOException, InterruptedException {
        this.frontBookingService = frontBookingService;
        this.frontTreatmentService = frontTreatmentService;
        this.chosenTreatments = frontTreatmentService.getChosenTreatments();
        this.allTimeSlots = frontBookingService.getAllTimeSlots();
    }

    /**
     * Retrieves the text area used for displaying booking information.
     *
     * @return TextArea for booking information.
     */
    public TextArea getarea() {
        return this.bookingTextArea;
    }

    /**
     * Retrieves the text field used for entering booking time.
     *
     * @return TextField for booking time.
     */
    public TextField getfield() {
        return this.bookingTextField;
    }

    /**
     * Retrieves all available time slots.
     *
     * @return List of all TimeSlot objects.
     */
    public List<TimeSlot> getAllTimeSlots() {
        return this.allTimeSlots;
    }


    /**
     * Displays all available and booked time slots in the text area.
     *
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     */
    @FXML
    public void showAllTimeSlots() throws IOException, InterruptedException {
        loadJsonFile();
        StringBuilder text = new StringBuilder("Oversikt over timer: \n");

        for (TimeSlot slot : allTimeSlots) {
            String status = slot.isBooked() ? "Booked" : "Availible";
            text.append(slot.getStart().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"))).append(" - ").append(status).append("\n");
        }

        bookingTextArea.setText(text.toString());
    }

    /**
     * Loads the JSON file and updates the booking status for the time slots.
     *
     * @throws IOException if an I/O error occurs.
     * @throws InterruptedException if the thread is interrupted.
     */
    public void loadJsonFile() throws IOException, InterruptedException {
        List<TimeSlot> bookedSlots = frontBookingService.getBookedSlots();
        
        if (bookedSlots.size() != 0 && bookedSlots.stream().allMatch(obj -> obj instanceof TimeSlot)) {
            for (TimeSlot bookedSlot : bookedSlots) {
                for (TimeSlot slot : allTimeSlots) {
                    if (bookedSlot.getStart().equals(slot.getStart())) {
                        slot.setBooked(true);
                    }
                }
            } 
        }
        
    }
    

    /**
     * Books a time slot based on the user's entered time and selected treatments.
     * Updates the text area with the result of the booking process.
     */
    @FXML
    public void bookTimeSlot() {

        String text = bookingTextField.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy");
            LocalDateTime desiredStartTime = LocalDateTime.parse(text, formatter);

            int requiredSlots = chosenTreatments.size();
            List<TimeSlot> slotsToBook = new ArrayList<>();

            for (int i = 0; i < requiredSlots; i++){
                LocalDateTime slotTime = desiredStartTime.plusHours(i);
                TimeSlot matchingTimeSlot = null;

                for (TimeSlot slot: this.allTimeSlots) {
                    if (slot.getStart().equals(slotTime) && !slot.isBooked()) {
                        matchingTimeSlot = slot;
                        break;
                    }
                }

                if (matchingTimeSlot == null) {
                    throw new IllegalArgumentException("Tidsintervallet er ikke tilgjengelig du må ha " + requiredSlots + " ledige timer på f.o.m valgt tidspunkt");
                }
                slotsToBook.add(matchingTimeSlot);
            }

            
            if (slotsToBook.size() != 0 && slotsToBook.stream().allMatch(obj -> obj instanceof TimeSlot)) {
                for (TimeSlot slot : slotsToBook) {
                    frontBookingService.bookSlot(slot);
                }
            }
            
            loadJsonFile();

            DateTimeFormatter outputFormatterHour = DateTimeFormatter.ofPattern("HH:mm ");
            String formattedStartTime = desiredStartTime.format(outputFormatterHour);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm 'den' dd-MM-yyyy");
            String formattedEndTime = desiredStartTime.plusHours(requiredSlots).format(outputFormatter);

            bookingTextArea.setText("Timen fra " + formattedStartTime + " til " + formattedEndTime + " er booket for " + requiredSlots + " behandling(er) :)");

        }

            catch (IllegalArgumentException e) {
                bookingTextArea.setText("Ugyldig starttid: " + e.getMessage());
            } catch (Exception e) {
                bookingTextArea.setText("En feil oppstod: " + e.getMessage());
            }
        
    }

}

