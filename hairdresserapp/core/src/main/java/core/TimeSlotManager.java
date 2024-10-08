package core;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TimeSlotManager {
    private List<TimeSlot> AllTimeSlots;

    public TimeSlotManager() throws IOException{
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

    public void addTimeSlot(TimeSlot timeSlot){
        AllTimeSlots.add(timeSlot);

    }

    public List<TimeSlot> getAvailableTimeSlots(){
        List<TimeSlot> notBookedSlots = new ArrayList<>();
        for (TimeSlot timeSlot : AllTimeSlots){
            if (!timeSlot.isBooked()){
                notBookedSlots.add(timeSlot);
            }
        }
        return notBookedSlots;
    }

    public void bookTimeSlot(LocalDateTime startTime){
        for (TimeSlot timeSlot : AllTimeSlots){
            if (timeSlot.getStartTime().equals(startTime) && !timeSlot.isBooked()){
                timeSlot.book();
                System.out.println("Timen den" + startTime + "er booket.");
                return;
            } 
        }
        throw new IllegalArgumentException("Timen er allerede booket");
    }

    public void cancelBooking(LocalDateTime startTime){
        for (TimeSlot timeSlot : AllTimeSlots){
            if (timeSlot.getStartTime().equals(startTime)){
                timeSlot.cancelBooking();
                System.out.println("Timen " + startTime + "er avbestilt");
                return;
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



    public static void main(String[] args) throws IOException {
        TimeSlotManager manager = new TimeSlotManager();

        manager.getAllTimeSlotsToString();
    }
}

