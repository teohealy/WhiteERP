package com.example.whiteerp;

import com.example.ClassesImpl.ClientImpl;
import com.example.entities.Client;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {
        showClients();

        tblClients.setOnMouseClicked(event -> {
            Client selectedClient = tblClients.getSelectionModel().getSelectedItem();
            if (selectedClient != null) {
                tfFirstName.setText(selectedClient.getFirstName());
                tfLastName.setText(selectedClient.getLastName());

                String dateBirthString =dateFormat.format(selectedClient.getDateBirth());
                tfDateBirth.setText(dateBirthString);

                tfNumber.setText(selectedClient.getNumber());
            }
        });
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

    @FXML
    void handleClientsTabButtons(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (event.getSource() == btnAddClient){
            addClient();
            clearFields();
            loadClients();
        }
        if (event.getSource() == btnDeleteClient){
            deleteClient();
            loadClients();
            clearFields();
        }
        if (event.getSource() == btnUpdateClient){
            updateClient();
            loadClients();
        }
        if (event.getSource() == btnClear){
            clearFields();
        }
    }

    public void addClient() throws SQLException, ClassNotFoundException {
        String firstName = tfFirstName.getText();
        String lastName = tfLastName.getText();
        String dateBirthString = tfDateBirth.getText();
        String number = tfNumber.getText();


        Date dateBirth = null;
        try {
            dateBirth = dateFormat.parse(dateBirthString);
        }catch (ParseException e){
            e.printStackTrace();
            return;
        }
        Client client = new Client(firstName, lastName, dateBirth, number);

        clientImpl.addClient(client);
    }

    public void updateClient() throws SQLException, ClassNotFoundException {
        Client client = tblClients.getSelectionModel().getSelectedItem();

        if (client != null){
            client.setFirstName(tfFirstName.getText());
            client.setLastName(tfLastName.getText());

            String dateBirthString = tfDateBirth.getText();


            Date dateBirth = null;
            try {
                dateBirth = dateFormat.parse(dateBirthString);
            }catch (ParseException e){
                e.printStackTrace();
                return;
            }
            client.setDateBirth(dateBirth);
            client.setNumber(tfNumber.getText());
            clientImpl.updateClient(client);
        }
    }

    private void deleteClient() throws SQLException, ClassNotFoundException {
        Client client = tblClients.getSelectionModel().getSelectedItem();

        if (client != null){
            clientImpl.deleteClient(client);
        }
    }

    public void clearFields(){
        tfFirstName.clear();
        tfLastName.clear();
        tfDateBirth.clear();
        tfNumber.clear();
    }
}
