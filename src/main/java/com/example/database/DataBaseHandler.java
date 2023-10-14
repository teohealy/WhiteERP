package com.example.database;
import com.example.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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


    public ResultSet getUser(User user){
        ResultSet resultSet = null;

        try{
            String query = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERNAME + " =? AND " + Const.USER_PASSWORD + "=?";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            resultSet = preparedStatement.executeQuery();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultSet;
    }



}