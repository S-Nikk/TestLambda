package org.example.commons;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TestDaoSingletonClass {
    public static Connection CONNECTION;
    public TestDaoSingletonClass() throws SQLException {
        // Get the singleton connection instance
        CONNECTION = SingletonDBConnection.getInstance().getConnection();
    }

    public void insertRecord(String name, String email) throws SQLException {
        if (!CONNECTION.isValid(0)){
            new TestDaoSingletonClass();
        }
        String insertQuery = "INSERT INTO user (name, email) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        }
    }

    // Retrieve records
    public void getAllRecords() throws SQLException {
        String selectQuery = "SELECT * FROM user";
        if (!CONNECTION.isValid(0)){
            new TestDaoSingletonClass();
        }
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(selectQuery);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.println("User ID: " + id + ", Name: " + name + ", Email: " + email);
            }
        }
    }

    // Update a record
    public void updateRecord(int userId, String newName) throws SQLException {
        if (!CONNECTION.isValid(0)){
            new TestDaoSingletonClass();
        }
        String updateQuery = "UPDATE user SET name = ? WHERE username = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, newName);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        }
    }

    // Delete a record
    public void deleteRecord(int userId) throws SQLException {
        if (!CONNECTION.isValid(0)){
            new TestDaoSingletonClass();
        }
        String deleteQuery = "DELETE FROM user WHERE id = ?";
        try (PreparedStatement preparedStatement = CONNECTION.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        }
    }
}



