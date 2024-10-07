package com.example.vetclinic.model;

import java.util.UUID;

public class Exam {

    private UUID id;
    private String name;
    private String result;
    private Appointment appointment;

    public Exam(String name, String result, Appointment appointment) {
        this(UUID.randomUUID(), name, result, appointment);
    }

    public Exam(String name, String result) {
        this(UUID.randomUUID(), name, result, null);
    }

    public Exam(UUID id, String name, String result, Appointment appointment) {
        this.id = id;
        this.name = name;
        this.result = result;
        this.appointment = appointment;
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

    public Appointment getAppointment() {
        return appointment;
    }

}
