package com.example.repositories;

import com.example.entities.Post;
import com.example.entities.Room;

import java.sql.SQLException;
import java.util.List;

public interface RoomRepository {
    List<Room> getAllRooms() throws SQLException, ClassNotFoundException;
    Room getRoomById(int id) throws SQLException, ClassNotFoundException;
}
