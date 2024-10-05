package com.example.vetclinic.model;

import java.util.Date;
import java.util.UUID;

public class Treatment {

    private UUID id;
    private Date dateStart;
    private Date dateEnd;

    public Treatment(Date start, Date end) {
        this(UUID.randomUUID(), start, end);
    }

    public Treatment(UUID id, Date start, Date end) {
        this.id = id;
        this.dateStart = start;
        this.dateEnd = end;
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
