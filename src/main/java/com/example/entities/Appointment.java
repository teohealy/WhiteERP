package com.example.entities;

import java.sql.Time;

public class Appointment {
    private int id;
    private Time startTime;
    private Time endTime;

    private User user = new User();
    private Client client = new Client();
    private DailySchedule dailySchedule = new DailySchedule();
    private Room room = new Room();

    public Appointment(Time startTime, Time endTime, User user, Client client, DailySchedule dailySchedule, Room room){
        this.startTime = startTime;
        this.endTime = endTime;
        this.user = user;
        this.client = client;
        this.dailySchedule = dailySchedule;
        this.room = room;
    }

    public Appointment(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Time getStartTime() {
        return startTime;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public Time getEndTime() {
        return endTime;
    }

    public void setEndTime(Time endTime) {
        this.endTime = endTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public DailySchedule getDailySchedule() {
        return dailySchedule;
    }

    public void setDailySchedule(DailySchedule dailySchedule) {
        this.dailySchedule = dailySchedule;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
