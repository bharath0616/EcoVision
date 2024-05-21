JDBC:
 

import java.sql.*;

public class DatabaseOperations {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "pwd"; // Use the correct password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connect to MySQL Server
            try (Connection con = DriverManager.getConnection(url, user, password)) {
                // Create Database
                createDatabase(con);

                // Connect to the newly created database
                try (Connection conDB = DriverManager.getConnection(url + "Riviera", user, password)) {
                    // Create a table
                    createTable(conDB);
                    // Insert multiple records using PreparedStatement
                    insertRecords(conDB);
                    // Read and display records
                    readRecords(conDB);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void createDatabase(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            String sql = "CREATE DATABASE IF NOT EXISTS Riviera";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        }
    }

    private static void createTable(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS REGISTRATION " +
                         "(id INTEGER NOT NULL, " +
                         "Name VARCHAR(255), " +
                         "PRIMARY KEY (id))";
            stmt.executeUpdate(sql);
            System.out.println("Table created successfully...");
        }
    }

    private static void insertRecords(Connection con) throws SQLException {
        String sql = "INSERT INTO REGISTRATION (id, Name) VALUES (?, ?)";
        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            // Insert first record
            pstmt.setInt(1, 101);
            pstmt.setString(2, "Karnan");
            pstmt.executeUpdate();

            // Insert second record
            pstmt.setInt(1, 102);
            pstmt.setString(2, "John Doe");
            pstmt.executeUpdate();

            System.out.println("Values inserted successfully...");
        }
    }

    private static void readRecords(Connection con) throws SQLException {
        String sql = "SELECT id, Name FROM REGISTRATION";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("Reading records...");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                System.out.println("ID: " + id + ", Name: " + name);
            }
        }
    }
}
