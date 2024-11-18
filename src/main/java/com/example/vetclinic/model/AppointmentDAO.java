package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class AppointmentDAO implements DataAccess<Appointment> {

    private static String tableName = "appointment";
    private Database db;
    private TreatmentDAO treatmentDAO;
    private VetDAO vetDAO;

    public AppointmentDAO() {
        db = Database.getInstance();
        treatmentDAO = new TreatmentDAO();
        vetDAO = new VetDAO();
        createTable();
    }

    @Override
    public void create(Appointment entity) {
        String sql = "INSERT INTO " + tableName
                + "(id, date, report, treatment_id, vet_id) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());
            stmt.setLong(2, entity.getDate().getTime());
            stmt.setString(3, entity.getReport());

            Treatment t = entity.getTreatment();
            stmt.setString(4, t == null ? null : t.getId());

            stmt.setString(5, entity.getVet().getId());
            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("AppointmentDAO: " + e.getMessage());
        }
    }

    @Override
    public void update(Appointment entity) {
        String sql = "UPDATE " + tableName + "\n"
                + "SET date=?, report=?\n"
                + "WHERE id=?";

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, entity.getDate().getTime());
            stmt.setString(2, entity.getReport());
            stmt.setString(3, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("AppointmentDAO: " + e.getMessage());
        }
    }

    @Override
    public void delete(Appointment entity) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("AppointmentDAO: " + e.getMessage());
        }
    }

    @Override
    public Appointment retrieveById(String id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id=?";
        Appointment appointment = null;

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();

            if (result == null || !result.first()) {
                return null;
            }
            appointment = buildObject(result);
        } catch (SQLException e) {
            System.out.println("AppointmentDAO: " + e.getMessage());
        }

        return appointment;
    }

    @Override
    public List<Appointment> retrieveAll() {
        String sql = "SELECT * FROM " + tableName;
        List<Appointment> appointmentList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                appointmentList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("AppointmentDAO: " + e.getMessage());
        }

        return appointmentList;
    }

    public List<Appointment> retrieveByTreatment(Treatment t) {
        String sql = "SELECT * FROM " + tableName + "\n"
                + "INNER JOIN " + treatmentDAO.getTableName() + "\n"
                + "ON " + tableName + ".treatment_id=" + treatmentDAO.getTableName() + ".id\n"
                + "WHERE " + treatmentDAO.getTableName() + ".id='" + t.getId() + "'";
        List<Appointment> appointmentList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                appointmentList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("AppointmentDAO: " + e.getMessage());
        }

        return appointmentList;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id TEXT PRIMARY KEY,\n"
                + "date INTEGER,\n"
                + "report TEXT,\n"
                + "treatment_id TEXT,\n"
                + "vet_id TEXT,\n"
                + "FOREIGN KEY(treatment_id) REFERENCES treatment(id),\n"
                + "FOREIGN KEY(vet_id) REFERENCES vet(id))";
        db.executeUpdate(sql);
    }

    private Appointment buildObject(ResultSet result) throws SQLException {
        String uuid = result.getString("id");
        Date date = new Date(result.getLong("date"));
        String report = result.getString("report");
        Treatment treatment = treatmentDAO.retrieveById(result.getString("treatment_id"));
        Vet vet = vetDAO.retrieveById(result.getString("vet_id"));

        return new Appointment(
                UUID.fromString(uuid),
                date,
                report,
                vet,
                treatment
        );
    }

}
