package core;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Treatment {
    @JsonProperty("price")
    private int price;

    @JsonProperty("duration")
    private int duration;

    @JsonProperty("name")
    private String name;

    public Treatment() {}

    public Treatment(String name, int price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        }
        this.price = price;
        this.duration = 60;
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
        return duration;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    

    

}
