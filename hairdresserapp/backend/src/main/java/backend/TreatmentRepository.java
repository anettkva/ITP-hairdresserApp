package backend;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import core.Treatment;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    Optional<Treatment> findByName(String name);
    List<Treatment> findAllByNameIn(List<String> names);
    
}
