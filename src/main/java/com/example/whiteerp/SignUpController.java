package com.example.whiteerp;

import java.net.URL;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.example.database.DataBaseHandler;
import com.example.entities.Post;
import com.example.entities.User;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

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

        DataBaseHandler dbHandler = new DataBaseHandler();
        ObservableList<String> list = (ObservableList<String>) dbHandler.getPostNames();
        cbPost.getItems().addAll(list);

        signUpButton.setOnAction(actionEvent -> {
            signUpNewUser();

        });

    }





    private void signUpNewUser(){
        DataBaseHandler dbHandler = new DataBaseHandler();

        String firstname = firstNameTextField.getText();
        String lastname = lastNameTextField.getText();
        String post = cbPost.getValue();
        String username = loginTextField.getText();
        String password = passwordTextField.getText();
        String number = numberTextField.getText();
        String gender = "";

        if (maleCheckBox.isSelected()){
            gender = "Male";
        }else
            gender = "Female";

        Post postObject = new Post(post);
        User user = new User(firstname, lastname, postObject, username, password, number, gender);

        dbHandler.signUpUser(user);

    }

}