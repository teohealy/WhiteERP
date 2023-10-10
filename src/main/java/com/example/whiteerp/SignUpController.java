package com.example.whiteerp;

import com.example.database.DataBaseHandler;
import com.example.entities.Post;
import com.example.entities.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cbPost;

    @FXML
    private CheckBox femaleCheckBox;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField loginTextField;

    @FXML
    private CheckBox maleCheckBox;

    @FXML
    private TextField numberTextField;

    @FXML
    private TextField passwordTextField;


    @FXML
    private Button signUpButton;



    @FXML
    void initialize() throws Exception {
        List<Post> postList = getPosts();
        List<String> postNames = new ArrayList<>();
        for(Post post: postList){
            postNames.add(post.getName());
        }

        List<String> postIds = new ArrayList<>();
        for(Post post: postList){
            postIds.add(Integer.toString(post.getId()));
        }

        cbPost.setItems(FXCollections.observableArrayList(postNames));

        signUpButton.setOnAction(actionEvent -> {
            signUpNewUser(postList, postNames);

        });

    }


    public List<String> getData() throws ClassNotFoundException, SQLException{
        String table = "posts";
        String column = "post_name";
        DataBaseHandler dbHandler = new DataBaseHandler();
        List<String> options = new ArrayList<>();

        String selectQuery = "SELECT "+ column + " FROM " + table ;
        PreparedStatement statement = dbHandler.getDbConnection().prepareStatement(selectQuery);
        ResultSet set = statement.executeQuery();

        while(set.next()){
            options.add(set.getString(column));
        }
        statement.close();
        set.close();

        return options;
    }


    public List<Post> getPosts() throws SQLException, ClassNotFoundException {
        String table = "posts";
        DataBaseHandler dbHandler = new DataBaseHandler();
        List<Post> options = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + table ;
        PreparedStatement statement = dbHandler.getDbConnection().prepareStatement(selectQuery);
        ResultSet set = statement.executeQuery();

        while(set.next()){
            int id = set.getInt(1);
            String postName = set.getString(2);
            Post post = new Post(postName);
            post.setId(id);
            options.add(post);
        }
        statement.close();
        set.close();

        return options;

    }

    private void signUpNewUser(List<Post> postList, List<String> postNames){
        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstname = firstNameTextField.getText();
        String lastname = lastNameTextField.getText();
        String post = cbPost.getValue();
        Post tempPost = postList.get(postNames.indexOf(post));
        String username = loginTextField.getText();
        String password = passwordTextField.getText();
        String number = numberTextField.getText();
        String gender = "";

        if (maleCheckBox.isSelected()){
            gender = "Male";
        }else
            gender = "Female";

        User user = new User(firstname, lastname, tempPost, username, password, number, gender);


        dbHandler.signUpUser(user);

    }

}