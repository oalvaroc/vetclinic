package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AnimalDAO implements DataAccess<Animal> {

    private static String tableName = "animal";
    private Database db;

    public AnimalDAO() {
        db = Database.getInstance();
        createTable();
    }

    @Override
    public void create(Animal entity) {
        String sql = "INSERT INTO " + tableName
                + "(id, name, age, sex, weight) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setInt(3, entity.getAge());
            stmt.setInt(4, entity.getSex() == Animal.Sex.MALE ? 0 : 1);
            stmt.setDouble(5, entity.getWeight());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("AnimalDAO: " + e.getMessage());
        }
    }

    @Override
    public void update(Animal entity) {
        String sql = "UPDATE " + tableName + "\n"
                + "SET name=?, age=?, sex=?, weight=?\n"
                + "WHERE id=?";

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getName());
            stmt.setInt(2, entity.getAge());
            stmt.setInt(3, entity.getSex() == Animal.Sex.MALE ? 0 : 1);
            stmt.setDouble(4, entity.getWeight());
            stmt.setString(5, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("AnimalDAO: " + e.getMessage());
        }
    }

    @Override
    public void delete(Animal entity) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("AnimalDAO: " + e.getMessage());
        }
    }

    @Override
    public Animal retrieveById(String id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id=" + id;
        Animal animal = null;

        try {
            ResultSet result = db.executeQuery(sql);
            if (result == null || !result.first()) {
                return null;
            }
            animal = buildObject(result);
        } catch (SQLException e) {
            System.out.println("AnimalDAO: " + e.getMessage());
        }

        return animal;
    }

    @Override
    public List<Animal> retrieveAll() {
        String sql = "SELECT * FROM " + tableName;
        List<Animal> animalList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                animalList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("AnimalDAO: " + e.getMessage());
        }

        return animalList;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id TEXT PRIMARY KEY,\n"
                + "name TEXT,\n"
                + "age INTEGER,\n"
                + "sex INTEGER,\n"
                + "weight REAL)";
        db.executeUpdate(sql);
    }

    private Animal buildObject(ResultSet result) throws SQLException {
        return new Animal(
                UUID.fromString(result.getString("id")),
                result.getString("name"),
                result.getInt("age"),
                result.getInt("sex") == 0 ? Animal.Sex.MALE : Animal.Sex.FEMALE,
                result.getDouble("weight")
        );
    }
}
