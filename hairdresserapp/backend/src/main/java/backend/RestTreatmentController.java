package backend;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import core.PriceCalculator;
import core.Treatment;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

@RestController
@RequestMapping("/api/treatments")
public class RestTreatmentController {


    
    @Autowired
    private TreatmentService treatmentService;




 

    // @GetMapping
    // public List<Treatment> getAllTreatments() {
    //     return treatmentService.getAllTreatments();
    // }

    @GetMapping
    public List<Treatment> getChosenTreatments() {
        return treatmentService.getAllTreatments();
    }

    @PostMapping
    public void addTreatment(@RequestBody Treatment treatment) {
        treatmentService.addTreatment(treatment);
    }
    

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteTreatment(@PathVariable String name){
        Treatment treatment = treatmentService.findByName(name);
        if (!treatment.equals(null)){
            treatmentService.deleteTreatment(name);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }


    @PostMapping("/calculateTotalPrice")
    public ResponseEntity<Double> calculateTotalPrice() {
        double totalPrice = treatmentService.calculateTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }

    

    

        

}
