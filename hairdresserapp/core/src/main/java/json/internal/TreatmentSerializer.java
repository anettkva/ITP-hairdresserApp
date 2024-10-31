package json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Treatment;

public class TreatmentSerializer extends JsonSerializer<Treatment>{

    @Override
    public void serialize(Treatment treatment, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub
        gen.writeStartObject();

        gen.writeStringField("name", treatment.getName());
        gen.writeStringField("price", String.valueOf(treatment.getPrice()));

        gen.writeEndObject();
    }
    
}
