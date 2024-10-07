package com.example.vetclinic.model;

import java.util.Date;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private Date dateEntry;
    private Date dateExpiration;
    private int count;

    public Product() {
        this("", new Date(), new Date(), 0);
    }

    public Product(String name, Date dateEntry, Date dateExpiration, int count) {
        this(UUID.randomUUID(), name, dateEntry, dateExpiration, count);
    }

    public Product(UUID id, String name, Date dateEntry, Date dateExpiration, int count) {
        this.id = id;
        this.name = name;
        this.dateEntry = dateEntry;
        this.dateExpiration = dateExpiration;
        this.count = count;
    }

    public Product(Product p) {
        this(UUID.fromString(p.getId()), p.getName(), p.getDateEntry(), p.getDateExpiration(), p.getCount());
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

    public int getCount() {
        return count;
    }

}
