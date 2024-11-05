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

public class TreatmentDeserializer extends JsonDeserializer<List<Treatment>>{
    @Override
    public List<Treatment> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        // TODO Auto-generated method stub
        JsonNode node = p.getCodec().readTree(p);
        List<Treatment> list = new ArrayList<>();
        if (node.isArray()) {
            for (JsonNode n : node) {
                String navn = n.get("name").asText();
                int price = n.get("price").asInt();
                Treatment t = new Treatment(navn, price);

                list.add(t);
            } 
        }
        

        return list;
    }
}
