package com.example.vetclinic.model;

import java.util.UUID;

public class Animal {

    private UUID id;
    private String name;
    private int age;
    private Sex sex;
    private double weight;

    public enum Sex {
        MALE, FEMALE;
    }

    public Animal(String name, int age, Sex sex, double weight) {
        this(UUID.randomUUID(), name, age, sex, weight);
    }

    public Animal(UUID id, String name, int age, Sex sex, double weight) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Sex getSex() {
        return sex;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Animal(id=%s, name=%s, age=%d, sex=%s, weight=%f)"
                .formatted(id, name, age, sex, weight);
    }

}
