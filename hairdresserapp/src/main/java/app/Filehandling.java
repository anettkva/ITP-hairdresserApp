package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filehandling {
    
    public void writeToFile(Treatment t) throws IOException{
        File myFile = new File("hairdresserapp/src/main/java/app/TreatmentsAndPrices.txt");

        try (FileWriter myWriter = new FileWriter(myFile, true)) {
            myWriter.write(t.getName() + ": " + t.getPrice() + "kr, " + t.getduration() +"min"+ "\n");
        }
    }

    public List<Treatment> loadFromFile() throws IOException {
        File myFile = new File("hairdresserapp/src/main/java/app/TreatmentsAndPrices.txt");
        List<Treatment> list = new ArrayList<>();

        try (Scanner scanner = new Scanner(myFile)) {

            while (scanner.hasNextLine()) {
                String s = scanner.nextLine();
                String[] nameList = s.split(":");
                String[] priceAndDurationList = nameList[1].split(","); 
                String price = priceAndDurationList[0].split("k")[0];
                String duration = priceAndDurationList[1].split("m")[0];
                
                Treatment t = new Treatment(nameList[0], Integer.valueOf(price), Integer.valueOf(duration));

                list.add(t);
            }
        }

        return list;
    }

    public void reset() {
        File myFile = new File("hairdresserapp/src/main/java/app/TreatmentsAndPrices.txt");

        try (FileWriter myWriter = new FileWriter(myFile, false)) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
