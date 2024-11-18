package com.example.vetclinic.controller;

import com.example.vetclinic.model.Appointment;
import com.example.vetclinic.model.AppointmentDAO;
import com.example.vetclinic.model.Treatment;
import com.example.vetclinic.model.Vet;
import java.util.Date;
import java.util.List;

public class AppointmentController {

    private final AppointmentDAO appointmentDAO = new AppointmentDAO();

    public Appointment create(Date date, String report, Vet vet, Treatment treatment) {
        Appointment prod = new Appointment(date, report, vet, treatment);
        appointmentDAO.create(prod);
        return prod;
    }

    public void update(Appointment prod) {
        appointmentDAO.update(prod);
    }

    public void delete(Appointment prod) {
        appointmentDAO.delete(prod);
    }

    public List<Appointment> retrieveAll() {
        return appointmentDAO.retrieveAll();
    }

    public List<Appointment> retrieveByTreatment(Treatment t) {
        return appointmentDAO.retrieveByTreatment(t);
    }

}
