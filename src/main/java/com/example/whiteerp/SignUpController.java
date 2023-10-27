package com.example.whiteerp;

import com.example.implementations.PostImpl;
import com.example.implementations.UserImpl;
import com.example.entities.Post;
import com.example.entities.User;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.List;

public class SignUpController {

    @FXML
    private ComboBox<Post> cbPost;

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


    private PostImpl postImpl = new PostImpl();
    private UserImpl userImpl = new UserImpl();

    @FXML
    void initialize() throws Exception {

        List<Post> postList = postImpl.getAllPosts();

        cbPost.setItems(FXCollections.observableArrayList(postList));

        signUpButton.setOnAction(actionEvent -> {
            signUpNewUser();
        });
    }


    private void signUpNewUser(){

        String firstname = firstNameTextField.getText();
        String lastname = lastNameTextField.getText();
        String username = loginTextField.getText();
        String password = passwordTextField.getText();
        String number = numberTextField.getText();
        String gender = "";
        if (maleCheckBox.isSelected()){
            gender = "Male";
        }else
            gender = "Female";
        Post post = cbPost.getValue();

        User user = new User(firstname, lastname, username, password, number, gender, post);

        userImpl.addUser(user);

    }


}