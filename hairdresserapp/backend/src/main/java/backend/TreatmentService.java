package backend;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;


@Service
public class TreatmentService {
    
    @Autowired
    private TreatmentRepository TreatmentRepository;

    
}
