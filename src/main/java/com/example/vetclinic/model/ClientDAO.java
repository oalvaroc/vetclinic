package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClientDAO implements DataAccess<Client> {

    private static String tableName = "client";
    private Database db;
    private UserDAO userDAO;

    public ClientDAO() {
        db = Database.getInstance();
        userDAO = new UserDAO();
        createTable();
    }

    public String getTableName() {
        return tableName;
    }

    @Override
    public void create(Client entity) {
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
            System.err.println("ClientDAO: " + e.getMessage());
        }
    }

    @Override
    public void update(Client entity) {
        userDAO.update(entity);
    }

    @Override
    public void delete(Client entity) {
        // delete from child table
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("ClientDAO: " + e.getMessage());
        }

        // delete from parent table
        userDAO.delete(entity);
    }

    @Override
    public Client retrieveById(String id) {
        String sql = "SELECT * FROM "
                + "(SELECT * FROM "
                + userDAO.getTableName() + " INNER JOIN "
                + tableName + " ON "
                + tableName + ".id = " + userDAO.getTableName() + ".id)\n"
                + "WHERE id=?";
        Client client = null;

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();

            if (result == null || !result.next()) {
                return null;
            }
            client = buildObject(result);
        } catch (SQLException e) {
            System.out.println("ClientDAO: " + e.getMessage());
        }

        return client;
    }

    @Override
    public List<Client> retrieveAll() {
        String sql = "SELECT * FROM "
                + userDAO.getTableName() + " INNER JOIN "
                + tableName + " ON "
                + tableName + ".id = " + userDAO.getTableName() + ".id";
        List<Client> clientList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                clientList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("ClientDAO: " + e.getMessage());
        }

        return clientList;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id TEXT PRIMARY KEY,\n"
                + "FOREIGN KEY(id) REFERENCES user(id))";
        db.executeUpdate(sql);
    }

    private Client buildObject(ResultSet result) throws SQLException {
        return new Client(
                UUID.fromString(result.getString("id")),
                result.getString("name"),
                result.getString("address"),
                result.getString("cep"),
                result.getString("tel"),
                result.getString("email")
        );
    }

}
