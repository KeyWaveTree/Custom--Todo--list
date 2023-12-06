package com.customtodolist.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {
    private static final String URL = "jdbc:sqlite:src/main/java/com/customtodolist/DB/TodoDatabase"; // 데이터베이스 파일 경로

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}