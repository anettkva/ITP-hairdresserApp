package core;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a time slot for appointments.
 * 
 * This class encapsulates the details of a time slot, including its start and end times,
 * and whether it is booked. It provides methods to book and cancel bookings while enforcing
 * business rules such as booking time restrictions and cancellation policies.
 */
public class TimeSlot {
    
    /**
     * The start time of the time slot.
     */
    @JsonProperty("start")
    private LocalDateTime start;

    /**
     * Indicates whether the time slot is booked.
     */
    @JsonProperty("booked")
    private boolean booked;

    /**
     * The end time of the time slot.
     */
    @JsonProperty("end")
    private LocalDateTime end;

    /**
     * Default constructor for {@link TimeSlot}.
     * 
     * Required for JSON deserialization.
     */
    public TimeSlot() {
    }

    /**
     * Constructs a new {@link TimeSlot} with the specified start time.
     * 
     * Initializes the end time to one hour after the start time and sets the booking status to false.
     * 
     * @param start the start time of the time slot
     * @throws IllegalArgumentException if the start time is in the past, not on the hour, or outside business hours
     */
    public TimeSlot(LocalDateTime start) throws IOException {
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

    /**
     * Retrieves the start time of the time slot.
     * 
     * @return the start time as a {@link LocalDateTime}
     */
    public LocalDateTime getStart() {
        return start;
    }

    /**
     * Sets the start time of the time slot.
     * 
     * @param start the new start time to set
     */
    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    /**
     * Retrieves the end time of the time slot.
     * 
     * @return the end time as a {@link LocalDateTime}
     */
    public LocalDateTime getEnd() {
        return end;
    }

    /**
     * Sets the end time of the time slot.
     * 
     * @param end the new end time to set
     */
    public void setEnd(LocalDateTime end) {
        this.end = end;
    }

    /**
     * Checks if the time slot is booked.
     * 
     * @return {@code true} if the time slot is booked, {@code false} otherwise
     */
    public boolean isBooked() {
        return booked;
    }

    /**
     * Sets the booking status of the time slot.
     * 
     * @param isBooked {@code true} to mark the time slot as booked, {@code false} to mark it as available
     */
    public void setBooked(boolean isBooked) {
        this.booked = isBooked;
    }

    /**
     * Books the time slot.
     * 
     * Marks the time slot as booked if it is not already booked.
     * 
     * @throws IllegalStateException if the time slot is already booked
     */
    public void book() {
        if (this.booked) {
            throw new IllegalStateException("Timen er allerede booket");
        }

        this.booked = true;
    }

    /**
     * Cancels the booking of the time slot.
     * 
     * Marks the time slot as available if it is currently booked and the cancellation is made at least two hours before the start time.
     * 
     * @throws IllegalStateException if the time slot is not booked or if the cancellation is made less than two hours before the start time
     */
    public void cancelBooking() {
        if (Duration.between(LocalDateTime.now(), this.start).toHours() < 2) {
            throw new IllegalStateException("Det er under to timer til timen, den kan ikke kanselleres");
        }

        if (!this.booked) {
            throw new IllegalStateException("Timen er ikke booket");
        }

        this.booked = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimeSlot timeSlot = (TimeSlot) o;

        return booked == timeSlot.booked &&
               Objects.equals(start, timeSlot.start) &&
               Objects.equals(end, timeSlot.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, booked, end);
    }

    
}

