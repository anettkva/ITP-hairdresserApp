package json.internal;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import core.TimeSlot;


public class BookingDeserializer extends JsonDeserializer<List<TimeSlot>>{

    //@Override
    //public TimeSlot deserialize(JsonParser p)

    @Override
    public List<TimeSlot> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        // TODO Auto-generated method stub
        JsonNode node = p.getCodec().readTree(p);
        List<TimeSlot> list = new ArrayList<>();
        if (node == null) {
            return list;
        }
        else if (node.isArray()) {
            for (JsonNode n : node) {
                String start = n.get("start").asText();
                Boolean booked = n.get("booked").asBoolean();

                LocalDateTime startTime = LocalDateTime.parse(start);
                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                //LocalDateTime startTime = LocalDateTime.parse(start, formatter);

                TimeSlot timeSlot = new TimeSlot(startTime);
                timeSlot.setBooked(booked);
                list.add(timeSlot);
            } 
        }
        

        return list;
    }
    
}
