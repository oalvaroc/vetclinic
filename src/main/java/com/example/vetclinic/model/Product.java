package com.example.vetclinic.model;

import java.util.Date;

public class Product {
    private int id;
    private String name;
    private Date dateEntry;
    private Date dateExpiration;
    private Date dateDeparture;

    public Product(int id, String name, Date dateEntry, Date dateExpiration, Date dateDeparture) {
        this.id = id;
        this.name = name;
        this.dateEntry = dateEntry;
        this.dateExpiration = dateExpiration;
        this.dateDeparture = dateDeparture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getDateEntry() {
        return dateEntry;
    }

    public Date getDateExpiration() {
        return dateExpiration;
    }

    public Date getDateDeparture() {
        return dateDeparture;
    }
    
}
