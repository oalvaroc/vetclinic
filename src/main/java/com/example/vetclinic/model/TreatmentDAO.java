package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TreatmentDAO implements DataAccess<Treatment> {

    private static String tableName = "treatment";
    private Database db;
    private AnimalDAO animalDAO;

    public TreatmentDAO() {
        db = Database.getInstance();
        animalDAO = new AnimalDAO();
        createTable();
    }

    @Override
    public void create(Treatment entity) {
        String sql = "INSERT INTO " + tableName
                + "(id, date_start, date_end, animal_id) "
                + "VALUES (?, ?, ?, ?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());
            stmt.setLong(2, entity.getDateStart().getTime());
            stmt.setLong(3, entity.getDateEnd().getTime());
            stmt.setString(4, entity.getAnimal().getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("TreatmentDAO: " + e.getMessage());
        }
    }

    @Override
    public void update(Treatment entity) {
        String sql = "UPDATE " + tableName + "\n"
                + "SET date_start=?, date_end=?\n"
                + "WHERE id=?";

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setLong(1, entity.getDateStart().getTime());
            stmt.setLong(2, entity.getDateEnd().getTime());
            stmt.setString(3, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("TreatmentDAO: " + e.getMessage());
        }
    }

    @Override
    public void delete(Treatment entity) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("TreatmentDAO: " + e.getMessage());
        }
    }

    @Override
    public Treatment retrieveById(String id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id=?";
        Treatment treatment = null;

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();

            if (result == null || !result.first()) {
                return null;
            }
            treatment = buildObject(result);
        } catch (SQLException e) {
            System.out.println("TreatmentDAO: " + e.getMessage());
        }

        return treatment;
    }

    @Override
    public List<Treatment> retrieveAll() {
        String sql = "SELECT * FROM " + tableName;
        List<Treatment> treatmentList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                treatmentList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("TreatmentDAO: " + e.getMessage());
        }

        return treatmentList;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id TEXT PRIMARY KEY,\n"
                + "date_start INTEGER,\n"
                + "date_end INTEGER,\n"
                + "animal_id TEXT,\n"
                + "FOREIGN KEY(animal_id) REFERENCES animal(id))";
        db.executeUpdate(sql);
    }

    private Treatment buildObject(ResultSet result) throws SQLException {
        Animal animal = animalDAO.retrieveById(result.getString("animal_id"));

        return new Treatment(
                animal,
                UUID.fromString(result.getString("id")),
                new Date(result.getLong("date_start")),
                new Date(result.getLong("date_end"))
        );
    }

}
