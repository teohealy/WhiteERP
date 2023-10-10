package com.example.whiteerp;

import com.example.entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class MainAppController implements Initializable {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnClients;

    @FXML
    private Button btnDocuments;

    @FXML
    private Button btnMedicine;

    @FXML
    private Button btnSalary;

    @FXML
    private Button btnSchedule;

    @FXML
    private Button btnStock;

    @FXML
    private Button btnUsers;

    @FXML
    private Label lbStatus;


    @FXML
    private Tab tabClients;

    @FXML
    private Tab tabUsers;

    @FXML
    private TableView<User> tblUsers;

    @FXML
    private TableColumn<User, String> tcUserGender;

    @FXML
    private TableColumn<User, Integer> tcUserId;

    @FXML
    private TableColumn<User, String> tcUserLastName;

    @FXML
    private TableColumn<User, String> tcUserLogin;

    @FXML
    private TableColumn<User, String> tcUserName;

    @FXML
    private TableColumn<User, String> tcUserPassword;

    @FXML
    private TableColumn<User, String> tcUserPhone;

    @FXML
    private TableColumn<User, String> tcUserPost;

    @FXML
    void initialize() {
        //init
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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




}
