package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO implements DataAccess<User> {

    private static String tableName = "user";
    private Database db;

    public UserDAO() {
        db = Database.getInstance();
        createTable();
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public void create(User entity) {
        String sql = "INSERT INTO " + tableName
                + "(id, name, address, cep, tel, email) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setString(3, entity.getAddress());
            stmt.setString(4, entity.getCep());
            stmt.setString(5, entity.getTel());
            stmt.setString(6, entity.getEmail());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("UserDAO: " + e.getMessage());
        }
    }

    @Override
    public void update(User entity) {
        String sql = "UPDATE " + tableName + "\n"
                + "SET name=?, address=?, cep=?, tel=?, email=?\n"
                + "WHERE id=?";

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getName());
            stmt.setString(2, entity.getAddress());
            stmt.setString(3, entity.getCep());
            stmt.setString(4, entity.getTel());
            stmt.setString(5, entity.getEmail());
            stmt.setString(6, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("UserDAO: " + e.getMessage());
        }
    }

    @Override
    public void delete(User entity) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("UserDAO: " + e.getMessage());
        }
    }

    @Override
    public User retrieveById(String id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id=?";
        User animal = null;

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();

            if (result == null || !result.first()) {
                return null;
            }
            animal = buildObject(result);
        } catch (SQLException e) {
            System.out.println("UserDAO: " + e.getMessage());
        }

        return animal;
    }

    @Override
    public List<User> retrieveAll() {
        String sql = "SELECT * FROM " + tableName;
        List<User> animalList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                animalList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("UserDAO: " + e.getMessage());
        }

        return animalList;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id TEXT PRIMARY KEY,\n"
                + "name TEXT,\n"
                + "address TEXT,\n"
                + "cep TEXT,\n"
                + "tel TEXT,\n"
                + "email TEXT)";
        db.executeUpdate(sql);
    }

    private User buildObject(ResultSet result) throws SQLException {
        return new User(
                UUID.fromString(result.getString("id")),
                result.getString("name"),
                result.getString("address"),
                result.getString("cep"),
                result.getString("tel"),
                result.getString("email")
        );
    }

}
