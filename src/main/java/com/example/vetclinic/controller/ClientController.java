package com.example.vetclinic.controller;

import com.example.vetclinic.model.ClientDAO;
import com.example.vetclinic.model.Client;
import java.util.List;

public class ClientController {

    private final ClientDAO clientDAO = new ClientDAO();

    public Client create() {
        Client client = new Client();
        clientDAO.create(client);
        return client;
    }

    public void update(Client client) {
        clientDAO.update(client);
    }

    public void delete(Client client) {
        clientDAO.delete(client);
    }

    public List<Client> retrieveAll() {
        return clientDAO.retrieveAll();
    }

    public List<Client> retrieveBySimilarName(String search) {
        return clientDAO.retrieveBySimilarName(search);
    }

    public Client retrieveByName(String name) {
        return clientDAO.retrieveByName(name);
    }

}
