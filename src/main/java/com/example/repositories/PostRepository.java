package com.example.repositories;

import com.example.entities.Post;


import java.sql.SQLException;
import java.util.List;

public interface PostRepository {
    List<Post> getAllPosts() throws SQLException, ClassNotFoundException;
    Post getPostById(int id) throws SQLException, ClassNotFoundException;

}
