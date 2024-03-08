package org.example.commons;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingletonDBConnection {

    private static SingletonDBConnection instance; // Singleton instance
    private Connection CONNECTION=null;

    private SingletonDBConnection() {}

    public static SingletonDBConnection getInstance() {
        if (instance == null) {
            instance = new SingletonDBConnection();
        }
        return instance;
    }

    private void initializeConnection() {
        String dbUrl = "jdbc:mysql://localhost:3306/testdata?useSSL=false";
        String userName = "root";
        String userPswrd = "swampfire$12";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            CONNECTION = DriverManager.getConnection(dbUrl, userName, userPswrd);
            System.out.println("Connection Successfully established");
        } catch (Exception e) {
            System.out.println("Error establishing the connection: " + e.getMessage());
        }
    }
    public Connection getConnection() throws SQLException {
        if(CONNECTION==null){
            initializeConnection();
        }
        if (CONNECTION.isValid(5)){
            initializeConnection();
        }
        return CONNECTION;
    }

}
