package backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        Optional<Treatment> treatment= treatments.stream().filter(t -> t.getName().equals(name)).findFirst();
        return treatment;
    }

    

    @Override
    public void save(Treatment treatment) throws IOException {
        filehandling.writeToFile(treatment);
    }

    @Override
    public void deleteByName(String name) throws IOException {
        List<Treatment> treatments = findAll();
        List<Treatment> list = new ArrayList<>();
        for (Treatment t : treatments) {
            if(!t.getName().equals(name)) {
                list.add(t);
            }
        }
    
        filehandling.reset();
        for (Treatment t : list) {
            save(t);
        }

    }

   
}