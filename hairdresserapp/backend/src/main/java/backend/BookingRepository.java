package backend;

import java.io.IOException;
import java.util.List;

import core.TimeSlot;




public interface BookingRepository {
    public List<TimeSlot> getBookedSlots() throws IOException;
    public void bookSlot(TimeSlot timeSlot) throws IOException;

}
