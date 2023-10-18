package com.example.entities;

public class Room {
    private int id;
    private int roomNumber;

    public Room(int roomNumber){
        this.roomNumber = roomNumber;
    }

    public Room(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString() {
        return Integer.toString(roomNumber);
    }
}
