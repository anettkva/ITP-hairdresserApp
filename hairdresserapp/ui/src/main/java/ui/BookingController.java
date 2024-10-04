package ui;

import LocalDateTime;
import json.JsonFilehandling;
import core.TimeSlot;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class BookingController {

    @FXML 
    private TextField bookingTextField;

    @FXML 
    private TextArea BookingTextArea;

    private JsonFilehandling = new JsonFilehandling();


    @FXML
    public void bookTimeSLot() {

        String bookingTextField = userInput.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime desiredStartTime = LocalDateTime.parse(input, formatter);

            List<TimeSlot> bookedTimeSlots = JsonFilehandling.readFromFile();

            for (TimeSlot bookedSlot : bookedTimeSlots) {
                if (bookedTimeSlots.getStartTime().equals(desiredStartTime)) {
                    StringBuilder bookedSlotsText = new StringBuilder("Timen du ønsker er ikke ledig:( Her er en oversikt over bookede timer:\n");
                    for (TimeSlot slot : bookedTimeSlots) {
                        bookedSlotsText.append(bookedTimeSlots.getStartTime().toString());
                    }
                    BookingTextArea.setText(bookedSlotsText.toString());
                    return;
                }
            }

            TimeSlot newTimeSlot = new TimeSlot(desiredStartTime);
            newTimeSlot.book();
            bookedTimeSlots.add(newTimeSlot);
            fileHandler.writeToFile(bookedTimeSlots);

            BookingTextArea.setText("Timen med starttid " + desiredStartTime.toString() + "er booket:)");
        }
        
    }
}
