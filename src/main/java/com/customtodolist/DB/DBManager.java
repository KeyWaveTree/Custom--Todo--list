package com.customtodolist.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class DBManager {
    private static final String URL = "jdbc:sqlite:src/main/java/com/customtodolist/DB/TodoDatabase";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void insertTask(String task, String detail, String category, String date, int completion, int importance) throws SQLException {
        String sql = "INSERT INTO Tasks (Task, Detail, Category, Date, Completion, Importance) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, task);
            pstmt.setString(2, detail);
            pstmt.setString(3, category);
            pstmt.setString(4, date);
            pstmt.setInt(5, completion);
            pstmt.setInt(6, importance);
            pstmt.executeUpdate();
        }
    }

    public static void deleteTask(int id) throws SQLException {
        String sql = "DELETE FROM Tasks WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    public static void updateTask(int id, int completion) throws SQLException {
        String sql = "UPDATE Tasks SET Completion = ? WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, completion);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        }
    }

    public static void selectTasks() {
        String sql = "SELECT * FROM Tasks;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println(rs.getInt("id") + "\t" +
                        rs.getString("Task") + "\t" +
                        rs.getString("Detail") + "\t" +
                        rs.getString("Category") + "\t" +
                        rs.getString("Date") + "\t" +
                        rs.getInt("Completion") + "\t" +
                        rs.getInt("Importance"));
            }
        } catch (SQLException e) {
            System.out.println("Database operation failed: " + e.getMessage());
        }
    }
}