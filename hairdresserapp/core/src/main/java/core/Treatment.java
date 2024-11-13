package core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a treatment offered by the hairdresser.
 * 
 * This class encapsulates the details of a treatment, including its name, price, and duration.
 * It provides constructors for creating treatment instances and methods to access and modify
 * the treatment's properties.
 */
public class Treatment {
    
    /**
     * The price of the treatment.
     */
    @JsonProperty("price")
    private int price;

    /**
     * The duration of the treatment in minutes.
     */
    @JsonProperty("duration")
    private int duration;

    /**
     * The name of the treatment.
     */
    @JsonProperty("name")
    private String name;

    /**
     * Default constructor for {@link Treatment}.
     * 
     * Required for JSON deserialization.
     */
    public Treatment() {}

    /**
     * Constructs a new {@link Treatment} with the specified name and price.
     * 
     * The duration is set to a default value of 60 minutes.
     * 
     * @param name the name of the treatment
     * @param price the price of the treatment
     * @throws IllegalArgumentException if the price is negative
     */
    public Treatment(String name, int price) {
        if (price < 0) {
            throw new IllegalArgumentException();
        }
        this.price = price;
        this.duration = 60;
        this.name = name;
    }

    /**
     * Retrieves the price of the treatment.
     * 
     * @return the price of the treatment
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the price of the treatment.
     * 
     * @param price the new price of the treatment
     * @throws IllegalArgumentException if the price is negative
     */
    public void setPrice(int price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price can't be negative");
        }
        this.price = price;
    }

    /**
     * Retrieves the duration of the treatment.
     * 
     * @return the duration of the treatment in minutes
     */
    public int getduration() {
        return duration;
    }

    /**
     * Retrieves the name of the treatment.
     * 
     * @return the name of the treatment
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the treatment.
     * 
     * @param name the new name of the treatment
     */
    public void setName(String name) {
        this.name = name;
    }

}

