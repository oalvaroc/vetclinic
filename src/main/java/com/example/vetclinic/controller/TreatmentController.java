package com.example.vetclinic.controller;

import com.example.vetclinic.model.Animal;
import com.example.vetclinic.model.TreatmentDAO;
import com.example.vetclinic.model.Treatment;
import java.util.Date;
import java.util.List;

public class TreatmentController {

    private final TreatmentDAO treatmentDAO = new TreatmentDAO();

    public Treatment create(String name, Animal animal, Date start, Date end) {
        Treatment prod = new Treatment(name, animal, start, end);
        treatmentDAO.create(prod);
        return prod;
    }

    public void update(Treatment prod) {
        treatmentDAO.update(prod);
    }

    public void delete(Treatment prod) {
        treatmentDAO.delete(prod);
    }

    public List<Treatment> retrieveAll() {
        return treatmentDAO.retrieveAll();
    }

    public Treatment retrieveByName(String name) {
        return treatmentDAO.retrieveByName(name);
    }

    public List<Treatment> retrieveBySimilarName(String name) {
        return treatmentDAO.retrieveBySimilarName(name);
    }
}
