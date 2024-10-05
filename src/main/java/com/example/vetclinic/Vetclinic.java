package com.example.vetclinic;

import com.example.vetclinic.model.Animal;
import com.example.vetclinic.model.AnimalDAO;
import com.example.vetclinic.model.Client;
import com.example.vetclinic.model.ClientDAO;
import com.example.vetclinic.model.User;
import com.example.vetclinic.model.UserDAO;
import com.example.vetclinic.model.Vet;
import com.example.vetclinic.model.VetDAO;

import java.util.List;

public class Vetclinic {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        AnimalDAO animalDAO = new AnimalDAO();

        {
            for (int i = 0; i < 10; i++) {
                animalDAO.create(new Animal("pus-pus-" + i, 1, Animal.Sex.MALE, 1));
            }
        }

        List<Animal> animals = animalDAO.retrieveAll();
        for (Animal a : animals) {
            System.out.println(a);
            animalDAO.delete(a);
        }

        VetDAO vetDAO = new VetDAO();
        ClientDAO clientDAO = new ClientDAO();
        {
            Vet vet = new Vet("John", "Adalbert St.", "123-45", "225-555-999", "john@doe.com");
            Client cli = new Client("Marie", "Bert St.", "648-68", "999-555-111", "marie@example.com");
            vetDAO.create(vet);
            clientDAO.create(cli);
        }

        List<Vet> vets = vetDAO.retrieveAll();
        for (Vet v : vets) {
            System.out.println(v);
            vetDAO.delete(v);
        }

        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.retrieveAll();
        for (User u : users) {
            System.out.println(u);
        }

        List<Client> clients = clientDAO.retrieveAll();
        for (Client c : clients) {
            System.out.println(c);
            clientDAO.delete(c);
        }

    }
}
