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

@RestController
@RequestMapping("/api/treatments")
public class RestTreatmentController {

    private static final Logger logger = LoggerFactory.getLogger(RestTreatmentController.class);

    
    private final TreatmentService treatmentService;

    @Autowired
    public RestTreatmentController() {
        this.treatmentService = new TreatmentService();
    }

    @GetMapping
    public List<Treatment> getChosenTreatments() throws IOException {
        logger.info("GET request received for chosen treatments" );
        return treatmentService.getAllTreatments();
        
    }

    @PostMapping
    public void addTreatment(@RequestBody Treatment treatment) throws IOException {
        treatmentService.addTreatment(treatment);
        logger.info("POST request received for added treatment");
    }
    

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteTreatment(@PathVariable("name") String name) throws IOException{
        Optional<Treatment> treatment = treatmentService.findByName(name);
        logger.info("DELETE request received for delete treatment from chosen for api/treatments/name");
        if (treatment != null){
            treatmentService.deleteTreatment(name);
            logger.info(String.valueOf(ResponseEntity.ok().build()));
            return ResponseEntity.ok().build();
        } else {
            System.out.println(ResponseEntity.notFound().build());
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping("/calculateTotalPrice")
    public ResponseEntity<Double> calculateTotalPrice() throws IOException {
        logger.info("POST request received for api/treatments/caclulateTotalPrice");
        double totalPrice = treatmentService.calculateTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }

    

    

        

}
