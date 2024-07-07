package com.bloodbank;

import java.sql.*;
import java.util.Scanner;

public class BloodBank {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/blood_bank_management";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "root";
    private static Connection conn = null;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            boolean exit = false;
            while (!exit) {
                System.out.println("Blood Bank Management System");
                System.out.println("1. Manage Donors");
                System.out.println("2. Manage Blood");
                System.out.println("3. Manage Blood Banks");
                System.out.println("4. Manage Blood Bank Managers");
                System.out.println("5. Exit");
                System.out.print("Select an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        manageDonors();
                        break;
                    case 2:
                        manageBlood();
                        break;
                    case 3:
                        manageBloodBanks();
                        break;
                    case 4:
                        manageBloodBankManagers();
                        break;
                    case 5:
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void manageDonors() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("Manage Donors");
            System.out.println("1. Add Donor");
            System.out.println("2. View Donors");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addDonor();
                    break;
                case 2:
                    viewDonors();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addDonor() throws SQLException {
        System.out.print("Enter donor name: ");
        String name = scanner.nextLine();
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();

        String query = "INSERT INTO Donors (name, age, address, phone) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setInt(2, age);
        pstmt.setString(3, address);
        pstmt.setString(4, phone);
        pstmt.executeUpdate();
        System.out.println("Donor added successfully.");
    }

    private static void viewDonors() throws SQLException {
        String query = "SELECT * FROM Donors";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Donors List:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Age: " + rs.getInt("age") + ", Address: " + rs.getString("address") + ", Phone: " + rs.getString("phone"));
        }
    }

    private static void manageBlood() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("Manage Blood");
            System.out.println("1. Add Blood");
            System.out.println("2. View Blood");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBlood();
                    break;
                case 2:
                    viewBlood();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBlood() throws SQLException {
        System.out.print("Enter blood type: ");
        String bloodType = scanner.nextLine();
        System.out.print("Enter cost: ");
        double cost = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        String query = "INSERT INTO Blood (bloodType, cost) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, bloodType);
        pstmt.setDouble(2, cost);
        pstmt.executeUpdate();
        System.out.println("Blood added successfully.");
    }

    private static void viewBlood() throws SQLException {
        String query = "SELECT * FROM Blood";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Blood List:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Blood Type: " + rs.getString("bloodType") + ", Cost: " + rs.getDouble("cost"));
        }
    }

    private static void manageBloodBanks() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("Manage Blood Banks");
            System.out.println("1. Add Blood Bank");
            System.out.println("2. View Blood Banks");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBloodBank();
                    break;
                case 2:
                    viewBloodBanks();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBloodBank() throws SQLException {
        System.out.print("Enter blood bank name: ");
        String name = scanner.nextLine();

        String query = "INSERT INTO BloodBanks (name) VALUES (?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.executeUpdate();
        System.out.println("Blood Bank added successfully.");
    }

    private static void viewBloodBanks() throws SQLException {
        String query = "SELECT * FROM BloodBanks";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Blood Banks List:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name"));
        }
    }

    private static void manageBloodBankManagers() throws SQLException {
        boolean back = false;
        while (!back) {
            System.out.println("Manage Blood Bank Managers");
            System.out.println("1. Add Blood Bank Manager");
            System.out.println("2. View Blood Bank Managers");
            System.out.println("3. Back");
            System.out.print("Select an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addBloodBankManager();
                    break;
                case 2:
                    viewBloodBankManagers();
                    break;
                case 3:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addBloodBankManager() throws SQLException {
        System.out.print("Enter manager name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        String query = "INSERT INTO BloodBankManagers (name, phone, email) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, name);
        pstmt.setString(2, phone);
        pstmt.setString(3, email);
        pstmt.executeUpdate();
        System.out.println("Blood Bank Manager added successfully.");
    }

    private static void viewBloodBankManagers() throws SQLException {
        String query = "SELECT * FROM BloodBankManagers";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        System.out.println("Blood Bank Managers List:");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") + ", Phone: " + rs.getString("phone") + ", Email: " + rs.getString("email"));
        }
    }
}
