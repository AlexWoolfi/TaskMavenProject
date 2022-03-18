package org.example.entity;

import org.example.conectDB.ConnectionToPostgress;
import org.example.conectDB.SingeltonToDb;
import org.example.handler.Patterns;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Task {
    public static final String insertNewTASK = "INSERT INTO tasks (task_name,task_body,fk_users_id) VALUES (?,?,?)";
    public static final String query = "SELECT * FROM users WHERE user_username = ?";
    public static final String query3 = "SELECT * FROM tasks";

    private Long id;
    private String title;
    private String description;
    private Long user_id;
    private List<User> users;

    public Task() {
    }

    public Task(Long id, String title, String description, Long user_id, List<User> users) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.user_id = user_id;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static void createTask(String[] args) {
        Task task = new Task();
        task.setTitle(Patterns.cleanWorldArgs(args[2]));
        task.setDescription(Patterns.cleanWorldArgs(args[3]));
        System.out.println(task);

        String unicNameUser = Patterns.cleanWorldArgs(args[1]);
        System.out.println(unicNameUser);

        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = SingeltonToDb.connectSingleToBD();
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

    public static void workMethod(String[] args) {
        String user_name = Patterns.cleanWorldArgs(args[1]);
        String queryFromUser = query;
        User user = getDataUser(user_name, queryFromUser);
        showAllUsersWithTask(user);

    }

    public static void showAllUsersWithTask(User user) {
        long us_id = user.getId();
        String queryFromUser = "SELECT * FROM tasks WHERE fk_users_id = ?";
        Connection connection;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        Task task;
        List<Task> tasks;
        try {

            connection = ConnectionToPostgress.startConnection();
            preparedStatement = connection.prepareStatement(queryFromUser);
            preparedStatement.setLong(1,us_id);
            resultSet = preparedStatement.executeQuery();
            tasks = new ArrayList<>();
            while (resultSet.next()) {
                long id = resultSet.getLong("task_id");
                String title = resultSet.getString("task_name");
                String description = resultSet.getString("task_body");
                int user_idD = resultSet.getInt("fk_users_id");
                task = new Task();
                task.setUser_id((long) user_idD);
                task.setTitle(title);
                task.setDescription(description);
                task.setId(id);
                tasks.add(task);
            }
            for (Task t : tasks) {
                System.out.println(t);
            }
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

    public static void showTasksFromUser(String[] args) {
        String userName = Patterns.cleanWorldArgs(args[1]);
        String selectUser = "SELECT * FROM users WHERE user_username = ?";
        User user = getDataUser(userName, selectUser);
        long user_id = user.getId();
        System.out.println(user);
        System.out.println(user_id);

    }

    public static User getDataUserFromTask(String taskName) {
        User user = null;
        Connection con;
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        try {
            con = ConnectionToPostgress.startConnection();
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, taskName);
            resultSet = preparedStatement.executeQuery();

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
                ", user_id=" + user_id +
                '}';
    }
}
