package json;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import core.TimeSlot;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFilehandling {

    public void writeToFile(TimeSlot time) throws IOException{
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.writeValue(new File("hairdresserapp./TimeSlotOverveiw.json"), time + "\n"); 

    }

    public List<TimeSlot> readFromFile() {
        List<TimeSlot> list = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper(); 

        try (BufferedReader reader = new BufferedReader(new FileReader("hairdresserapp/TimeSlotOverview.json"))) {
            String line = reader.readLine();
            while (line != null) {
                TimeSlot timeslot = objectMapper.readValue(line, TimeSlot.class);
                list.add(timeslot);
            }
        }
        
        return list;
    } 

    public void reset() throws IOException{
        File file = new File(Paths.get("hairdresserapp/TimeSlotOverview.json").toString());
        file.setWritable(true);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(Paths.get("hairdresserapp/TimeSlotOverview.json").toString()));
        bufferedOutputStream.write("{}".getBytes());
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }



    public static void main(String[] args) {
     
        
    }
    
}
