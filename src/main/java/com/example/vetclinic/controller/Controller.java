package com.example.vetclinic.controller;

import com.example.vetclinic.model.Client;
import com.example.vetclinic.model.Animal;
import com.example.vetclinic.model.Vet;

public class Controller {

    private static Controller instance;
    private final ClientController clientController;
    private final ProductController productController;
    private final AnimalController animalController;
    private final VetController vetController;
    private final AppointmentController appointmentController;
    private final TreatmentController treatmentController;
    private final ExamController examController;

    private Client currentClient;
    private Animal currentAnimal;
    private Vet currentVet;

    private Controller() {
        clientController = new ClientController();
        productController = new ProductController();
        animalController = new AnimalController();
        vetController = new VetController();
        appointmentController = new AppointmentController();
        treatmentController = new TreatmentController();
        examController = new ExamController();
    }

    public static Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public ProductController getProductController() {
        return productController;
    }

    public AnimalController getAnimalController() {
        return animalController;
    }

    public VetController getVetController() {
        return vetController;
    }

    public AppointmentController getAppointmentController() {
        return appointmentController;
    }

    public TreatmentController getTreatmentController() {
        return treatmentController;
    }

    public ExamController getExamController() {
        return examController;
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void setCurrentClient(Client currentClient) {
        this.currentClient = currentClient;
    }

    public Animal getCurrentAnimal() {
        return currentAnimal;
    }

    public void setCurrentAnimal(Animal currentAnimal) {
        this.currentAnimal = currentAnimal;
    }

    public Vet getCurrentVet() {
        return currentVet;
    }

    public void setCurrentVet(Vet currentVet) {
        this.currentVet = currentVet;
        System.out.println(">> CURRENT VET: " + currentVet);
    }

}
