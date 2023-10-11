package com.example.ClassesImpl;

import com.example.database.DataBaseHandler;
import com.example.entities.Post;
import com.example.repositories.PostRepository;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostImpl implements PostRepository {
    private static final String TBL_POSTS = "posts";
    private DataBaseHandler dbHandler = new DataBaseHandler();
    @Override
    public List<Post> getAllPosts() throws SQLException, ClassNotFoundException {
        List<Post> postList = new ArrayList<>();
        String query = "SELECT * FROM " + TBL_POSTS;
        PreparedStatement statement = dbHandler.getDbConnection().prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            int id = resultSet.getInt(1);
            String postName = resultSet.getString(2);
            Post post = new Post(postName);
            post.setId(id);
            postList.add(post);
        }
        return postList;
    }

    @Override
    public Post getPostById(int postId) throws SQLException, ClassNotFoundException {
        Post post = null;
        String query = "SELECT * FROM " + TBL_POSTS + " WHERE post_id=?";
        PreparedStatement statement = dbHandler.getDbConnection().prepareStatement(query);
        statement.setInt(1, postId);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            String postName = resultSet.getString(2);
            post = new Post(postName);
            post.setId(postId);
        }
        return post;
    }
}
