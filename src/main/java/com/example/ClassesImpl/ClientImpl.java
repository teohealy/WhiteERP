package com.example.ClassesImpl;

import com.example.database.DataBaseHandler;
import com.example.entities.Client;
import com.example.repositories.ClientRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientImpl implements ClientRepository {

    private DataBaseHandler dbHandler = new DataBaseHandler();
    private Connection connection = null;
    private static final String TBL_CLIENTS = "clients";
    @Override
    public void addClient(Client client) {

    }

    @Override
    public void updateClient(Client client) {

    }

    @Override
    public void deleteClient(Client client) {

    }

    @Override
    public List<Client> getAllClients() throws SQLException, ClassNotFoundException {
        List<Client> clientList = new ArrayList<>();
        String query = "SELECT * FROM " + TBL_CLIENTS;
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Client client = new Client();
            client.setId(resultSet.getInt(1));
            client.setFirstName(resultSet.getString(2));
            client.setLastName(resultSet.getString(3));
            client.setDateBirth(resultSet.getDate(4));
            client.setNumber(resultSet.getString(5));
            clientList.add(client);
        }
        return clientList;
    }
}
