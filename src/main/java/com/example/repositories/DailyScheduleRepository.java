package com.example.repositories;

import com.example.entities.DailySchedule;

import java.sql.SQLException;
import java.util.Date;

public interface DailyScheduleRepository {
    DailySchedule getDailyScheduleByDate(Date dailyScheduleDate) throws SQLException, ClassNotFoundException;
}
