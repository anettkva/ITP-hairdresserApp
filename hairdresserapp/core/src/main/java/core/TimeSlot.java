package core;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import json.JsonFilehandling;

public class TimeSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isBooked;


    public TimeSlot(LocalDateTime startTime) throws IOException {
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Starttid må være i fremtiden");
        }

        if (!(startTime.getMinute() == 0 && startTime.getSecond() == 0)) {
            throw new IllegalArgumentException("Starttid må være på et helt klokkeslett");
        }

        if (startTime.toLocalTime().isBefore(LocalTime.of(8, 0)) || startTime.toLocalTime().isAfter(LocalTime.of(15, 0))) {
            throw new IllegalArgumentException("Timer kan ikke starte før 8 eller slutte etter 16");
        }

        JsonFilehandling fileHandler = new JsonFilehandling();
        
        
        List<TimeSlot> bookedTimeSlots = fileHandler.readFromFile();
        for (TimeSlot slot : bookedTimeSlots) {
            if (startTime.equals(slot.getStartTime())) {
                throw new IllegalArgumentException("Starttiden er allerede tatt");
            }

        }
        

        this.startTime = startTime;
        this.endTime = startTime.plusHours(1);
        this.isBooked = false;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }


    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


    public LocalDateTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }


    public boolean isBooked() {
        return isBooked;
    }


    public void setBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public void book() {
        if (this.isBooked) {
            throw new IllegalStateException("Timen er allerede booket");
        }

        this.isBooked = true;
    }

    public void cancelBooking() {
        if (Duration.between(LocalDateTime.now(), this.startTime).toHours() < 2) {
            throw new IllegalStateException("Det er under to timer til timen, den kan ikke kanselleres");
        }

        if (!this.isBooked) {
            throw new IllegalStateException("Timen er ikke booket");
        }

        this.isBooked = false;
    }

    
}
