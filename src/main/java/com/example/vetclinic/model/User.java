package com.example.vetclinic.model;

import java.util.UUID;

public class User {

    protected final UUID id;
    protected final String name;
    protected final String address;
    protected final String cep;
    protected final String tel;
    protected final String email;

    public User(String name, String address, String cep, String tel, String email) {
        this(UUID.randomUUID(), name, address, cep, tel, email);
    }

    public User(UUID id, String name, String address, String cep, String tel, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cep = cep;
        this.tel = tel;
        this.email = email;
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCep() {
        return cep;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User(id=%s, name=%s, address=%s, cep=%s, tel=%s, email=%s"
                .formatted(id, name, address, cep, tel, email);
    }

}
