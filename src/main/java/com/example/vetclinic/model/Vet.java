package com.example.vetclinic.model;

public class Vet extends User {

    public Vet(int id, String name, String address, String cep, String tel, String email) {
        super(id, name, address, cep, tel, email);
    }
    
    @Override
    public String toString() {
        return "Vet(id=%d, name=%s, address=%s, cep=%s, tel=%s, email=%s"
                .formatted(id, name, address, cep, tel, email);
    }
    
}
