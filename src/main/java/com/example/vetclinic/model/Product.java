package com.example.vetclinic.model;

import java.util.Date;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private Date dateEntry;
    private Date dateExpiration;
    private Date dateDeparture;

    public Product(String name, Date dateEntry, Date dateExpiration, Date dateDeparture) {
        this(UUID.randomUUID(), name, dateEntry, dateExpiration, dateDeparture);
    }

    public Product(UUID id, String name, Date dateEntry, Date dateExpiration, Date dateDeparture) {
        this.id = id;
        this.name = name;
        this.dateEntry = dateEntry;
        this.dateExpiration = dateExpiration;
        this.dateDeparture = dateDeparture;
    }

    public String getId() {
        return id.toString();
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
