package org.example.conectDB;
import java.sql.*;

public enum ConnectionToPostgress {
    INSTANCE;
    static final String DB_URL = "jdbc:postgresql://localhost:5050/TaskManager";
    static final String USER = "postgres";
    static final String PASS = "Woolfi504";


    public static Connection startConnection() {
        Connection con = null;
        try{
            con = DriverManager.getConnection(DB_URL,USER,PASS);
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
        return con;
    }
}
