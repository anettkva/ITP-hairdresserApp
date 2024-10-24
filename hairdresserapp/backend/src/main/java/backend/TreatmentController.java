package backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import core.Treatment;

import java.util.List;
import backend.TreatmentService;

@RestController
@RequestMapping("api/treatments")
public class TreatmentController {
    
    @Autowired
    private TreatmentService treatmentService;

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Treatment> getTreatmentById(@PathVariable Long id) {
        Treatment treatment = treatmentService.getTreatmentById(id);
        return treatment != null ? ResponseEntity.ok(treatment) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Treatment> createTreatment(@RequestBody Treatment treatment){
        Treatment newTreatment = treatmentService.createTreatment(treatment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newTreatment);
    }


    @PutMapping ("/{id}")
    public ResponseEntity<Treatment> updateTreatment(@PathVariable Long id, @RequestBody Treatment treatmentDetails){
        Treatment updatedTreatment = treatmentService.updateTreatment(id, treatmentDetails);
        return updatedTreatment != null ? ResponseEntity.ok(treatmentDetails) : ResponseEntity.notFound().build();
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteTreatment(@PathVariable Long id){
        boolean isDeleted = treatmentService.deleteTreatment(id);
        return isDeleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
