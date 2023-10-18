package com.example.ClassesImpl;

import com.example.database.DataBaseHandler;
import com.example.entities.DailySchedule;
import com.example.repositories.DailyScheduleRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DailyScheduleImpl implements DailyScheduleRepository {

    private DataBaseHandler dbHandler = new DataBaseHandler();
    private Connection connection = null;
    private static final String TBL_DAILY_SCHEDULES = "daily_schedules";
    @Override
    public DailySchedule getDailyScheduleByDate(Date dailyScheduleDate) throws SQLException, ClassNotFoundException {
        DailySchedule dailySchedule = new DailySchedule();
        String query = "SELECT * FROM " + TBL_DAILY_SCHEDULES + " WHERE daily_schedule_date = ?";
        connection = dbHandler.getDbConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, new java.sql.Date (dailyScheduleDate.getTime()));
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            dailySchedule.setId(resultSet.getInt(1));
            dailySchedule.setDailyScheduleDate(dailyScheduleDate);
        }
        return dailySchedule;
    }
}
