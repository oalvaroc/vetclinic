package com.example.vetclinic.model;

public class User {
    protected final int id;
    protected final String name;
    protected final String address;
    protected final String cep;
    protected final String tel;
    protected final String email;

    public User(int id, String name, String address, String cep, String tel, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.cep = cep;
        this.tel = tel;
        this.email = email;
    }

    public int getId() {
        return id;
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
        return "User(id=%d, name=%s, address=%s, cep=%s, tel=%s, email=%s"
                .formatted(id, name, address, cep, tel, email);
    }

}
