package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass{
    private static final String URL = "jdbc:mysql://localhost:3306/login_Schema";
    private static final String USER = "user";
    private static final String PASSWORD = "password";

    private static Connection connection = null;
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database Connected Successfully");
            } catch (SQLException e) {
                System.out.println("Database Connection Failed: " + e.getMessage());
            }
        }
        return connection;
    }
}
