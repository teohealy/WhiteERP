package com.example.whiteerp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;


public class MainAppController {

    @FXML
    private Button
            btnClients, btnDocuments, btnMedicine, btnSalary, btnSchedule, btnStock, btnUsers;


    @FXML
    private Label lbStatus;


    @FXML
    private Tab tabClients, tabUsers, tabSchedule;

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
            tabSchedule.setClosable(true);
            tabSchedule.getTabPane().getSelectionModel().select(tabSchedule);
            lbStatus.setText("Расписание");
        }
    }


    @FXML
    private void handleTabs(){
        tabClients.setOnSelectionChanged(ActionEvent -> lbStatus.setText("Клиенты"));
        tabUsers.setOnSelectionChanged(event -> lbStatus.setText("Сотрудники"));
        tabSchedule.setOnSelectionChanged(event -> lbStatus.setText("Расписание"));
    }

}
