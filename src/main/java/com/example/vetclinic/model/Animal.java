package com.example.vetclinic.model;

public class Animal {
    private String name;
    private int age;
    private AnimalSex sex;
    private double weight;

    public enum AnimalSex {
        MALE, FEMALE;
    }

    public Animal(String name, int age, AnimalSex sex, double weight) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public AnimalSex getSex() {
        return sex;
    }

    public double getWeight() {
        return weight;
    }
    
    @Override
    public String toString() {
        return "Animal(name=%s, age=%d, sex=%s, weight=%f)"
                .formatted(name, age, sex, weight);
    }

}
