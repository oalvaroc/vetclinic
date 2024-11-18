package com.example.vetclinic.model;

import java.util.UUID;

public class User {

    protected final UUID id;
    protected String name;
    protected String address;
    protected String cep;
    protected String tel;
    protected String email;

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

    public User(User user) {
        this.id = user.id;
        this.name = user.getName();
        this.address = user.getAddress();
        this.cep = user.getCep();
        this.tel = user.getTel();
        this.email = user.getEmail();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User(id=%s, name=%s, address=%s, cep=%s, tel=%s, email=%s"
                .formatted(id, name, address, cep, tel, email);
    }

}
