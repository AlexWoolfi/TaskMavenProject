package org.example.conectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingeltonToDb  {
    private static SingeltonToDb instance;
    static final String DB_URL = "jdbc:postgresql://localhost:5050/TaskManager";
    static final String USER = "postgres";
    static final String PASS = "Woolfi504";

    public static Connection connectSingleToBD() {
        Connection con = null;
        try {
             con = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    private SingeltonToDb() {}

    public static SingeltonToDb getInstance() {
        if(instance == null){
            instance = new SingeltonToDb();
        }
        return instance;
    }
}
