package com.example.repositories;

import com.example.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    void addUser(User user);
    void updateUser(User user);
    void deleteUser(User user) throws SQLException, ClassNotFoundException;

    List<User> getAllUsers() throws SQLException, ClassNotFoundException;

}
