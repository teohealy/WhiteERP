package com.example.whiteerp;

import com.example.ClassesImpl.PostImpl;
import com.example.ClassesImpl.UserImpl;
import com.example.entities.Post;
import com.example.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;


public class UsersTabController {

    @FXML
    private Button
            btnAddUser, btnDeleteUser, btnUpdateUser, btnClear;

    @FXML
    private TableView<User> tblUsers;

    @FXML
    private TableColumn<User, String>
            tcUserGender, tcUserLastName, tcUserLogin, tcUserName, tcUserPassword, tcUserPhone, tcUserPost;

    @FXML
    private TextField
            tfFirstName, tfLastName, tfLogin, tfNumber, tfPassword;

    @FXML
    private CheckBox
            checkBoxFemale, checkBoxMale;

    @FXML
    private ComboBox<Post> cbPost;


    private UserImpl userImpl = new UserImpl();
    private PostImpl postImpl = new PostImpl();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        showUsers();

        List<Post> postList = postImpl.getAllPosts();

        cbPost.setItems(FXCollections.observableArrayList(postList));

        tblUsers.setOnMouseClicked(event -> {
            User selectedUser = tblUsers.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
                tfFirstName.setText(selectedUser.getFirstName());
                tfLastName.setText(selectedUser.getLastName());
                tfLogin.setText(selectedUser.getUsername());
                tfPassword.setText(selectedUser.getPassword());
                tfNumber.setText(selectedUser.getNumber());
                if ("Male".equals(selectedUser.getGender())) {
                    checkBoxMale.setSelected(true);
                    checkBoxFemale.setSelected(false);
                } else {
                    checkBoxFemale.setSelected(true);
                    checkBoxMale.setSelected(false);
                }
                cbPost.setValue(selectedUser.getPost());
            }
        });

    }


    public void showUsers() throws SQLException, ClassNotFoundException {
        tcUserName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tcUserLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tcUserLogin.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tcUserPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        tcUserPhone.setCellValueFactory(new PropertyValueFactory<>("Number"));
        tcUserGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        tcUserPost.setCellValueFactory(new PropertyValueFactory<>("Post"));
        loadUsers();
    }

    public void loadUsers() throws SQLException, ClassNotFoundException {
        ObservableList<User> users = FXCollections.observableArrayList();
        List<User> userList = userImpl.getAllUsers();
        for (User user: userList){
            users.add(user);
        }
        tblUsers.setItems(users);
    }

    @FXML
    void handleUserTabButtons(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (event.getSource() == btnAddUser){
            addUser();
            loadUsers();
            clearFields();
        }
        if (event.getSource() == btnUpdateUser){
            updateUser();
            loadUsers();
        }
        if (event.getSource() == btnDeleteUser){
            deleteUser();
            loadUsers();
            clearFields();
        }
        if (event.getSource() == btnClear){
            clearFields();
        }
    }


    public void addUser(){
        String firstname = tfFirstName.getText();
        String lastname = tfLastName.getText();
        String username = tfLogin.getText();
        String password = tfPassword.getText();
        String number = tfNumber.getText();
        String gender = "";
        if (checkBoxMale.isSelected()){
            gender = "Male";
        }else
            gender = "Female";
        Post post = cbPost.getValue();

        User user = new User(firstname, lastname, username, password, number, gender, post);

        userImpl.addUser(user);
    }

    public void updateUser(){
        User user = tblUsers.getSelectionModel().getSelectedItem();

        if (user != null){
            user.setFirstName(tfFirstName.getText());
            user.setLastName(tfLastName.getText());
            user.setUsername(tfLogin.getText());
            user.setPassword(tfPassword.getText());
            user.setNumber(tfNumber.getText());
            user.setGender(checkBoxMale.isSelected() ? "Male" : "Female");
            user.setPost(cbPost.getValue());
            userImpl.updateUser(user);
        }
    }

    private void deleteUser() throws SQLException, ClassNotFoundException {
        User user = tblUsers.getSelectionModel().getSelectedItem();

        if (user != null){
            userImpl.deleteUser(user);
        }
    }

    private void clearFields() {
        tfFirstName.clear();
        tfLastName.clear();
        tfLogin.clear();
        tfPassword.clear();
        tfNumber.clear();
        checkBoxMale.setSelected(false);
        checkBoxFemale.setSelected(false);
        cbPost.getSelectionModel().clearSelection();
    }
}
