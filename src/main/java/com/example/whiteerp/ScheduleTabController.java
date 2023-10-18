package com.example.whiteerp;


import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.example.ClassesImpl.*;
import com.example.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class ScheduleTabController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAddAppointment, btnUpdateAppointment, btnDeleteAppointment, btnClear;

    @FXML
    private Button btnSearch;

    @FXML
    private ComboBox<Client> cbClient;

    @FXML
    private ComboBox<Room> cbRoom;

    @FXML
    private ComboBox<User> cbUser;

    @FXML
    private TableView<Appointment> tblAppointments;

    @FXML
    private TableColumn<Appointment, String> tcClient;

    @FXML
    private TableColumn<Appointment, Time> tcEndTime;

    @FXML
    private TableColumn<Appointment, Integer> tcRoom;

    @FXML
    private TableColumn<Appointment, Time> tcStartTime;

    @FXML
    private TableColumn<Appointment, String> tcUser;

    @FXML
    private TextField tfDate;

    @FXML
    private TextField tfEndTime;

    @FXML
    private TextField tfStartTime;

    RoomImpl roomImpl = new RoomImpl();
    UserImpl userImpl = new UserImpl();
    ClientImpl clientImpl = new ClientImpl();

    DailyScheduleImpl dailyScheduleImpl = new DailyScheduleImpl();

    AppointmentImpl appointmentImpl = new AppointmentImpl();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    void initialize() throws SQLException, ClassNotFoundException {

    }


    public void fillComboBoxRoom() throws SQLException, ClassNotFoundException {
        List<Room> roomList = roomImpl.getAllRooms();

        cbRoom.setItems(FXCollections.observableArrayList(roomList));
    }


    public void fillComboBoxUser() throws SQLException, ClassNotFoundException {
        List<User> userList = userImpl.getAllUsers();

        cbUser.setItems(FXCollections.observableArrayList(userList));
    }

    public void fillComboBoxClient() throws SQLException, ClassNotFoundException {
        List<Client> clientList = clientImpl.getAllClients();

        cbClient.setItems(FXCollections.observableArrayList(clientList));
    }

    @FXML
    void handleScheduleTabButtons(ActionEvent event) throws SQLException, ClassNotFoundException {
        if (event.getSource() == btnSearch){
            searchAppointments();
        }

    }


    public void searchAppointments() throws SQLException, ClassNotFoundException {
        tcStartTime.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        tcEndTime.setCellValueFactory(new PropertyValueFactory<>("EndTime"));
        tcUser.setCellValueFactory(new PropertyValueFactory<>("User"));
        tcClient.setCellValueFactory(new PropertyValueFactory<>("Client"));
        tcRoom.setCellValueFactory(new PropertyValueFactory<>("Room"));

        String dateString = tfDate.getText();
        Date dailyScheduleDate = null;
        try {
            dailyScheduleDate = dateFormat.parse(dateString);
        }catch (ParseException e){
            e.printStackTrace();
        }
        DailySchedule dailySchedule = dailyScheduleImpl.getDailyScheduleByDate(dailyScheduleDate);

        dailySchedule.setAppointmentList(appointmentImpl.getAppointmentByDate(dailySchedule.getId()));
        List<Appointment> appointmentList = dailySchedule.getAppointmentList();

        ObservableList<Appointment> appointments = FXCollections.observableArrayList();

        for(Appointment appointment: appointmentList){
            appointments.add(appointment);
        }
        tblAppointments.setItems(appointments);

    }
}
