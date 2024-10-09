package ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import core.TimeSlot;
import core.WeeklyTimeSlots;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import json.JsonFilehandling;

import java.io.File;
import java.util.ArrayList;


public class BookingController {

    private JsonFilehandling filehandling = new JsonFilehandling();
    private WeeklyTimeSlots weeklyTimeSlots;
    private List<TimeSlot> allTimeSlots;

    @FXML
    private TextField bookingTextField;

    @FXML
    private TextArea bookingTextArea;

    public BookingController() throws IOException {
        try {
            weeklyTimeSlots = new WeeklyTimeSlots();
        } catch (Exception e) {
            e.printStackTrace();;
        }

        allTimeSlots = weeklyTimeSlots.getAllTimeSlots();
        loadJsonFile();
    }


    @FXML
    public void showAllTimeSlots() throws IOException {
        StringBuilder text = new StringBuilder("Oversikt over timer: \n");

        for (TimeSlot slot : allTimeSlots) {
            String status = slot.isBooked() ? "Booket" : "Ledig";
            text.append(slot.getStartTime().format(DateTimeFormatter.ofPattern("HH:mm dd-MM-yyy"))).append(" - ").append(status).append("\n");
        }

        bookingTextArea.setText(text.toString());
    }

    public void loadJsonFile() throws IOException {
        List<TimeSlot> bookedSlots;
        File myFile = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        if (myFile.length() != 0) {
            bookedSlots = filehandling.readFromFile();
        }
        else {
           bookedSlots = new ArrayList<>(); 
        }
        
        for (TimeSlot bookedSlot : bookedSlots) {
            for (TimeSlot slot : allTimeSlots) {
                if (bookedSlot.getStartTime().equals(slot.getStartTime())) {
                    slot.setBooked(true);
                }
            }
        }
    }
    

    @FXML
    public void bookTimeSlot() {

        String text = bookingTextField.getText();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime desiredStartTime = LocalDateTime.parse(text, formatter);

            TimeSlot matchingTimeSlot = null;

            for (TimeSlot slot: allTimeSlots) {
                if (slot.getStartTime().equals(desiredStartTime)) {
                    matchingTimeSlot = slot;
                    break;
                }
            }

            if (matchingTimeSlot == null) {
                throw new IllegalArgumentException("Velg en tilgjengelig tid fra listen");
            }

            matchingTimeSlot.book();

            updateJsonFile(matchingTimeSlot);

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

