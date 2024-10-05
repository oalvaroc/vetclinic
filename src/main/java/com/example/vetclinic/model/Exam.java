package com.example.vetclinic.model;

import java.util.UUID;

public class Exam {

    private UUID id;
    private String name;
    private String result;

    public Exam(String name, String result) {
        this(UUID.randomUUID(), name, result);
    }

    public Exam(UUID id, String name, String result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

}
