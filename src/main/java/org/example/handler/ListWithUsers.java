package org.example.handler;

import org.example.entity.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListWithUsers implements Serializable {


    public static void createUser(String[] args) throws IOException {
        User user = new User();
        user.setName(Patterns.cleanWorldArgs(args[1]));
        user.setLastName(Patterns.cleanWorldArgs(args[2]));
        user.setUserName(Patterns.cleanWorldArgs(args[3]));


    }


}