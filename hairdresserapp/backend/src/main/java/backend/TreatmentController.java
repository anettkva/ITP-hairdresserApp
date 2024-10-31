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
public class TreatmentController {

    @FXML 
    TextField totalPriceField; 

    @FXML 
    TextArea overViewTextArea;

    
    @Autowired
    private TreatmentService treatmentService;

    @Autowired
    private TreatmentRepository treatmentRepository;


    @Autowired
    private PriceCalculator priceCalculator;


    @FXML
    protected void initialize() {
        priceCalculator= new PriceCalculator();

    }
 

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @PostMapping
    private Treatment addTreatment(@RequestBody Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public void handleTreatment(Treatment treatment) throws IOException {
        if (!getAllTreatments().contains(treatment)) {
            addTreatment(treatment);
        } else {
            deleteTreatment(treatment.getName());
        }
        calculateTotalPrice(); 
        handleShowOverveiw();   
    }
    

    @DeleteMapping("/{name}")
    public ResponseEntity<?> deleteTreatment(@PathVariable String name){
        return treatmentRepository.findByName(name).map(treatment -> {
            treatmentRepository.delete(treatment);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/calculateTotalPrice")
    public ResponseEntity<Double> calculateTotalPrice() {
        List<Treatment> treatments = treatmentRepository.findAll();

        double totalPrice = priceCalculator.CalculateTotalPrice(treatments);
        return ResponseEntity.ok(totalPrice);
    }

    @FXML
    public void handleShowOverveiw() throws IOException {
        List<Treatment> treatments = getChosenTreatments();
        for (Treatment t : treatments) {
            overViewTextArea.appendText(t.getName() + ": " + t.getPrice() + " kr, Varighet (min): " + t.getduration() + "\n" );
        }
    }

    @GetMapping
    private List<Treatment> getChosenTreatments() {
        return treatmentRepository.findAll();
    }

    @FXML 
    void handleBookingButton() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("Booking.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }
        

}
