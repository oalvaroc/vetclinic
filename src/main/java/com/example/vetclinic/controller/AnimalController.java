package com.example.vetclinic.controller;

import com.example.vetclinic.model.AnimalDAO;
import com.example.vetclinic.model.Animal;
import com.example.vetclinic.model.Client;
import java.util.List;

public class AnimalController {

    private final AnimalDAO animalDAO = new AnimalDAO();

    public Animal create(Client owner) {
        Animal animal = new Animal(owner);
        animalDAO.create(animal);
        return animal;
    }

    public synchronized void update(Animal animal) {
        animalDAO.update(animal);
    }

    public synchronized void delete(Animal animal) {
        animalDAO.delete(animal);
    }

    public List<Animal> retrieveAll() {
        return animalDAO.retrieveAll();
    }

    public List<Animal> retrieveAllFromOwner(Client client) {
        return animalDAO.retrieveAllFromOwner(client);
    }

    public List<Animal> retrieveBySimilarName(String search) {
        return animalDAO.retrieveBySimilarName(search);
    }

    public Animal retrieveByName(String name) {
        return animalDAO.retrieveByName(name);
    }

}
