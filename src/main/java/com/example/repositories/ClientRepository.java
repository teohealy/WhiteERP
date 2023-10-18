package com.example.repositories;

import com.example.entities.Client;

import java.sql.SQLException;
import java.util.List;


public interface ClientRepository {

    void addClient(Client client) throws SQLException, ClassNotFoundException;
    void updateClient(Client client) throws SQLException, ClassNotFoundException;
    void deleteClient(Client client) throws SQLException, ClassNotFoundException;
    List<Client> getAllClients() throws SQLException, ClassNotFoundException;

    Client getClientById(int clientId) throws SQLException, ClassNotFoundException;
}
