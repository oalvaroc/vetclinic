package com.example.vetclinic.controller;

import com.example.vetclinic.model.Appointment;
import com.example.vetclinic.model.Exam;
import com.example.vetclinic.model.ExamDAO;
import java.util.List;

public class ExamController {

    private final ExamDAO examDAO = new ExamDAO();

    public Exam create(String name, Appointment appointment) {
        Exam prod = new Exam(name, appointment);
        examDAO.create(prod);
        return prod;
    }

    public Exam create(Appointment a) {
        return create("", a);
    }

    public void update(Exam prod) {
        examDAO.update(prod);
    }

    public void delete(Exam prod) {
        examDAO.delete(prod);
    }

    public List<Exam> retrieveAll() {
        return examDAO.retrieveAll();
    }

    public List<Exam> retrieveByAppointment(Appointment a) {
        return examDAO.retrieveByAppointment(a);
    }

}
