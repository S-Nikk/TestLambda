package org.example.commons;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection Get_JDBC_Connection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/testdata?useSSL=false";
        String username = "root";
        String password = "swampfire$12";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
            System.out.println("Connection Successfully established");
            // Create a statement
            return connection;
        } catch (Exception e) {
            System.out.println("Check");
            e.printStackTrace();
        }
        return null;
    }
}
