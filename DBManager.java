package com.customtodolist.DB;

import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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

    public static List<Map<String, Object>> getTodoItems() throws SQLException {
        List<Map<String, Object>> todoItems = new ArrayList<>();
        String sql = "SELECT * FROM Tasks"; // Adjust this query based on your table structure

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                todoItems.add(row);
            }
        }

        return todoItems;
    }

}