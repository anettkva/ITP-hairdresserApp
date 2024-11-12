package core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Handles file operations related to user reviews.
 * 
 * This class provides methods to write reviews to a text file and read reviews from the file.
 * It manages the persistence of user-generated reviews by appending new reviews and retrieving existing ones.
 */
public class ReviewFilehandling {
    
    /**
     * Writes a review to the Reviews.txt file.
     * 
     * This method appends the provided text to the Reviews.txt file located at the specified path.
     * Each review is separated by newline characters to ensure readability.
     * 
     * @param text the review text to write to the file
     * @throws IOException if an I/O error occurs during writing to the file
     */
    public void writeToFile(String text) throws IOException {
        File myFile = new File("../../hairdresserapp/Reviews.txt");
        try (FileWriter myWriter = new FileWriter(myFile, true)) {
            myWriter.write("\n" + text + "\n");
        }
    }

    /**
     * Reads all reviews from the Reviews.txt file.
     * 
     * This method reads the content of the Reviews.txt file and returns it as a single string.
     * Each review is separated by newline characters to maintain the original formatting.
     * 
     * @return a {@code String} containing all the reviews from the file
     * @throws FileNotFoundException if the Reviews.txt file does not exist
     */
    public String readFromFile() throws FileNotFoundException {
        File myFile = new File("../../hairdresserapp/Reviews.txt");
        Scanner scanner = new Scanner(myFile);
        StringBuilder text = new StringBuilder();
        while (scanner.hasNextLine()) {
           text.append(scanner.nextLine()).append("\n"); 
        }
        scanner.close();
        return text.toString();
    }

}

