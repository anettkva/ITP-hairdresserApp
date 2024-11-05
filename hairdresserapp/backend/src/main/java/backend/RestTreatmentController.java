package backend;

import java.io.IOException;
import java.util.List;

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

    
    private final TreatmentService treatmentService;

    @Autowired
    public RestTreatmentController() {
        this.treatmentService = new TreatmentService();
    }

    @GetMapping
    public List<Treatment> getChosenTreatments() {
        return treatmentService.getAllTreatments();
    }

    @PostMapping
    public void addTreatment(@RequestBody Treatment treatment) {
        treatmentService.addTreatment(treatment);
    }
    

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteTreatment(@PathVariable("name") String name){
        Treatment treatment = treatmentService.findByName(name);
        if (treatment != null){
            treatmentService.deleteTreatment(name);
            System.out.println(ResponseEntity.ok().build());
            return ResponseEntity.ok().build();
        } else {
            System.out.println(ResponseEntity.notFound().build());
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping("/calculateTotalPrice")
    public ResponseEntity<Double> calculateTotalPrice() throws IOException {
        double totalPrice = treatmentService.calculateTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }

    

    

        

}
