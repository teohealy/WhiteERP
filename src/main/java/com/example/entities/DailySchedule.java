package com.example.entities;

import java.util.Date;
import java.util.List;

public class DailySchedule {
    private int id;
    private Date dailyScheduleDate;
    private List<Appointment> appointmentList;

    public DailySchedule(Date dailyScheduleDate){
        this.dailyScheduleDate = dailyScheduleDate;
    }

    public DailySchedule(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDailyScheduleDate() {
        return dailyScheduleDate;
    }

    public void setDailyScheduleDate(Date dailyScheduleDate) {
        this.dailyScheduleDate = dailyScheduleDate;
    }

    public List<Appointment> getAppointmentList() {
        return appointmentList;
    }

    public void setAppointmentList(List<Appointment> appointmentList) {
        this.appointmentList = appointmentList;
    }
}
