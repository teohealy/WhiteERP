package com.example.repositories;

import com.example.entities.Client;

import java.sql.SQLException;
import java.util.List;


public interface ClientRepository {

    void addClient(Client client);
    void updateClient(Client client);
    void deleteClient(Client client);
    List<Client> getAllClients() throws SQLException, ClassNotFoundException;
}
