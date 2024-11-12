package backend;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.Treatment;

/**
 * REST controller for managing treatments.
 * 
 * This controller provides endpoints to retrieve all treatments, add a new treatment,
 * delete an existing treatment by name, and calculate the total price of all treatments.
 * It utilizes the {@link TreatmentService} to perform business logic and interacts with
 * clients via HTTP requests.
 */
@RestController
@RequestMapping("/api/treatments")
public class RestTreatmentController {

    /**
     * Logger instance for logging information and debugging purposes.
     */
    private static final Logger logger = LoggerFactory.getLogger(RestTreatmentController.class);

    /**
     * Service class for handling treatment-related operations.
     */
    private final TreatmentService treatmentService;

    /**
     * Constructs a new {@link RestTreatmentController} instance and initializes the
     * {@link TreatmentService}.
     * 
     * This constructor is annotated with {@link Autowired} to allow Spring to manage its dependencies.
     */
    @Autowired
    public RestTreatmentController() {
        this.treatmentService = new TreatmentService();
    }

    /**
     * Handles GET requests to retrieve all chosen treatments.
     * 
     * This endpoint responds to GET requests at {@code /api/treatments} by returning a list
     * of all available {@link Treatment} objects.
     * 
     * @return a {@link List} of {@link Treatment} objects representing all chosen treatments
     * @throws IOException if an I/O error occurs during retrieval of treatments
     */
    @GetMapping
    public List<Treatment> getChosenTreatments() throws IOException {
        logger.info("GET request received for chosen treatments");
        return treatmentService.getAllTreatments();
    }

    /**
     * Handles POST requests to add a new treatment.
     * 
     * This endpoint responds to POST requests at {@code /api/treatments} by adding a new
     * {@link Treatment} object to the storage.
     * 
     * @param treatment the {@link Treatment} object to be added, received in the request body
     * @throws IOException if an I/O error occurs during the addition of the treatment
     */
    @PostMapping
    public void addTreatment(@RequestBody Treatment treatment) throws IOException {
        treatmentService.addTreatment(treatment);
        logger.info("POST request received for added treatment");
    }

    /**
     * Handles DELETE requests to remove a treatment by name.
     * 
     * This endpoint responds to DELETE requests at {@code /api/treatments/{name}} by removing
     * the {@link Treatment} with the specified name from the storage.
     * 
     * @param name the name of the {@link Treatment} to be deleted, extracted from the URL path
     * @return a {@link ResponseEntity} indicating the outcome of the deletion operation
     * @throws IOException if an I/O error occurs during the deletion process
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteTreatment(@PathVariable("name") String name) throws IOException {
        Optional<Treatment> treatment = treatmentService.findByName(name);
        logger.info("DELETE request received for treatment with name: {}", name);
        if (treatment != null) { // Note: Optional will never be null; consider using treatment.isPresent()
            treatmentService.deleteTreatment(name);
            logger.info("Treatment with name '{}' deleted successfully", name);
            return ResponseEntity.ok().build();
        } else {
            logger.warn("Treatment with name '{}' not found", name);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Handles POST requests to calculate the total price of all treatments.
     * 
     * This endpoint responds to POST requests at {@code /api/treatments/calculateTotalPrice} by
     * computing and returning the total price of all available treatments.
     * 
     * @return a {@link ResponseEntity} containing the total price as a {@code Double}
     * @throws IOException if an I/O error occurs during the price calculation
     */
    @PostMapping("/calculateTotalPrice")
    public ResponseEntity<Double> calculateTotalPrice() throws IOException {
        logger.info("POST request received for calculating total price of treatments");
        double totalPrice = treatmentService.calculateTotalPrice();
        logger.info("Total price calculated: {}", totalPrice);
        return ResponseEntity.ok(totalPrice);
    }

}

