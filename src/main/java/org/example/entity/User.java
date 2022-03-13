package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.conectDB.ConnectionToPostgressToShowUsers;
import org.example.handler.Patterns;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import sun.util.resources.LocaleData;

//import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    public Long id;
    public String userName;
    public String name;
    public String lastName;

    public static final String insertNewUser = "INSERT INTO users (user_name,user_lastname,user_username) VALUES (?,?,?)";


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
            connection = ConnectionToPostgressToShowUsers.INSTANCE.startConnection();
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
