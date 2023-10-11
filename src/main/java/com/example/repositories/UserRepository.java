package com.example.repositories;

import com.example.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    void addUser(User user);
    void updateUSer(User user);
    void deleteUser(User user);
    User getUserById(int userId);

    List<User> getAllUsers() throws SQLException, ClassNotFoundException;

}
