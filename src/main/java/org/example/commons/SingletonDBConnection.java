package org.example.commons;
import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonDBConnection {

    private static SingletonDBConnection instance; // Singleton instance
    private Connection connection;

    private SingletonDBConnection() {
        initializeConnection();
    }

    public static SingletonDBConnection getInstance() {
        if (instance == null) {
            instance = new SingletonDBConnection();
        }
        return instance;
    }

    private void initializeConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/testdata?useSSL=false";
        String username = "root";
        String password = "swampfire$12";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection Successfully established");
        } catch (Exception e) {
            System.out.println("Error establishing the connection: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
