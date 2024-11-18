package com.example.vetclinic.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.sqlite.SQLiteConfig;

public final class Database {

    private static final String URL = "jdbc:sqlite:./vetclinic.db";
    private static Database instance;
    private Connection connection;

    private Database() {
        connection = getConnection();
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try {
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);

            connection = DriverManager.getConnection(URL, config.toProperties());
        } catch (SQLException e) {
            System.err.println("SQL: " + e.getMessage());
        }

        return connection;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("SQL: " + e.getMessage());
        }
    }

    public void executeUpdate(PreparedStatement stmt) throws SQLException {
        System.out.println(("SQL: " + stmt).replace('\n', ' '));
        stmt.executeUpdate();
    }

    public void executeUpdate(String sql) {
        Connection conn = getConnection();
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            executeUpdate(stmt);
        } catch (SQLException e) {
            System.err.println("SQL: " + e.getMessage());
        }
    }

    public ResultSet executeQuery(String sql) {
        Connection conn = getConnection();
        ResultSet result = null;

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            System.out.println(("SQL: " + stmt).replace('\n', ' '));
            result = stmt.executeQuery();
        } catch (SQLException e) {
            System.err.println("SQL: " + e.getMessage());
        }

        return result;
    }
}
