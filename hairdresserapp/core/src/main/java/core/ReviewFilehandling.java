package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReviewFilehandling {
    
    public void writeToFile(String text) throws IOException {
        File myFile = new File("../../hairdresserapp/Reviews.txt");
        try (FileWriter myWriter = new FileWriter(myFile, true)) {
            myWriter.write("\n" + text + "\n");
        }
    }

    public String readFromFile() throws FileNotFoundException {
        File myFile = new File("../../hairdresserapp/Reviews.txt");
        Scanner scanner = new Scanner(myFile);
        String text = "";
        while (scanner.hasNextLine()) {
           text += scanner.nextLine() + "\n"; 
        }
        scanner.close();
        return text;
        
    }

}
