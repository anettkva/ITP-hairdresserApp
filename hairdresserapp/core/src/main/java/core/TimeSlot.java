public class TimeSlot {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isBooked;


    public TimeSlot(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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
        if (LocalDateTime.now() - startTime < 2) {
            throw new IllegalStateExeption("Det er under to timer til timen, den kan ikke kanselleres");
        }

        if (!this.isBooked) {
            throw new IllegalStateExeption("Timen er ikke booket");
        }

        this.isBooked = false;
    }

    
}
