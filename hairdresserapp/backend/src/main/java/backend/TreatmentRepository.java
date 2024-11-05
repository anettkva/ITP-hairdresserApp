package backend;


import java.io.IOException;
import java.util.List;
import java.util.Optional;

import core.Treatment;

public interface TreatmentRepository {
    Optional<Treatment> findByName(String name) throws IOException;
    List<Treatment> findAll() throws IOException;
    void save(Treatment treatment) throws IOException;
    void deleteByName(String name) throws IOException;
}
