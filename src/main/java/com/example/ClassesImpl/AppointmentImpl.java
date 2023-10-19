package com.example.ClassesImpl;

import com.example.database.DataBaseHandler;
import com.example.entities.Appointment;
import com.example.repositories.AppointmentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AppointmentImpl implements AppointmentRepository {

    private static final String TBL_APPOINTMENTS = "appointments";
    private DataBaseHandler dbHandler = new DataBaseHandler();
    private Connection connection = null;

    @Override
    public List<Appointment> getAllAppointments() {
        return null;
    }

    @Override
    public void addAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO " + TBL_APPOINTMENTS + "(appointment_start_time, appointment_end_time, user_id, client_id, daily_schedule_id, room_id)" +
                " VALUES (?, ?, ?, ?, ?, ?)";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTime(1, appointment.getStartTime());
        preparedStatement.setTime(2, appointment.getEndTime());
        preparedStatement.setInt(3, appointment.getUser().getId());
        preparedStatement.setInt(4, appointment.getClient().getClientId());
        preparedStatement.setInt(5, appointment.getDailySchedule().getId());
        preparedStatement.setInt(6, appointment.getRoom().getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        String query = "UPDATE " + TBL_APPOINTMENTS + " SET appointment_start_time = ?, appointment_end_time = ?, user_id = ?, " +
                "client_id = ?, daily_schedule_id = ?, room_id = ? WHERE appointment_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setTime(1, appointment.getStartTime());
        preparedStatement.setTime(2, appointment.getEndTime());
        preparedStatement.setInt(3, appointment.getUser().getId());
        preparedStatement.setInt(4, appointment.getClient().getClientId());
        preparedStatement.setInt(5, appointment.getDailySchedule().getId());
        preparedStatement.setInt(6, appointment.getRoom().getId());
        preparedStatement.setInt(7, appointment.getId());

        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM " + TBL_APPOINTMENTS + " WHERE appointment_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, appointment.getId());
        preparedStatement.executeUpdate();
    }

    @Override
    public List<Appointment> getAppointmentByDate(int dailyScheduleId) throws SQLException, ClassNotFoundException {
        UserImpl userImpl = new UserImpl();
        ClientImpl clientImpl = new ClientImpl();
        RoomImpl roomImpl = new RoomImpl();
        List<Appointment> appointmentList = new ArrayList<>();
        String query = "SELECT * FROM " + TBL_APPOINTMENTS + " WHERE daily_schedule_id = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, dailyScheduleId);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Appointment appointment = new Appointment();
            int id = resultSet.getInt(1);
            Time startTime = resultSet.getTime(2);
            Time endTime = resultSet.getTime(3);
            int userId = resultSet.getInt(4);
            int clientId = resultSet.getInt(5);
            int roomId = resultSet.getInt(7);

            appointment.setId(id);
            appointment.setStartTime(startTime);
            appointment.setEndTime(endTime);
            appointment.setUser(userImpl.getUserById(userId));
            appointment.setClient(clientImpl.getClientById(clientId));
            appointment.setRoom(roomImpl.getRoomById(roomId));
            appointmentList.add(appointment);
        }
        return appointmentList;
    }
}
