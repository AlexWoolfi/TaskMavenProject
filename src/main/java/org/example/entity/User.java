package org.example.entity;

import org.example.conectDB.ConnectionToPostgress;
import org.example.conectDB.SingeltonToDb;
import org.example.handler.Patterns;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class User {

    private Long id;
    private String userName;
    private String name;
    private String lastName;

    public static final String insertNewUser = "INSERT INTO users (user_name,user_lastname,user_username) VALUES (?,?,?)";

    public User(Long id, String userName, String name, String lastName) {
        this.id = id;
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static void createUser(String[] args) throws IOException {
        List<String> dataUsers = new ArrayList<>();
        User user = new User();
        user.setName(Patterns.cleanWorldArgs(args[1]));
        user.setLastName(Patterns.cleanWorldArgs(args[2]));
        user.setUserName(Patterns.cleanWorldArgs(args[3]));
        dataUsers.add(user.getName());
        dataUsers.add(user.getLastName());
        dataUsers.add(user.getUserName());

        try {
            addUserToDB(dataUsers, insertNewUser);
            System.out.println(user.insertNewUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addUserToDB(List<String> listUser, String s) throws SQLException {
        String name = listUser.get(0);
        String lastName = listUser.get(1);
        String user_name = listUser.get(2);
        System.out.println(name);
        System.out.println(lastName);
        System.out.println(user_name);
        s = insertNewUser;
        Connection connection = null;
        PreparedStatement st = null;
        try {
            connection = ConnectionToPostgress.INSTANCE.startConnection();
            st = connection.prepareStatement(s);
            st.setString(1, name);
            st.setString(2, lastName);
            st.setString(3, user_name);
            int row = st.executeUpdate();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void showAllusers() {
//        Connection con = ConnectionToPostgress.startConnection();
        Connection con = SingeltonToDb.connectSingleToBD();
        String query = "SELECT * FROM users";
        try (Statement statement = con.createStatement()) {
            statement.executeQuery(query);
            ResultSet resultSet = statement.executeQuery(query);
          List<User> users = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("user_id");
                String userName = resultSet.getString("user_name");
                String firstName = resultSet.getString("user_lastname");
                String lastName = resultSet.getString("user_username");

                users.add(new User(id, userName, firstName, lastName));
            }
               for(User u:users){
                   System.out.println(u);
               }

        } catch (SQLException throwables) {

        }
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +

                '}';
    }
}
