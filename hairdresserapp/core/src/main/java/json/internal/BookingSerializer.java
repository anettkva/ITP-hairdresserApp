package json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.TimeSlot;

public class BookingSerializer extends JsonSerializer<TimeSlot>{


    @Override
    public void serialize(TimeSlot timeSlot, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub
        gen.writeStartObject();

        gen.writeStringField("Start", String.valueOf(timeSlot.getStartTime()));
        gen.writeBooleanField("Booked", timeSlot.isBooked());
        

        gen.writeEndObject();
    }
    
}
