package main.java.hairdresserapp;

public class Treatment {
    private int price;
    private int durationMinutes;

    public Treatment(int price, int durationMinutes) {
        if (price < 0 || durationMinutes < 0 || durationMinutes > 420) {
            throw new IllegalArgumentException();
        }
        this.price = price;
        this.durationMinutes = durationMinutes;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        this.price = price;
    }

    public int getduration() {
        return durationMinutes;
    }

    public void setduration(int durationMinutes) {
        if (durationMinutes < 0 || durationMinutes > 420) {
            throw new IllegalArgumentException("duration must be between 0 and 420 minutes long");
        }
        this.durationMinutes = durationMinutes;
    }
}
