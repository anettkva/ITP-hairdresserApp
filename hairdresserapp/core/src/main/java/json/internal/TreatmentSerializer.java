package json.internal;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import core.Treatment;

/**
 * Custom serializer for the {@link Treatment} class.
 * 
 * This serializer defines how {@link Treatment} objects are converted to JSON format.
 * It specifies the structure and fields to include in the JSON representation.
 */
public class TreatmentSerializer extends JsonSerializer<Treatment> {
    
    /**
     * Serializes a {@link Treatment} object into JSON.
     * 
     * This method defines the JSON structure for a {@link Treatment} object by
     * writing the "name" and "price" fields.
     *
     * @param treatment the {@link Treatment} object to serialize
     * @param gen the {@link JsonGenerator} used to write JSON content
     * @param serializers the {@link SerializerProvider} that can be used to get serializers for other types
     * @throws IOException if an I/O error occurs during serialization
     */
    @Override
    public void serialize(Treatment treatment, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("name", treatment.getName());
        gen.writeStringField("price", String.valueOf(treatment.getPrice()));

        gen.writeEndObject();
    }
}
