package org.example;

import org.example.handler.FiltrArgs;

import java.io.IOException;

import static org.example.conectDB.ConnectionToPostgressToShowUsers.startConnection;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        FiltrArgs.filtrFromArgs(args);


    }
}
