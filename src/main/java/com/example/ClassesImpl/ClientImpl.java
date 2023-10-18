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
    public void addClient(Client client) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO " + TBL_CLIENTS + " (client_first_name, client_last_name, client_datebirth, client_number) VALUES (?, ?, ?, ?)";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setDate(3, new java.sql.Date(client.getDateBirth().getTime()));
        preparedStatement.setString(4, client.getNumber());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateClient(Client client) throws SQLException, ClassNotFoundException {
        String query = "UPDATE " +TBL_CLIENTS + " SET client_first_name = ?, client_last_name = ?, client_datebirth = ?, client_number = ? WHERE client_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setString(1, client.getFirstName());
        preparedStatement.setString(2, client.getLastName());
        preparedStatement.setDate(3, new java.sql.Date(client.getDateBirth().getTime()));
        preparedStatement.setString(4, client.getNumber());
        preparedStatement.setInt(5, client.getClientId());

        preparedStatement.executeUpdate();

    }

    @Override
    public void deleteClient(Client client) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + TBL_CLIENTS + " WHERE client_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, client.getClientId());
        preparedStatement.executeUpdate();
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

    @Override
    public Client getClientById(int clientId) throws SQLException, ClassNotFoundException {
        Client client = new Client();
        String query = "SELECT * FROM " + TBL_CLIENTS + " WHERE client_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        preparedStatement.setInt(1, clientId);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            client.setFirstName(resultSet.getString(2));
            client.setLastName(resultSet.getString(3));
            client.setDateBirth(resultSet.getDate(4));
            client.setNumber(resultSet.getString(5));
        }
        return client;
    }


}
