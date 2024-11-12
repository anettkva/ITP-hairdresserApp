package json.internal;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import core.TimeSlot;

/**
 * Custom deserializer for a list of {@link TimeSlot} objects.
 * 
 * This deserializer defines how JSON data representing a list of time slots
 * is converted into Java {@link TimeSlot} objects.
 */
public class BookingDeserializer extends JsonDeserializer<List<TimeSlot>> {

    /**
     * Deserializes JSON content into a list of {@link TimeSlot} objects.
     * 
     * This method parses the JSON array, extracts the "start" and "booked" fields
     * for each time slot, creates corresponding {@link TimeSlot} objects, and
     * adds them to a list.
     *
     * @param p the {@link JsonParser} used to parse the JSON content
     * @param ctxt the {@link DeserializationContext} that can be used to access
     *        information about this deserialization process
     * @return a {@code List<TimeSlot>} containing the deserialized TimeSlot objects
     * @throws IOException if an I/O error occurs during parsing
     * @throws JacksonException if a processing error occurs during deserialization
     */
    @Override
    public List<TimeSlot> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
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
                TimeSlot timeSlot = new TimeSlot(startTime);
                timeSlot.setBooked(booked);
                list.add(timeSlot);
            } 
        }

        return list;
    }
}

