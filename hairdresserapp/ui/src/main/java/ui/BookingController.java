package ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import core.TimeSlot;
import core.TimeSlotManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import json.JsonFilehandling;


public class BookingController {

    private JsonFilehandling filehandling = new JsonFilehandling();
    private TimeSlotManager manager;

    @FXML 
    private TextField bookingTextField;

    @FXML 
    private TextArea bookingTextArea;

    public BookingController() {
        try {
            manager = new TimeSlotManager();
        } catch (Exception e) {
            e.printStackTrace();;
        }

        // showAllTimeSlots();
            
    }


    @FXML
    public void showAllTimeSlots() throws IOException {
        loadBookedTimeSlots();
        
        List<TimeSlot> allTimeSlots = manager.getAllTimeSlots();
        StringBuilder text = new StringBuilder("Oversikt over timer: \n");

        for (TimeSlot slot : allTimeSlots) {
            String status = slot.isBooked() ? "Booket" : "Ledig";
            text.append(slot.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyy"))).append(" - ").append(status).append("\n");
        }

        bookingTextArea.setText(text.toString());
    }

    public void loadBookedTimeSlots() throws IOException {
        List<TimeSlot> bookedSlots = filehandling.readFromFile();
        for (TimeSlot slot : bookedSlots) {
            manager.bookTimeSlot(slot.getStartTime()); 
            slot.setBooked(true); 
        }
    }

    

    @FXML
    public void bookTimeSlot() {

        String text = bookingTextField.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime desiredStartTime = LocalDateTime.parse(text, formatter);

            TimeSlot newTimeSlot = new TimeSlot(desiredStartTime);

            manager.bookTimeSlot(desiredStartTime);


            updateJsonFile(newTimeSlot);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm 'den' dd-MM-yyyy");
            String formattedStartTime = desiredStartTime.format(outputFormatter);

            bookingTextArea.setText("Timen med starttid " + formattedStartTime + " er booket:)");

        }

            catch (IllegalArgumentException e) {
                bookingTextArea.setText("Ugyldig starttid: " + e.getMessage());
            } catch (Exception e) {
                bookingTextArea.setText("En feil oppstod: " + e.getMessage());
            }
        
    }

    public void updateJsonFile(TimeSlot bookedSlot) {
        try {
            filehandling.writeToFile(bookedSlot);  
        } catch (Exception e) {
            bookingTextArea.setText("Feil ved oppdatering av filen: " + e.getMessage());
        }
    }
}

