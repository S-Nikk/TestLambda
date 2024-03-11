package org.example;

import java.sql.SQLException;
import java.util.Scanner;
import org.example.commons.TestDaoSingletonClass;
public class MenuDrivenApp {
    public static void main(String[] args) throws SQLException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        TestDaoSingletonClass dao = new TestDaoSingletonClass(); // Initialize your DAO class here

        System.out.println(":: Welcome to Menu-Driven Application ::");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Insert a record");
            System.out.println("2. View all records");
            System.out.println("3. Update a record");
            System.out.println("4. Close Connection");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter email: ");
                    String email = scanner.next();
                    try {
                        dao.insertRecord(name, email);
                        System.out.println("Record inserted successfully!");
                    } catch (SQLException e) {
                        System.err.println("Error inserting record: " + e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        dao.getAllRecords();
                    } catch (SQLException e) {
                        System.err.println("Error fetching records: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter user ID: ");
                    int userId = scanner.nextInt();
                    System.out.print("Enter new name: ");
                    String newName = scanner.next();
                    try {
                        dao.updateRecord(userId, newName);
                        System.out.println("Record updated successfully!");
                    } catch (SQLException e) {
                        System.err.println("Error updating record: " + e.getMessage());
                    }
                    break;
                case 4:
                    TestDaoSingletonClass.CONNECTION.close();
                    default:
                    System.out.println("Invalid choice. Please select a valid option.");
                case 5:
                    System.out.println("Exiting the application. Goodbye!");
                    System.exit(0);
            }
            Thread.sleep(30000);
        }
    }
}