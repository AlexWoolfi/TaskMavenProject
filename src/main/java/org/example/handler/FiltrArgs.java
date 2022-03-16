package org.example.handler;

import org.example.entity.User;

import java.io.IOException;

import static org.example.entity.Task.createTask;
import static org.example.entity.Task.showAllUsersWithTask;
import static org.example.entity.User.showAllusers;


public class FiltrArgs  {
    static String s = new String();


    public static void filtrFromArgs(String[] args) throws IOException {
        for(int i=0;i<args.length;i++) {
            s = Patterns.cleanWorldForFirstArgs(args[0]);
        }
        switch (s){
            case "createUser":
                System.out.println("Ok create");
                User.createUser(args);
                break;
            case "showAllUsers":
                System.out.println("showAllUsers");
                showAllusers();
                break;
            case "addTask":
                System.out.println("addTask");
                createTask(args);
                break;
            case "showTasks":

                System.out.println("showTasks");
                showAllUsersWithTask(args);
                break;
            case "showAllTaskWithUser":

                break;
            default:
                System.out.println("Command not use, try again");

        }
    }

}

