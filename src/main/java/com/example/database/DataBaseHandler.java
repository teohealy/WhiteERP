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


    public void signUpUser(User user){
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_FIRST_NAME + "," + Const.USER_LAST_NAME + "," +
                Const.USER_POST + "," + Const.USERNAME + "," + Const.USER_PASSWORD + ", " + Const.USER_NUMBER + "," +  Const.USER_GENDER+") " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getPost().getName());
            preparedStatement.setString(4, user.getUsername());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, user.getNumber());
            preparedStatement.setString(7, user.getGender());

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public List<String>getPostNames() throws Exception {
        String post_name = new String("post_name");
        String table_name = new String("posts");
        ResultSet resultSet = null;
        try {
            String query = "SELECT " + post_name + " FROM " + table_name;
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            List<String> postNames = new ArrayList<>();
            while (resultSet.next()) {
                String postName = resultSet.getString("post_name");
                postNames.add(postName);
            }

            return postNames;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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