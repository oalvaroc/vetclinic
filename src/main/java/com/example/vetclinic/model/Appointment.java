package com.example.vetclinic.model;

import java.util.Date;
import java.util.UUID;

public class Appointment {

    private UUID id;
    private Date date;
    private String report;
    private Treatment treatment;
    private Vet vet;

    public Appointment(Date date, String report, Vet vet, Treatment treatment) {
        this(UUID.randomUUID(), date, report, vet, treatment);
    }

    public Appointment(UUID id, Date date, String report, Vet vet, Treatment treatment) {
        this.id = id;
        this.date = date;
        this.report = report;
        this.treatment = treatment;
        this.vet = vet;
    }

    public String getId() {
        return id.toString();
    }

    public Date getDate() {
        return date;
    }

    public String getReport() {
        return report;
    }

    public Treatment getTreatment() {
        return treatment;
    }

    public Vet getVet() {
        return vet;
    }
}
