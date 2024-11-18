package com.example.vetclinic.controller;

import com.example.vetclinic.model.Vet;
import com.example.vetclinic.model.VetDAO;
import java.util.List;

public class VetController {

    private final VetDAO vetDAO = new VetDAO();

    public Vet create() {
        Vet vet = new Vet();
        vetDAO.create(vet);
        return vet;
    }

    public void update(Vet prod) {
        vetDAO.update(prod);
    }

    public void delete(Vet prod) {
        vetDAO.delete(prod);
    }

    public List<Vet> retrieveAll() {
        return vetDAO.retrieveAll();
    }

    public List<Vet> retrieveBySimilarName(String search) {
        return vetDAO.retrieveBySimilarName(search);
    }

}
