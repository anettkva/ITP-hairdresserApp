package backend;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import core.Treatment;


@Service
public class TreatmentService {
    
    @Autowired
    private TreatmentRepository treatmentRepository;


    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public Treatment getTreatmentById(String name) {
        return treatmentRepository.findByName(name).orElse(null);
    }

    public Treatment createTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Treatment updateTreatment(String name, Treatment treatmentDetails) {
        return treatmentRepository.findByName(name).map(treatment -> {
            treatment.setName(treatmentDetails.getName());
            treatment.setPrice(treatmentDetails.getPrice());

            return treatmentRepository.save(treatment);
        }).orElse(null);
    }

    public boolean deleteTreatment(String name) {
        return treatmentRepository.findByName(name).map(treatment -> {
            treatmentRepository.delete(treatment);
            return true;
        }).orElse(false);
    }
}
