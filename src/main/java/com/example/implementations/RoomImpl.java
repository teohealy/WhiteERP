package com.example.implementations;

import com.example.database.DataBaseHandler;
import com.example.entities.Room;
import com.example.repositories.RoomRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomImpl implements RoomRepository {

    private static final String TBL_ROOMS = "rooms";
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private Connection connection = null;
    @Override
    public List<Room> getAllRooms() throws SQLException, ClassNotFoundException {
        List<Room> roomList = new ArrayList<>();
        String query = "SELECT * FROM " + TBL_ROOMS;
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int roomId = resultSet.getInt(1);
            int  roomNumber = resultSet.getInt(2);
            Room room = new Room(roomNumber);
            room.setId(roomId);
            roomList.add(room);
        }
        return roomList;
    }

    @Override
    public Room getRoomById(int roomId) throws SQLException, ClassNotFoundException {
        Room room = null;
        String query = "SELECT * FROM " + TBL_ROOMS + " WHERE room_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, roomId);

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int roomNumber = resultSet.getInt(2);
            room= new Room(roomNumber);
            room.setId(resultSet.getInt(1));
        }
        return room;
    }

}
