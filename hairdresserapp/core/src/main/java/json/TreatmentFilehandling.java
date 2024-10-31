package json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Treatment;
import json.internal.TreatmentDeserializer;

public class TreatmentFilehandling {
    private ObjectMapper objectMapper;
    

    public TreatmentFilehandling() {
        this.objectMapper = new ObjectMapper();
    }


    public void writeToFile(Treatment treatment) throws IOException{
        List<Treatment> chosen;
        File myFile = new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json");
        if (myFile.length() != 0) {
            chosen = readFromFile();
        }
        else {
           chosen = new ArrayList<>(); 
        }
        
        chosen.add(treatment);
        
        
        this.objectMapper.writeValue(myFile, chosen); 
        

    }

    public List<Treatment> readFromFile() throws IOException{
        JsonParser parser = objectMapper.getFactory().createParser(new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json"));
        DeserializationContext deserContext = objectMapper.getDeserializationContext();
        
        return new TreatmentDeserializer().deserialize(parser, deserContext); 
    
    } 

    public void reset() throws IOException{
        File file = new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json");
        new FileOutputStream(file).close();
    }
}
