package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class VetDAO implements DataAccess<Vet> {

    private static String tableName = "vet";
    private Database db;
    private UserDAO userDAO;

    public VetDAO() {
        db = Database.getInstance();
        userDAO = new UserDAO();
        createTable();
    }

    @Override
    public void create(Vet entity) {
        // create in parent table
        userDAO.create(entity);

        // create in child table
        String sql = "INSERT INTO " + tableName + "(id) VALUES (?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("VetDAO: " + e.getMessage());
        }
    }

    @Override
    public void update(Vet entity) {
        userDAO.update(entity);
    }

    @Override
    public void delete(Vet entity) {
        // delete from child table
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("VetDAO: " + e.getMessage());
        }

        // delete from parent table
        userDAO.delete(entity);
    }

    @Override
    public Vet retrieveById(String id) {
        String sql = "SELECT * FROM "
                + "(SELECT * FROM "
                + userDAO.getTableName() + " INNER JOIN "
                + tableName + " ON "
                + tableName + ".id = " + userDAO.getTableName() + ".id)\n"
                + "WHERE id=?";
        Vet vet = null;

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();

            if (result != null && result.next()) {
                vet = buildObject(result);
            }
        } catch (SQLException e) {
            System.out.println("VetDAO: " + e.getMessage());
        }

        return vet;
    }

    @Override
    public List<Vet> retrieveAll() {
        String sql = "SELECT * FROM "
                + userDAO.getTableName() + " INNER JOIN "
                + tableName + " ON "
                + tableName + ".id = " + userDAO.getTableName() + ".id";
        List<Vet> vetList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                vetList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("VetDAO: " + e.getMessage());
        }

        return vetList;
    }

    public List<Vet> retrieveBySimilarName(String search) {
        String sql = "SELECT * FROM "
                + userDAO.getTableName() + " INNER JOIN "
                + tableName + " ON "
                + tableName + ".id = " + userDAO.getTableName() + ".id\n"
                + "WHERE name LIKE '%" + search + "%'";
        List<Vet> vetList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                vetList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("ClientDAO: " + e.getMessage());
        }

        return vetList;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id TEXT PRIMARY KEY,\n"
                + "FOREIGN KEY(id) REFERENCES user(id))";
        db.executeUpdate(sql);
    }

    private Vet buildObject(ResultSet result) throws SQLException {
        return new Vet(
                UUID.fromString(result.getString("id")),
                result.getString("name"),
                result.getString("address"),
                result.getString("cep"),
                result.getString("tel"),
                result.getString("email")
        );
    }

}
