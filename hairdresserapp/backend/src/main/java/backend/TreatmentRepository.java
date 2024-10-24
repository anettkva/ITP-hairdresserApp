package backend;

import org.springframework.data.jpa.repository.JpaRepository;
import core.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    
}
