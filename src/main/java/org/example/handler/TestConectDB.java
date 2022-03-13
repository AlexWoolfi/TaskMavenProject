//package org.example.handler;
//
//import java.sql.*;
//
//public class TestConectDB {
//    public class ConnectionToPostgressToShowUsers {
//
//        static final String DB_URL = "jdbc:postgresql://localhost:5050/TaskManager";
//        static final String USER = "postgres";
//        static final String PASS = "Woolfi504";
//
//
//
////        public  void startConnection() {
////            try(Connection con = DriverManager.getConnection(DB_URL,USER,PASS)) {
//////           Statement statement = con.createStatement();
////                Integer userID = 7;
////                PreparedStatement preparedStatement = con.prepareStatement(QweryConstant.QUERY_ALL_USERS);
////                preparedStatement.setInt(1,userID);
////                ResultSet resultSet = preparedStatement.executeQuery();
////
////                while (resultSet.next()) {
//////               System.out.print(resultSet.getInt(1)+" ");
//////               System.out.print(resultSet.getString(2)+" ");
//////               System.out.println(resultSet.getString(5)+" ");
//////               System.out.println("**************************");
////
//////               System.out.print("ID: " + resultSet.getInt("user_id"));
//////               System.out.print(", User Name: " + resultSet.getString("user_name"));
//////               System.out.println(", User Last Name: " + resultSet.getString("user_lastname"));
//////               System.out.println(", User USerName: " + resultSet.getString("user_username"));
//////               System.out.println(", Create Data: " + resultSet.getDate("data_create"));
//////               System.out.println("************************************");
////                }
//            }catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//}
