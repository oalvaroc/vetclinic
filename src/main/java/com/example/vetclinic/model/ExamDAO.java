package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExamDAO implements DataAccess<Exam> {

    private static String tableName = "exam";
    private Database db;
    private AppointmentDAO appointmentDAO;

    public ExamDAO() {
        db = Database.getInstance();
        appointmentDAO = new AppointmentDAO();
        createTable();
    }

    @Override
    public void create(Exam entity) {
        String sql = "INSERT INTO " + tableName
                + "(id, name, appointment_id) VALUES (?, ?, ?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getAppointment().getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("ExamDAO: " + e.getMessage());
        }
    }

    @Override
    public void update(Exam entity) {
        String sql = "UPDATE " + tableName + "\n"
                + "SET name=?\n"
                + "WHERE id=?";

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("ExamDAO: " + e.getMessage());
        }
    }

    @Override
    public void delete(Exam entity) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("ExamDAO: " + e.getMessage());
        }
    }

    @Override
    public Exam retrieveById(String id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id=?";
        Exam exam = null;

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();

            if (result == null || !result.first()) {
                return null;
            }
            exam = buildObject(result);
        } catch (SQLException e) {
            System.out.println("ExamDAO: " + e.getMessage());
        }

        return exam;
    }

    @Override
    public List<Exam> retrieveAll() {
        String sql = "SELECT * FROM " + tableName;
        List<Exam> examList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                examList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("ExamDAO: " + e.getMessage());
        }

        return examList;
    }

    public List<Exam> retrieveByAppointment(Appointment a) {
        String sql = "SELECT * FROM " + tableName + "\n"
                + "WHERE appointment_id='" + a.getId() + "'";
        List<Exam> examList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                examList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("ExamDAO: " + e.getMessage());
        }

        return examList;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id TEXT PRIMARY KEY,\n"
                + "name TEXT,\n"
                + "appointment_id TEXT,\n"
                + "FOREIGN KEY(appointment_id) REFERENCES appointment(id))";
        db.executeUpdate(sql);
    }

    private Exam buildObject(ResultSet result) throws SQLException {
        Appointment appointment = appointmentDAO.retrieveById(result.getString("appointment_id"));

        return new Exam(
                UUID.fromString(result.getString("id")),
                result.getString("name"),
                appointment
        );
    }

}
