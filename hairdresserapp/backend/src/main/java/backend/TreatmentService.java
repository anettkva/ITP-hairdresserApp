package backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.PriceCalculator;
import core.Treatment;
import json.TreatmentFilehandling;

/**
 * Service class for managing treatments.
 * 
 * This class provides methods to retrieve all treatments, find a treatment by name,
 * add a new treatment, delete an existing treatment, and calculate the total price
 * of all treatments. It interacts with the {@link TreatmentFilehandling} class to
 * handle file operations and utilizes {@link PriceCalculator} for price calculations.
 */
@Service
public class TreatmentService {
    
    /**
     * Handles file operations related to treatments.
     */
    private TreatmentFilehandling filehandling;

    /**
     * Price calculator for treatments.
     */
    private final PriceCalculator priceCalculator;

    /**
     * Constructs a new {@link TreatmentService} instance and initializes the
     * {@link TreatmentFilehandling}.
     * 
     * This constructor is annotated with {@link Autowired} to allow Spring to
     * manage its dependencies. It initializes the {@link TreatmentFilehandling}
     * used for reading and writing treatment data.
     */
    @Autowired
    public TreatmentService() {
        this(new TreatmentFilehandling(), new PriceCalculator());
    }

    /**
     * Constructs a new {@link TreatmentService} instance with injected dependencies.
     * 
     * @param filehandling    The {@link TreatmentFilehandling} instance.
     * @param priceCalculator The {@link PriceCalculator} instance.
     */
    @Autowired
    public TreatmentService(TreatmentFilehandling filehandling, PriceCalculator priceCalculator) {
        this.filehandling = filehandling;
        this.priceCalculator = priceCalculator;
    }

    /**
     * Retrieves all treatments from the storage.
     * 
     * This method reads all treatments using the {@link TreatmentFilehandling} class.
     *
     * @return a {@link List} of {@link Treatment} objects representing all available treatments
     * @throws IOException if an I/O error occurs during reading from the file
     */
    public List<Treatment> getAllTreatments() throws IOException {
        return filehandling.readFromFile();
    }

    /**
     * Finds a treatment by its name.
     * 
     * This method searches for a treatment with the specified name within the list of all treatments.
     *
     * @param name the name of the treatment to find
     * @return an {@link Optional} containing the {@link Treatment} if found, or empty if not found
     * @throws IOException if an I/O error occurs during reading from the file
     */
    public Optional<Treatment> findByName(String name) throws IOException {
        List<Treatment> treatments = getAllTreatments();
        Optional<Treatment> treatment = treatments.stream()
                .filter(t -> t.getName().equals(name))
                .findFirst();
        return treatment;
    }

    /**
     * Adds a new treatment to the storage.
     * 
     * This method writes the provided {@link Treatment} object to the storage using the
     * {@link TreatmentFilehandling} class.
     *
     * @param treatment the {@link Treatment} object to add
     * @throws IOException if an I/O error occurs during writing to the file
     */
    public void addTreatment(Treatment treatment) throws IOException {
        filehandling.writeToFile(treatment);
    }

    /**
     * Deletes a treatment by its name from the storage.
     * 
     * This method removes the treatment with the specified name by filtering it out from
     * the list of all treatments and then rewriting the updated list to the storage.
     *
     * @param name the name of the treatment to delete
     * @throws IOException if an I/O error occurs during reading from or writing to the file
     */
    public void deleteTreatment(String name) throws IOException {
        List<Treatment> treatments = getAllTreatments();
        List<Treatment> updatedTreatments = new ArrayList<>();
        for (Treatment t : treatments) {
            if (!t.getName().equals(name)) {
                updatedTreatments.add(t);
            }
        }
    
        filehandling.reset();
        for (Treatment t : updatedTreatments) {
            addTreatment(t);
        }
    }

    /**
     * Calculates the total price of all treatments.
     * 
     * This method utilizes the {@link PriceCalculator} class to compute the sum of prices
     * of all available treatments.
     *
     * @return the total price of all treatments as a {@code double}
     * @throws IOException if an I/O error occurs during reading from the file
     */
    public double calculateTotalPrice() throws IOException {
        List<Treatment> treatments = getAllTreatments();
        return priceCalculator.CalculateTotalPrice(treatments);
    }
}

