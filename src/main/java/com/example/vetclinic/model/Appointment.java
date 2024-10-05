package com.example.vetclinic.model;

import java.util.Date;
import java.util.UUID;

public class Appointment {

    private UUID id;
    private Date date;
    private String report;

    public Appointment(Date date, String report) {
        this(UUID.randomUUID(), date, report);
    }

    public Appointment(UUID id, Date date, String report) {
        this.id = id;
        this.date = date;
        this.report = report;
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

}
