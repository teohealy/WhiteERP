package com.example.whiteerp;

import com.example.ClassesImpl.UserImpl;
import com.example.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;


public class MainAppController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button
            btnClients, btnDocuments, btnMedicine, btnSalary, btnSchedule, btnStock, btnUsers;

    @FXML
    private Button btnAddUser;


    @FXML
    private Label lbStatus;


    @FXML
    private Tab tabClients, tabUsers;


    @FXML
    private TableView<User> tblUsers;

    @FXML
    private TableColumn<User, String>
            tcUserGender, tcUserLastName, tcUserLogin, tcUserName, tcUserPassword, tcUserPhone, tcUserPost;


    private UserImpl userImpl = new UserImpl();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        tcUserName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tcUserLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tcUserLogin.setCellValueFactory(new PropertyValueFactory<>("Username"));
        tcUserPassword.setCellValueFactory(new PropertyValueFactory<>("Password"));
        tcUserPhone.setCellValueFactory(new PropertyValueFactory<>("Number"));
        tcUserGender.setCellValueFactory(new PropertyValueFactory<>("Gender"));
        tcUserPost.setCellValueFactory(new PropertyValueFactory<>("Post"));

        loadUsers();
    }



    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnUsers) {
            tabUsers.setClosable(true);
            tabUsers.getTabPane().getSelectionModel().select(tabUsers);
            lbStatus.setText("Сотрудники");
        } else if (event.getSource() == btnClients) {
            lbStatus.setText("Клиенты");
        } else if (event.getSource() == btnSchedule) {
            lbStatus.setText("Расписание");
        } else if (event.getSource() == btnStock) {
            lbStatus.setText("Склад");
        } else if (event.getSource() == btnDocuments) {
            lbStatus.setText("Документы");
        } else if (event.getSource() == btnMedicine) {
            lbStatus.setText("Медицина");
        } else if (event.getSource() == btnSalary) {
            lbStatus.setText("Зарплаты");
        }
    }


    @FXML
    private void handleTabs(){
        tabClients.setOnSelectionChanged(ActionEvent -> lbStatus.setText("Клиенты"));
        tabUsers.setOnSelectionChanged(event -> lbStatus.setText("Сотрудники"));
    }


    public void loadUsers() throws SQLException, ClassNotFoundException {
        ObservableList<User> users = FXCollections.observableArrayList();
        List<User> userList = userImpl.getAllUsers();
        for (User user: userList){
            users.add(user);
        }
        tblUsers.setItems(users);
    }


    public void handleUserTabButtons(ActionEvent event){
        if (event.getSource() == btnAddUser){
            showNewScene("userEdit");
        }
    }


    public void showNewScene(String fxml){
        try{
            Parent parent = FXMLLoader.load(getClass().getResource(fxml+".fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
