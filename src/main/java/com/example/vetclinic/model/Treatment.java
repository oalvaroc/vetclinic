package com.example.vetclinic.model;

import java.util.Date;

public class Treatment {
    private Date startDate;
    private Date endDate;
    
    public Treatment(Date start, Date end) {
        this.startDate = start;
        this.endDate = end;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

}
