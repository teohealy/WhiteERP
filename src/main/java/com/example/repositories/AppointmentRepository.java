package com.example.repositories;

import com.example.entities.Appointment;
import com.example.entities.Client;

import java.sql.SQLException;
import java.util.List;

public interface AppointmentRepository {
    List<Appointment> getAllAppointments();

    void addAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;

    void updateAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;
    void deleteAppointment(Appointment appointment) throws SQLException, ClassNotFoundException;

    List<Appointment> getAppointmentByDate(int dailyScheduleId) throws SQLException, ClassNotFoundException;
}
