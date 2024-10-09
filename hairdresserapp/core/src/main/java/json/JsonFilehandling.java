package json;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.TimeSlot;
import json.internal.BookingDeserializer;

public class JsonFilehandling {

    private ObjectMapper objectMapper;
    

    public JsonFilehandling() {
        this.objectMapper = new ObjectMapper();
        

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
        JsonParser parser = objectMapper.getFactory().createParser(new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json"));
        DeserializationContext deserContext = objectMapper.getDeserializationContext();
        return new BookingDeserializer().deserialize(parser, deserContext); 
    
    } 

    public void reset() throws IOException{
        File file = new File("../../hairdresserapp/core/src/main/java/json/TimeSlotOverview.json");
        new FileOutputStream(file).close();
    }


    
}
