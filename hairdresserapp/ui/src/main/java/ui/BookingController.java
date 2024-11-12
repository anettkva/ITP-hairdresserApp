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


public class BookingController {

    private List<TimeSlot> allTimeSlots;
    private List<Treatment> chosenTreatments;

    private FrontBookingService frontBookingService;
    private FrontTreatmentService frontTreatmentService;


    @FXML
    private TextField bookingTextField;

    @FXML
    private TextArea bookingTextArea;

    public BookingController() throws IOException, InterruptedException {
        this.frontBookingService = new FrontBookingService();
        this.frontTreatmentService = new FrontTreatmentService();
        this.chosenTreatments = frontTreatmentService.getChosenTreatments();
        allTimeSlots = frontBookingService.getAllTimeSlots();
    }

    public TextArea getarea() {
        return this.bookingTextArea;
    }

    public TextField getfield() {
        return this.bookingTextField;
    }

    public List<TimeSlot> getAllTimeSlots() {
        return this.allTimeSlots;
    }


    @FXML
    public void showAllTimeSlots() throws IOException, InterruptedException {
        loadJsonFile();
        StringBuilder text = new StringBuilder("Oversikt over timer: \n");

        for (TimeSlot slot : allTimeSlots) {
            String status = slot.isBooked() ? "Booket" : "Ledig";
            text.append(slot.getStart().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyyy"))).append(" - ").append(status).append("\n");
        }

        bookingTextArea.setText(text.toString());
    }

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

