package com.example.whiteerp;

import com.example.ClassesImpl.ClientImpl;
import com.example.entities.Client;
import com.example.entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ClientsTabController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button
            btnAddClient, btnClear, btnDeleteClient, btnUpdateClient;

    @FXML
    private TableView<Client> tblClients;

    @FXML
    private TableColumn<Client, Date> tcClientDateBirth;

    @FXML
    private TableColumn<Client, String>
            tcClientLastName, tcClientFirstName, tcClientPhone;

    @FXML
    private TextField
            tfFirstName, tfLastName, tfNumber, tfDateBirth;

    private ClientImpl clientImpl = new ClientImpl();

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        showClients();
    }


    public void loadClients() throws SQLException, ClassNotFoundException {
        ObservableList<Client> clients = FXCollections.observableArrayList();
        List<Client> clientList = clientImpl.getAllClients();
        for (Client clent: clientList){
            clients.add(clent);
        }
        tblClients.setItems(clients);
    }

    public void showClients() throws SQLException, ClassNotFoundException {
        tcClientFirstName.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        tcClientLastName.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        tcClientDateBirth.setCellValueFactory(new PropertyValueFactory<>("DateBirth"));
        tcClientPhone.setCellValueFactory(new PropertyValueFactory<>("Number"));

        loadClients();
    }
}
