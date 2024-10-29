package ui;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import core.TimeSlot;
import core.Treatment;
import core.WeeklyTimeSlots;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import json.JsonFilehandling;


public class BookingController {

    private JsonFilehandling filehandling = new JsonFilehandling();
    private WeeklyTimeSlots weeklyTimeSlots;
    private List<TimeSlot> allTimeSlots;
    private List<Treatment> chosenTreatments;
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

        chosenTreatments = HairdresserApp.getSelectedtreatments();
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

            int requiredSlots = chosenTreatments.size();
            List<TimeSlot> slotsToBook = new ArrayList<>();

            for (int i = 0; i < requiredSlots; i++){
                LocalDateTime slotTime = desiredStartTime.plusHours(i);
                TimeSlot matchingTimeSlot = null;

                for (TimeSlot slot: allTimeSlots) {
                    if (slot.getStartTime().equals(slotTime) && !slot.isBooked()) {
                        matchingTimeSlot = slot;
                        break;
                    }
                }

                if (matchingTimeSlot == null) {
                    throw new IllegalArgumentException("Velg en tilgjengelig tid fra listen");
                }
                slotsToBook.add(matchingTimeSlot);
            }

            

            for (TimeSlot slot : slotsToBook) {
                slot.book();
                updateJsonFile(slot);
            }

            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("HH:mm 'den' dd-MM-yyyy");
            String formattedStartTime = desiredStartTime.format(outputFormatter);
            String formattedEndTime = desiredStartTime.plusHours(requiredSlots).format(outputFormatter);

            bookingTextArea.setText("Timen fra " + formattedStartTime + " til " + formattedEndTime + " er booket for " + requiredSlots + " behandling(er) :)");

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

