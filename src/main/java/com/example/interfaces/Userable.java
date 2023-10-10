package com.example.interfaces;

import com.example.entities.User;

import java.util.List;

public interface Userable {
    public int add(User user);
    public int remove(User user);
    public List<User> getAll(User user);
    public User get(int id);

    public int delete(int id);
}
