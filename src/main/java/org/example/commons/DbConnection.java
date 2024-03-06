package org.example.commons;
import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    public static Connection getJdbcConnection() {
        String dbUrl = "jdbc:mysql://localhost:3306/testdata?useSSL=false";
        String userName = "root";
        String userPswrd = "swampfire$12";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection CONNECTION = DriverManager.getConnection(dbUrl, userName,userPswrd);
            System.out.println("Connection Successfully established");
            // Create a statement
            return CONNECTION;
        } catch (Exception e) {
            System.out.println("Check");
            e.printStackTrace();
        }
        return null;
    }
}
