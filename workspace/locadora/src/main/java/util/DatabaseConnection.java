package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/locadora";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1234";

    public static Connection getConnection() throws SQLException {
        // Carrega o driver explicitamente (opcional para Java 6+)
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver PostgreSQL não encontrado.", e);
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}