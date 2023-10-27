package com.example.database;

import com.example.entities.User;

import java.sql.*;

public class DataBaseHandler extends Configs{
    Connection dbConnection;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException{
        Class.forName("org.postgresql.Driver");
        dbConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, dbUser, dbPassword);
        if (dbConnection != null){
            System.out.println("Connection established!");
        }else {
            System.out.println("Connection failed");
        }

        return dbConnection;
    }
}