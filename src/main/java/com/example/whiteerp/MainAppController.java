package com.example.whiteerp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

import java.net.URL;
import java.util.ResourceBundle;


public class MainAppController {

    @FXML
    private Button
            btnClients, btnDocuments, btnMedicine, btnSalary, btnSchedule, btnStock, btnUsers;


    @FXML
    private Label lbStatus;


    @FXML
    private Tab tabClients, tabUsers;

    @FXML
    void initialize() {

    }

    @FXML
    private void handleClicks(ActionEvent event) {
        if (event.getSource() == btnUsers) {
            tabUsers.setClosable(true);
            tabUsers.getTabPane().getSelectionModel().select(tabUsers);
            lbStatus.setText("Сотрудники");
        }
        if (event.getSource() == btnClients) {
            tabClients.setClosable(true);
            tabClients.getTabPane().getSelectionModel().select(tabClients);
            lbStatus.setText("Клиенты");
        }
        if (event.getSource() == btnSchedule) {
            lbStatus.setText("Расписание");
        }
        if (event.getSource() == btnStock) {
            lbStatus.setText("Склад");
        }
        if (event.getSource() == btnDocuments) {
            lbStatus.setText("Документы");
        }
        if (event.getSource() == btnMedicine) {
            lbStatus.setText("Медицина");
        }
        if (event.getSource() == btnSalary) {
            lbStatus.setText("Зарплаты");
        }
    }


    @FXML
    private void handleTabs(){
        tabClients.setOnSelectionChanged(ActionEvent -> lbStatus.setText("Клиенты"));
        tabUsers.setOnSelectionChanged(event -> lbStatus.setText("Сотрудники"));
    }

}
