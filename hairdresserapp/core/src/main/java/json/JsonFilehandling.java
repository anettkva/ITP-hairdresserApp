package json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import core.TimeSlot;
import json.internal.BookingDeserializer;

/**
 * Handles file operations related to TimeSlots using JSON format.
 * 
 * This class provides methods to write TimeSlot objects to a JSON file,
 * read TimeSlot objects from the JSON file, and reset the file by clearing its contents.
 */
public class JsonFilehandling {
    
    /**
     * ObjectMapper instance for JSON serialization and deserialization.
     */
    private ObjectMapper objectMapper;

    /**
     * Constructs a new JsonFilehandling instance with a configured ObjectMapper.
     * 
     * The ObjectMapper is configured to handle Java 8 date and time types
     * and to disable writing dates as timestamps.
     */
    public JsonFilehandling() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    /**
     * Writes a TimeSlot object to the JSON file.
     * 
     * If the JSON file already contains TimeSlot entries, the new TimeSlot is added to the existing list.
     * Otherwise, a new list is created with the provided TimeSlot.
     * 
     * Additionally, marks the TimeSlot as booked before adding it to the list.
     * 
     * @param timeSlot the TimeSlot object to write to the file
     * @throws IOException if an I/O error occurs during writing to the file
     */
    public void writeToFile(TimeSlot timeSlot) throws IOException {
        List<TimeSlot> bookings;
        File myFile = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        
        if (myFile.length() != 0) {
            bookings = readFromFile();
        } else {
            bookings = new ArrayList<>(); 
        }
        
        timeSlot.setBooked(true);
        bookings.add(timeSlot);
        
        this.objectMapper.writeValue(myFile, bookings); 
    }

    /**
     * Reads and returns a list of TimeSlot objects from the JSON file.
     * 
     * If the JSON file is empty, an empty list is returned.
     * 
     * @return a List of TimeSlot objects read from the file
     * @throws IOException if an I/O error occurs during reading from the file
     */
    public List<TimeSlot> readFromFile() throws IOException {
        File myFile = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        
        if (myFile.length() == 0) {
            return new ArrayList<>();
        }
        
        JsonParser parser = this.objectMapper.getFactory().createParser(new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json"));
        DeserializationContext deserContext = this.objectMapper.getDeserializationContext();

        return new BookingDeserializer().deserialize(parser, deserContext); 
    } 

    /**
     * Resets the JSON file by clearing its contents.
     * 
     * This method effectively empties the "TimeSlotOverview.json" file,
     * removing all previously stored TimeSlot entries.
     * 
     * @throws IOException if an I/O error occurs during resetting the file
     */
    public void reset() throws IOException {
        File file = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        new FileOutputStream(file).close();
    }
}



