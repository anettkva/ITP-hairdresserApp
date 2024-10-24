package backend;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import core.Treatment;
import backend.TreatmentRepository;


@Service
public class TreatmentService {
    
    @Autowired
    private TreatmentRepository treatmentRepository;


    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    public Treatment getTreatmentById(Long id) {
        return treatmentRepository.findById(id).orElse(null);
    }

    public Treatment createTreatment(Treatment treatment) {
        return treatmentRepository.save(treatment);
    }

    public Treatment updateTreatment(Long id, Treatment treatmentDetails) {
        return treatmentRepository.findById(id).map(treatment -> {
            treatment.setName(treatmentDetails.getName());
            treatment.setPrice(treatmentDetails.getPrice());

            return treatmentRepository.save(treatment);
        }).orElse(null);
    }

    public boolean deleteTreatment(Long id) {
        return treatmentRepository.findById(id).map(treatment -> {
            treatmentRepository.delete(treatment);
            return true;
        }).orElse(false);
    }
}
