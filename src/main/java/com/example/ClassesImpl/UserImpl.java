package com.example.ClassesImpl;

import com.example.database.DataBaseHandler;
import com.example.entities.Post;
import com.example.entities.User;
import com.example.repositories.UserRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserImpl implements UserRepository {

    private DataBaseHandler dbHandler = new DataBaseHandler();
    public static final String TBL_USERS = "users";

    @Override
    public void addUser(User user) {
        String query = "INSERT INTO " + TBL_USERS + " (user_first_name, user_last_name, username, user_password," +
                "user_number, user_gender, post_id)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement preparedStatement = dbHandler.getDbConnection().prepareStatement(query);
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
    public void updateUSer(User user) {

    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public User getUserById(int userId) {
        return null;
    }

    @Override
    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        List<User> userList = new ArrayList<>();
        String query = "SELECT * FROM" + TBL_USERS;
        PreparedStatement statement = dbHandler.getDbConnection().prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            User user = new User();
            user.setFirstName(resultSet.getString(1));
            user.setLastName(resultSet.getString(2));
            user.setUsername(resultSet.getString(3));
            user.setPassword(resultSet.getString(4));
            user.setGender(resultSet.getString(5));

            PostImpl postImpl = new PostImpl();
            Post post = postImpl.getPostById(resultSet.getInt(6));
            user.setPost(post);

            userList.add(user);
        }
        return userList;
    }
}
