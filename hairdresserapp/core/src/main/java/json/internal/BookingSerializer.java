package json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.TimeSlot;

/**
 * Custom serializer for the {@link TimeSlot} class.
 * 
 * This serializer defines how {@link TimeSlot} objects are converted to JSON format.
 * It specifies the structure and fields to include in the JSON representation.
 */
public class BookingSerializer extends JsonSerializer<TimeSlot> {

    /**
     * Serializes a {@link TimeSlot} object into JSON.
     * 
     * This method defines the JSON structure for a {@link TimeSlot} object by
     * writing the "start" and "booked" fields.
     *
     * @param timeSlot the {@link TimeSlot} object to serialize
     * @param gen the {@link JsonGenerator} used to write JSON content
     * @param serializers the {@link SerializerProvider} that can be used to get serializers for other types
     * @throws IOException if an I/O error occurs during serialization
     */
    @Override
    public void serialize(TimeSlot timeSlot, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("start", String.valueOf(timeSlot.getStart()));
        gen.writeBooleanField("booked", timeSlot.isBooked());
        gen.writeEndObject();
    }

}

