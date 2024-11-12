package json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;

import core.Treatment;
import json.internal.TreatmentDeserializer;

/**
 * Handles file operations related to Treatments using JSON format.
 * 
 * This class provides methods to write treatments to a JSON file,
 * read treatments from the JSON file, and reset the file by clearing its contents.
 */
public class TreatmentFilehandling {
    
    /**
     * ObjectMapper instance for JSON serialization and deserialization.
     */
    private ObjectMapper objectMapper;
    
    /**
     * Constructs a new TreatmentFilehandling instance with a default ObjectMapper.
     */
    public TreatmentFilehandling() {
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Writes a Treatment object to the JSON file.
     * 
     * If the JSON file already contains treatments, the new treatment is added to the existing list.
     * Otherwise, a new list is created with the provided treatment.
     * 
     * @param treatment the Treatment object to write to the file
     * @throws IOException if an I/O error occurs during writing to the file
     */
    public void writeToFile(Treatment treatment) throws IOException {
        List<Treatment> chosen;
        File myFile = new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json");
        
        if (myFile.length() != 0) {
            chosen = readFromFile();
        } else {
            chosen = new ArrayList<>(); 
        }
        
        chosen.add(treatment);
        
        this.objectMapper.writeValue(myFile, chosen); 
    }

    /**
     * Reads and returns a list of Treatment objects from the JSON file.
     * 
     * If the JSON file is empty, an empty list is returned.
     * 
     * @return a List of Treatment objects read from the file
     * @throws IOException if an I/O error occurs during reading from the file
     */
    public List<Treatment> readFromFile() throws IOException {
        File myFile = new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json");
        
        if (myFile.length() == 0) {
            return new ArrayList<>();
        }
        
        JsonParser parser = objectMapper.getFactory().createParser(new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json"));
        DeserializationContext deserContext = objectMapper.getDeserializationContext();

        return new TreatmentDeserializer().deserialize(parser, deserContext); 
    } 

    /**
     * Resets the JSON file by clearing its contents.
     * 
     * This method effectively empties the "ChosenTreatments.json" file,
     * removing all previously stored treatments.
     * 
     * @throws IOException if an I/O error occurs during resetting the file
     */
    public void reset() throws IOException {
        File file = new File("../../hairdresserapp/core/src/main/java/json/ChosenTreatments.json");
        new FileOutputStream(file).close();
    }
}

