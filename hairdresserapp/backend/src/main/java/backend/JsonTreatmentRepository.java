package backend;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import core.Treatment;
import json.TreatmentFilehandling;

@Repository
public class JsonTreatmentRepository implements TreatmentRepository{

    private TreatmentFilehandling filehandling;

    @Autowired
    public JsonTreatmentRepository() {
        this.filehandling = new TreatmentFilehandling();
    }

    @Override 
    public List<Treatment> findAll() throws IOException {
        return filehandling.readFromFile();
    }

    @Override
    public Optional<Treatment> findByName(String name) throws IOException {
        List<Treatment> treatments = findAll();
        return  treatments.stream().filter(t -> t.getName().equalsIgnoreCase(name)).findFirst();
        }

    

    @Override
    public void save(Treatment treatment) throws IOException {
        filehandling.writeToFile(treatment);
    }

    @Override
    public void deleteByName(String name) throws IOException {
        List<Treatment> treatments = findAll();
        treatments = treatments.stream().filter(t -> !t.getName().equalsIgnoreCase(name)).collect(Collectors.toList());
        filehandling.reset();
        for (Treatment t : treatments) {
            filehandling.writeToFile(t);
        }

    }

   
}
