package com.example.vetclinic.model;

import java.util.Date;
import java.util.UUID;

public class Treatment {

    private UUID id;
    private Date dateStart;
    private Date dateEnd;
    private Animal animal;

    public Treatment(Animal animal, Date start, Date end) {
        this(animal, UUID.randomUUID(), start, end);
    }

    public Treatment(Animal animal, UUID id, Date start, Date end) {
        this.animal = animal;
        this.id = id;
        this.dateStart = start;
        this.dateEnd = end;
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

}
