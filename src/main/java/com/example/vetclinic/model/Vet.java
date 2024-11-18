package com.example.vetclinic.model;

import java.util.UUID;

public class Vet extends User {

    public Vet(String name, String address, String cep, String tel, String email) {
        super(name, address, cep, tel, email);
    }

    public Vet(UUID id, String name, String address, String cep, String tel, String email) {
        super(id, name, address, cep, tel, email);
    }

    public Vet() {
        super("", "", "", "", "");
    }

    public Vet(Vet other) {
        this(UUID.fromString(other.getId()), other.getName(), other.getAddress(), other.getCep(), other.getTel(), other.getEmail());
    }

    @Override
    public String toString() {
        return "Vet(id=%s, name=%s, address=%s, cep=%s, tel=%s, email=%s"
                .formatted(id, name, address, cep, tel, email);
    }

}
