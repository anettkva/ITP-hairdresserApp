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


public class JsonFilehandling {

    private ObjectMapper objectMapper;
    

    public JsonFilehandling() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        

    }


    public void writeToFile(TimeSlot timeSlot) throws IOException{
        List<TimeSlot> bookings;
        File myFile = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        if (myFile.length() != 0) {
            bookings = readFromFile();
        }
        else {
           bookings = new ArrayList<>(); 
        }
        
        timeSlot.setBooked(true);
        bookings.add(timeSlot);
        
        
        this.objectMapper.writeValue(myFile, bookings); 
        

    }

    public List<TimeSlot> readFromFile() throws IOException{
        File myFile = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        if (myFile.length() == 0) {
            return new ArrayList<>();
        }
        JsonParser parser = this.objectMapper.getFactory().createParser(new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json"));
        DeserializationContext deserContext = this.objectMapper.getDeserializationContext();
        return new BookingDeserializer().deserialize(parser, deserContext); 
    
    } 

    public void reset() throws IOException{
        File file = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        new FileOutputStream(file).close();
    }

}


