package com.example.vetclinic.model;

import java.util.UUID;

public class Client extends User {

    public Client(String name, String address, String cep, String tel, String email) {
        super(name, address, cep, tel, email);
    }

    public Client(UUID id, String name, String address, String cep, String tel, String email) {
        super(id, name, address, cep, tel, email);
    }

    @Override
    public String toString() {
        return "Client(id=%s, name=%s, address=%s, cep=%s, tel=%s, email=%s"
                .formatted(id, name, address, cep, tel, email);
    }

}
