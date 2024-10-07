package ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import core.TimeSlot;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import json.JsonFilehandling;


public class BookingController {

    private JsonFilehandling filehandling = new JsonFilehandling();;

    @FXML 
    private TextField bookingTextField;

    @FXML 
    private TextArea bookingTextArea;

    

    @FXML
    public void bookTimeSlot() {

        String text = bookingTextField.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime desiredStartTime = LocalDateTime.parse(text, formatter);

            List<TimeSlot> bookedTimeSlots = filehandling.readFromFile();

            for (TimeSlot bookedSlot : bookedTimeSlots) {
                if (bookedSlot.getStartTime().equals(desiredStartTime)) {
                    StringBuilder bookedSlotsText = new StringBuilder("Timen du ønsker er ikke ledig:( Her er en oversikt over bookede timer:\n");
                    for (TimeSlot slot : bookedTimeSlots) {
                        bookedSlotsText.append(slot.getStartTime().toString());
                    }
                    bookingTextArea.setText(bookedSlotsText.toString());
                    return;
                }
            }

            TimeSlot newTimeSlot = new TimeSlot(desiredStartTime);
            newTimeSlot.book();
            filehandling.writeToFile(newTimeSlot);

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm 'den' MM-dd-yyyy");
            String formattedStartTime = desiredStartTime.format(outputFormatter);

            bookingTextArea.setText("Timen med starttid " + formattedStartTime + "er booket:)");

        }

            catch (IllegalArgumentException e) {
                bookingTextArea.setText("Ugyldig starttid: " + e.getMessage());
            } catch (Exception e) {
                bookingTextArea.setText("En feil oppstod: " + e.getMessage());
            }
        
    }
}

