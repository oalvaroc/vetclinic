package com.example.vetclinic;

import com.example.vetclinic.controller.Controller;
import com.example.vetclinic.model.Animal;
import com.example.vetclinic.model.AnimalDAO;
import com.example.vetclinic.model.Client;
import com.example.vetclinic.model.ClientDAO;
import com.example.vetclinic.model.User;
import com.example.vetclinic.model.UserDAO;
import com.example.vetclinic.view.MainForm;

import java.util.List;

public class VetClinic {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        ClientDAO clientDAO = new ClientDAO();

        Client cli = new Client("Marie", "Bert St.", "648-68", "999-555-111", "marie@example.com");
        clientDAO.create(cli);

        UserDAO userDAO = new UserDAO();
        List<User> users = userDAO.retrieveAll();
        for (User u : users) {
            System.out.println(u);
        }

        List<Client> clients = clientDAO.retrieveAll();
        for (Client c : clients) {
            System.out.println(c);
        }

        AnimalDAO animalDAO = new AnimalDAO();

        {
            System.out.println("> Creating animals...");
            for (int i = 0; i < 1; i++) {
                animalDAO.create(new Animal(cli, "pus-pus-" + i, 1, Animal.Sex.MALE, 1));
            }
            System.out.println("> DONE!");
        }

        List<Animal> animals = animalDAO.retrieveAll();
        for (Animal a : animals) {
            System.out.println(a);
            animalDAO.delete(a);
        }

        MainForm.launch(new Controller());
    }
}
