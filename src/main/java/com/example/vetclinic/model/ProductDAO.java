package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProductDAO implements DataAccess<Product> {

    private static String tableName = "product";
    private Database db;

    public ProductDAO() {
        db = Database.getInstance();
        createTable();
    }

    @Override
    public void create(Product entity) {
        String sql = "INSERT INTO " + tableName
                + "(id, name, date_entry, date_expiration, count) "
                + "VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());
            stmt.setString(2, entity.getName());
            stmt.setLong(3, entity.getDateEntry().getTime());
            stmt.setLong(4, entity.getDateExpiration().getTime());
            stmt.setInt(5, entity.getCount());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("ProductDAO: " + e.getMessage());
        }
    }

    @Override
    public void update(Product entity) {
        String sql = "UPDATE " + tableName + "\n"
                + "SET name=?, date_entry=?, date_expiration=?, count=?\n"
                + "WHERE id=?";

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getName());
            stmt.setLong(2, entity.getDateEntry().getTime());
            stmt.setLong(3, entity.getDateExpiration().getTime());
            stmt.setInt(4, entity.getCount());
            stmt.setString(5, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("ProductDAO: " + e.getMessage());
        }
    }

    @Override
    public void delete(Product entity) {
        String sql = "DELETE FROM " + tableName + " WHERE id=?";
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, entity.getId());

            db.executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("ProductDAO: " + e.getMessage());
        }
    }

    @Override
    public Product retrieveById(String id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id=?";
        Product product = null;

        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            ResultSet result = stmt.executeQuery();

            if (result == null || !result.first()) {
                return null;
            }
            product = buildObject(result);
        } catch (SQLException e) {
            System.out.println("ProductDAO: " + e.getMessage());
        }

        return product;
    }

    @Override
    public List<Product> retrieveAll() {
        String sql = "SELECT * FROM " + tableName;
        List<Product> productList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                productList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("ProductDAO: " + e.getMessage());
        }

        return productList;
    }

    public List<Product> retrieveBySimilarName(String search) {
        String sql = "SELECT * FROM " + tableName + "\n"
                + "WHERE name LIKE '%" + search + "%'";
        List<Product> productList = new ArrayList<>();

        try {
            ResultSet result = db.executeQuery(sql);
            while (result != null && result.next()) {
                productList.add(buildObject(result));
            }
        } catch (SQLException e) {
            System.out.println("ProductDAO: " + e.getMessage());
        }

        return productList;
    }

    private void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + tableName + "(\n"
                + "id TEXT PRIMARY KEY,\n"
                + "name TEXT,\n"
                + "date_entry INTEGER,\n"
                + "date_expiration INTEGER,\n"
                + "count INTEGER)";
        db.executeUpdate(sql);
    }

    private Product buildObject(ResultSet result) throws SQLException {
        return new Product(
                UUID.fromString(result.getString("id")),
                result.getString("name"),
                new Date(result.getLong("date_entry")),
                new Date(result.getLong("date_expiration")),
                result.getInt("count")
        );
    }

}
