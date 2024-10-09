package json.internal;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import core.TimeSlot;


public class BookingDeserializer extends JsonDeserializer<List<TimeSlot>>{

    //@Override
    //public TimeSlot deserialize(JsonParser p)

    @Override
    public List<TimeSlot> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        // TODO Auto-generated method stub
        JsonNode node = p.getCodec().readTree(p);
        List<TimeSlot> list = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode n : node) {
                String start = n.get("Start").asText();
                Boolean booked = n.get("Booked").asBoolean();

                //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-ddTHH:mm");
                LocalDateTime startTime = LocalDateTime.parse(start);

                TimeSlot timeSlot = new TimeSlot(startTime);
                timeSlot.setBooked(booked);
                list.add(timeSlot);
            } 
        }
        

        return list;
    }
    
}
