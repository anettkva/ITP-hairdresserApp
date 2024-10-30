package core;

public class Treatment {
    private int price;
    private int durationMinutes;
    private String name;

    public Treatment(String name, int price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        }
        this.price = price;
        this.durationMinutes = 60;
        this.name = name;
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



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    

}
