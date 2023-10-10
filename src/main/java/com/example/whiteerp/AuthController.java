package com.example.whiteerp;

import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.example.database.DataBaseHandler;
import com.example.entities.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button loginButton;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private Button signUpButton;

    @FXML
    void initialize() {


        loginButton.setOnAction(actionEvent -> {
            String userLogin = loginTextField.getText().trim();
            String userPassword = passwordTextField.getText().trim();

            if(!userLogin.equals("") && !userPassword.equals("")){
                toLoginUser(userLogin, userPassword);
            }else
                System.out.println("Логин и пароль пустые");
        });


        signUpButton.setOnAction(actionEvent -> {
            openNewScene(signUpButton, "signUp.fxml");
        });
    }

    private void toLoginUser(String userLogin, String userPassword){
        DataBaseHandler dbHandler = new DataBaseHandler();
        User user = new User();
        user.setUsername(userLogin);
        user.setPassword(userPassword);

        ResultSet result = dbHandler.getUser(user);

        int counter = 0;
        try {
            while(result.next()){
                counter++;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(counter >= 1){
            openNewScene(loginButton, "mainApp.fxml");
        }

    }


    public void openNewScene(Button button, String window){
        button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try{
            loader.load();
        }catch (Exception e){
            e.printStackTrace();
        }

        Parent root =  loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
}