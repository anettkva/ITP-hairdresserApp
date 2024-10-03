package core;

import java.time.LocalDateTime;
import core.JsonFileHandeling;

public class TimeSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isBooked;


    public TimeSlot(LocalDateTime startTime) {
        if (startTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentExeption("Starttid må være i fremtiden");
        }

        if (!(time.getMinute() == 0 && time.getSecond() == 0)) {
            throw new IllegalArgumentExeption("Starttid må være på et helt klokkeslett");
        }

        if (startTime.isBefore(LocalTime.of(8, 0)) || startTime.isAfter(LocalTime.of(15, 0))) {
            throw new IllegalArgumentException("Timer kan ikke starte før 8 eller slutte etter 16");
        }

        JsonFileHandeling fileHandeler = new JsonFileHandeling();
        
        
        List<TimeSlot> bookedTimeSlots = fileHandeler.readFromFile();
        for (TimeSlot slot : bookedTimeSlots) {
            if (startTime.equals(slot.getStartTime())) {
                throw new IllegalArgumentExeption("Starttiden er allerede tatt");
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
            throw new IllegalStateExeption("Timen er allerede booket");
        }

        this.isBooked = true;
    }

    public void cancelBooking() {
        if (Duration.between(LocalDateTime.now(), startTime) < 2) {
            throw new IllegalStateExeption("Det er under to timer til timen, den kan ikke kanselleres");
        }

        if (!this.isBooked) {
            throw new IllegalStateExeption("Timen er ikke booket");
        }

        this.isBooked = false;
    }

    
}
