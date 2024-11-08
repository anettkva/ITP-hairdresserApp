package backend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.PriceCalculator;
import core.Treatment;
import json.TreatmentFilehandling;


@Service
public class TreatmentService {
    

    private TreatmentFilehandling filehandling;

    @Autowired
    public TreatmentService() {
        this.filehandling = new TreatmentFilehandling();
    }


    public List<Treatment> getAllTreatments() throws IOException {
        return filehandling.readFromFile();
    }

    public Optional<Treatment> findByName(String name) throws IOException {
        List<Treatment> treatments = getAllTreatments();
        Optional<Treatment> treatment= treatments.stream().filter(t -> t.getName().equals(name)).findFirst();
        return treatment;
    }

    
    public void addTreatment(Treatment treatment) throws IOException {
        filehandling.writeToFile(treatment);
    }


    public void deleteTreatment(String name) throws IOException {
        List<Treatment> treatments = getAllTreatments();
        List<Treatment> list = new ArrayList<>();
        for (Treatment t : treatments) {
            if(!t.getName().equals(name)) {
                list.add(t);
            }
        }
    
        filehandling.reset();
        for (Treatment t : list) {
            addTreatment(t);
        }

    }
    

    public double calculateTotalPrice() throws IOException {
        List<Treatment> treatments = getAllTreatments();
        PriceCalculator pc = new PriceCalculator();
        return pc.CalculateTotalPrice(treatments);
    }

    
}
