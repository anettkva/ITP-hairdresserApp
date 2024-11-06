package core;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;


public class TimeSlot {

    @JsonProperty("start")
    private LocalDateTime start;

    @JsonProperty("booked")
    private boolean booked;

    @JsonProperty("end")
    private LocalDateTime end;

    public TimeSlot() {
    }


    public TimeSlot(LocalDateTime start) throws IOException{
        if (start.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Starttid må være i fremtiden");
        }

        if (!(start.getMinute() == 0 && start.getSecond() == 0)) {
            throw new IllegalArgumentException("Starttid må være på et helt klokkeslett");
        }

        if (start.toLocalTime().isBefore(LocalTime.of(8, 0)) || start.toLocalTime().isAfter(LocalTime.of(15, 0))) {
            throw new IllegalArgumentException("Timer kan ikke starte før 8 eller slutte etter 16");
        }
        

        this.start = start;
        this.end = start.plusHours(1);
        this.booked = false;
    }


    public LocalDateTime getStart() {
        return start;
    }


    public void setStart(LocalDateTime start) {
        this.start = start;
    }


    public LocalDateTime getEnd() {
        return end;
    }


    public void setEnd(LocalDateTime end) {
        this.end = end;
    }


    public boolean isBooked() {
        return booked;
    }


    public void setBooked(boolean isBooked) {
        this.booked = isBooked;
    }

    public void book() {
        if (this.booked) {
            throw new IllegalStateException("Timen er allerede booket");
        }

        this.booked = true;
    }

    public void cancelBooking() {
        if (Duration.between(LocalDateTime.now(), this.start).toHours() < 2) {
            throw new IllegalStateException("Det er under to timer til timen, den kan ikke kanselleres");
        }

        if (!this.booked) {
            throw new IllegalStateException("Timen er ikke booket");
        }

        this.booked = false;
    }

    
}
