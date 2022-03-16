package org.example.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.conectDB.ConnectionToPostgress;
import org.example.conectDB.ConnectionToPostgress;
import org.example.handler.Patterns;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Task {
    public static final String insertNewTASK = "INSERT INTO tasks (task_name,task_body,fk_users_id) VALUES (?,?,?)";
    public static final String query = "SELECT * FROM users WHERE user_username = ?";
//    public static final String query2 = "SELECT * FROM tasks WHERE user_username = ?";

    private Long id;
    private String title;
    private String description;
    private User user;
//    private List<User> users;

    public static void createTask(String[] args) {
        Task task = new Task();
        task.setTitle(Patterns.cleanWorldArgs(args[2]));
        task.setDescription(Patterns.cleanWorldArgs(args[3]));
        System.out.println(task);

        String unicNameUser = Patterns.cleanWorldArgs(args[1]);
        System.out.println(unicNameUser);

        Connection con = null;
        PreparedStatement pst = null;
        Statement statement = null;
        try {
            con = ConnectionToPostgress.INSTANCE.startConnection();
            pst = con.prepareStatement(insertNewTASK);
            pst.setString(1, task.getTitle());
            pst.setString(2, task.getDescription());
            User user = getDataUser(unicNameUser, query);
            long id = user.getId();
            pst.setInt(3, (int) id);
            pst.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public static User getDataUser(String unicNameUser, String query) {
        User user = null;
        Connection con;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            String s = query;
            con = ConnectionToPostgress.startConnection();
            preparedStatement = con.prepareStatement(s);
            preparedStatement.setString(1, unicNameUser);
            resultSet = preparedStatement.executeQuery();
            user = new User();

            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setUserName(resultSet.getString(4));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }


    public static void showAllUsersWithTask(String[] args) {
        Connection con;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        String userName = Patterns.cleanWorldArgs(args[1]);
        String selectUser = "SELECT * FROM users WHERE user_username = ?";
        User user = getDataUser(userName, selectUser);
        String userID = user.getId().toString();
        System.out.println(user);
        try {
            String query2 = "SELECT * FROM tasks WHERE fk_users_id =?";
            con = ConnectionToPostgress.startConnection();
            preparedStatement = con.prepareStatement(query2);
            preparedStatement.setString(1, userID);
            resultSet = preparedStatement.executeQuery(query2);
            List<Task> tasks = new ArrayList<>();

            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String title = resultSet.getString(2);
                String description = resultSet.getString(3);

                tasks.add(new Task(id, title, description, user));
            }
            for (Task t : tasks) {
                System.out.println(t);
            }

        } catch (SQLException throwables) {

        }
    }

    public static User getDataUserFromTask(String taskName) {
        User user = null;
        Connection con;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
//            String query = "SELECT * FROM users WHERE user_username = ?";
            con = ConnectionToPostgress.startConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, taskName);
            resultSet = preparedStatement.executeQuery();
            user = new User();

            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setUserName(resultSet.getString(4));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                '}';
    }
}
