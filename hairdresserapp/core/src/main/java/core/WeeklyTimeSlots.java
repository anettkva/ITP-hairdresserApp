package core;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WeeklyTimeSlots {
    private List<TimeSlot> AllTimeSlots;

    public WeeklyTimeSlots() throws IOException{
        this.AllTimeSlots = new ArrayList<>();

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(15,0);
        LocalDate today = LocalDate.now();

        for (int day = 1; day <8 ; day ++) {
            LocalDate currentDay = today.plusDays(day);

            for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusHours(1)) {
                AllTimeSlots.add(new TimeSlot(LocalDateTime.of(currentDay, time)));
            }
        }
    }


    public List<TimeSlot> getAllTimeSlots() {
        return AllTimeSlots;
    } 

    public void getAllTimeSlotsToString() {
        for (TimeSlot slot : AllTimeSlots) {
            System.out.println(slot.getStartTime());
        }
    }

}



