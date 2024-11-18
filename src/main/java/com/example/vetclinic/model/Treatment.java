package com.example.vetclinic.model;

import java.util.Date;
import java.util.UUID;

public class Treatment {

    private UUID id;
    private String name;
    private Date dateStart;
    private Date dateEnd;
    private Animal animal;

    public Treatment(String name, Animal animal, Date start, Date end) {
        this(UUID.randomUUID(), name, animal, start, end);
    }

    public Treatment(UUID id, String name, Animal animal, Date start, Date end) {
        this.id = id;
        this.name = name;
        this.animal = animal;
        this.dateStart = start;
        this.dateEnd = end;
    }

    public Treatment(Treatment other) {
        this(UUID.fromString(other.getId()), other.getName(), other.getAnimal(), other.getDateStart(), other.getDateEnd());
    }

    public String getName() {
        return name;
    }

    public Animal getAnimal() {
        return animal;
    }

    public String getId() {
        return id.toString();
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

}
