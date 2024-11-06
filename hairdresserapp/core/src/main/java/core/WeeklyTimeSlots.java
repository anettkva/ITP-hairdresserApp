package core;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WeeklyTimeSlots {
    private List<TimeSlot> allTimeSlots;

    public WeeklyTimeSlots() throws IOException{
        this.allTimeSlots = new ArrayList<>();

        LocalTime startTime = LocalTime.of(9, 0);
        LocalTime endTime = LocalTime.of(15,0);
        LocalDate today = LocalDate.now();

        for (int day = 1; day <8 ; day ++) {
            LocalDate currentDay = today.plusDays(day);

            for (LocalTime time = startTime; !time.isAfter(endTime); time = time.plusHours(1)) {
                allTimeSlots.add(new TimeSlot(LocalDateTime.of(currentDay, time)));
            }
        }
    }


    public List<TimeSlot> getAllTimeSlots() {
        return allTimeSlots;
    } 

    
}



