package json.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import core.Treatment;

/**
 * Custom deserializer for a list of {@link Treatment} objects.
 * 
 * This deserializer defines how JSON data representing a list of treatments
 * is converted into Java {@link Treatment} objects.
 */
public class TreatmentDeserializer extends JsonDeserializer<List<Treatment>> {
    
    /**
     * Deserializes JSON content into a list of {@link Treatment} objects.
     * 
     * This method parses the JSON array, extracts the "name" and "price" fields
     * for each treatment, creates corresponding {@link Treatment} objects, and
     * adds them to a list.
     *
     * @param p the {@link JsonParser} used to parse the JSON content
     * @param ctxt the {@link DeserializationContext} that can be used to access
     *        information about this deserialization process
     * @return a {@code List<Treatment>} containing the deserialized Treatment objects
     * @throws IOException if an I/O error occurs during parsing
     * @throws JacksonException if a processing error occurs during deserialization
     */
    @Override
    public List<Treatment> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        JsonNode node = p.getCodec().readTree(p);
        List<Treatment> list = new ArrayList<>();
   
        if (node.isArray()) {
            for (JsonNode n : node) {
                String name = n.get("name").asText();
                int price = n.get("price").asInt();

                Treatment t = new Treatment(name, price);
                list.add(t);
            } 
        }

        return list;
    }
}
