package org.example.conectDB;

import org.example.handler.QweryConstant;

import java.sql.*;

public enum ConnectionToPostgressToShowUsers {
    INSTANCE;
    static final String DB_URL = "jdbc:postgresql://localhost:5050/TaskManager";
    static final String USER = "postgres";
    static final String PASS = "Woolfi504";


    public static Connection startConnection() {
        Connection con = null;
        try{
            con = DriverManager.getConnection(DB_URL,USER,PASS);
//           return con;
       } catch (SQLException throwables) {
           throwables.printStackTrace();
       }
        return con;
    }
}
