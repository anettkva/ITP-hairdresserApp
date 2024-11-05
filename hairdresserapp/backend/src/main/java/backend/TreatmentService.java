package backend;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.PriceCalculator;
import core.Treatment;


@Service
public class TreatmentService {
    
    @Autowired
    private final TreatmentRepository treatmentRepository;

    @Autowired
    public TreatmentService() {
        this.treatmentRepository = new JsonTreatmentRepository();
    }


    public List<Treatment> getAllTreatments() {
        try {
            return treatmentRepository.findAll();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public Treatment findByName(String name) {
        try {
            return treatmentRepository.findByName(name).orElse(null);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    
    public void addTreatment(Treatment treatment) {
        try {
            treatmentRepository.save(treatment);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void deleteTreatment(String name) {
        try{
            treatmentRepository.deleteByName(name);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double calculateTotalPrice() throws IOException {
        List<Treatment> treatments = treatmentRepository.findAll();
        PriceCalculator pc = new PriceCalculator();
        return pc.CalculateTotalPrice(treatments);
    }

    
}
