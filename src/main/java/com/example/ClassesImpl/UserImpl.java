package com.example.ClassesImpl;

import com.example.database.DataBaseHandler;
import com.example.entities.Post;
import com.example.entities.User;
import com.example.repositories.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserRepository {

    private DataBaseHandler dbHandler = new DataBaseHandler();
    private Connection connection = null;
    private static final String TBL_USERS = "users";

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO " + TBL_USERS + " (user_first_name, user_last_name, username, user_password," +
                "user_number, user_gender, post_id)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            connection = dbHandler.getDbConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getNumber());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setInt(7, user.getPost().getId());

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) {
        String query = "UPDATE " + TBL_USERS +
                " SET user_first_name = ?, user_last_name = ?, username = ?, user_password = ?, user_number = ?, user_gender = ?, post_id = ? WHERE user_id = ?";
        try{
            connection = dbHandler.getDbConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getNumber());
            preparedStatement.setString(6, user.getGender());
            preparedStatement.setInt(7, user.getPost().getId());
            preparedStatement.setInt(8, user.getId());

            preparedStatement.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(User user) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + TBL_USERS + " WHERE user_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();

    }

    @Override
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM " + TBL_USERS;
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setUsername(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setNumber(resultSet.getString(6));
            user.setGender(resultSet.getString(7));

            PostImpl postImpl = new PostImpl();
            Post post = postImpl.getPostById(resultSet.getInt(8));
            user.setPost(post);

            userList.add(user);
        }
        return userList;
    }

    @Override
    public User getUserById(int userId) throws SQLException, ClassNotFoundException {
        User user = new User();
        String query = "SELECT * FROM " + TBL_USERS + " WHERE user_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, userId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setUsername(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setNumber(resultSet.getString(6));
            user.setGender(resultSet.getString(7));

            PostImpl postImpl = new PostImpl();
            Post post = postImpl.getPostById(resultSet.getInt(8));
            user.setPost(post);
        }
        return user;
    }
}
