package com.example.vetclinic.model;

public class Exam {
    private String name;
    private String result;

    public Exam(String name, String result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

}
