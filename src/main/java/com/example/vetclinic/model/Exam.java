package com.example.vetclinic.model;

import java.util.UUID;

public class Exam {

    private UUID id;
    private String name;
    private Appointment appointment;

    public Exam(String name, Appointment appointment) {
        this(UUID.randomUUID(), name, appointment);
    }

    public Exam(UUID id, String name, Appointment appointment) {
        this.id = id;
        this.name = name;
        this.appointment = appointment;
    }

    public String getId() {
        return id.toString();
    }

    public String getName() {
        return name;
    }

    public Appointment getAppointment() {
        return appointment;
    }

}
