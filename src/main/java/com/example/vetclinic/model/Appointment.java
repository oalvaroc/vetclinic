package com.example.vetclinic.model;

import java.util.Date;

public class Appointment {
    private Date date;
    private String report;

    public Appointment(Date date, String report) {
        this.date = date;
        this.report = report;
    }

    public Date getDate() {
        return date;
    }

    public String getReport() {
        return report;
    }

}
