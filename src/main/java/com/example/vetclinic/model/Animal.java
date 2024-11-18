package com.example.vetclinic.model;

import java.util.UUID;

public class Animal {

    private final UUID id;
    private String name;
    private int age;
    private Sex sex;
    private double weight;
    private Client owner;

    public enum Sex {
        MALE, FEMALE;
    }

    public Animal(Client owner, String name, int age, Sex sex, double weight) {
        this(owner, UUID.randomUUID(), name, age, sex, weight);
    }

    public Animal(Client owner, UUID id, String name, int age, Sex sex, double weight) {
        this.owner = owner;
        this.id = id;
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
    }

    public Animal(Client owner) {
        this(owner, "", 0, Sex.MALE, 0);
    }

    public Animal(Animal animal) {
        this(animal.owner, animal.id, animal.name, animal.age, animal.sex, animal.weight);
    }

    public Client getOwner() {
        return owner;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Animal(id=%s, name=%s, age=%d, sex=%s, weight=%f, owner_id=%s)"
                .formatted(id, name, age, sex, weight, owner.getId());
    }

}
