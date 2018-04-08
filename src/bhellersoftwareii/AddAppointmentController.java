/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import bhellersoftwareii.CustomerAppointment;
import bhellersoftwareii.CustomerSearchController;
import bhellersoftwareii.HomeController;
import bhellersoftwareii.BusinessHoursException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class AddAppointmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    int apptID = 0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        NewApptCustIDEntry = new TextField();
        String customerID = CustomerSearchController.custID;
        System.out.println("Customer ID is " + customerID);
        NewApptCustIDEntry.setText(customerID);
    }    
    
    
    @FXML
    private TextField NewApptApptIDEntry;
    
    @FXML
    Label custIDLabel;

    @FXML
    TextField NewApptCustIDEntry;

    @FXML
    private TextField NewApptTitleTextEntry;

    @FXML
    private TextArea NewApptDescTextEntry;

    @FXML
    private TextField NewApptLocTextEntry;

    @FXML
    private TextField NewApptContactTextEntry;

    @FXML
    private TextField NewApptURLTextEntry;

    @FXML
    private DatePicker NewApptDatePicker;

    @FXML
    private TextField NewApptStartTimeEntry;

    @FXML
    private TextField NewApptEndTimeEntry;

    @FXML
    private Button NewApptCancelButton;

    @FXML
    private Button NewApptSaveButton;

    @FXML
    void NewApptCancelButtonPressed(ActionEvent event) throws IOException {
        //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Appointment will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        //Close the window
        confirm.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) NewApptCancelButton.getScene().getWindow();
                stage.close();
            }
            }));
    }

    @FXML
    void NewApptSaveButtonPressed(ActionEvent event) throws ParseException, SQLException, BusinessHoursException {
        //Check for exceptions
        if(NewApptCustIDEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Customer ID field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(NewApptTitleTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Title field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(NewApptDescTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Description field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(NewApptLocTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Location field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(NewApptContactTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Contact field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(NewApptURLTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment URL field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(NewApptDatePicker.getValue().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Date field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(NewApptStartTimeEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment start field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(NewApptEndTimeEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment end field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        } else {
        //Collect New Appointment information
        String customerID = NewApptCustIDEntry.getText();
        int custId = Integer.parseInt(customerID);
        String apptTitle = NewApptTitleTextEntry.getText();
        String apptDesc = NewApptDescTextEntry.getText();
        String apptLoc = NewApptLocTextEntry.getText();
        String apptContact = NewApptContactTextEntry.getText();
        String apptURL = NewApptURLTextEntry.getText();
        LocalDateTime startLocal = getAppointmentStartTime();
        LocalDateTime startingTime = getAppointmentStartTimeUTC();
        LocalDateTime endLocal = getAppointmentEndTime();
        java.sql.Timestamp startTime = java.sql.Timestamp.valueOf(startingTime);
        LocalDateTime endingTime = getAppointmentEndTimeUTC();
        java.sql.Timestamp endTime = java.sql.Timestamp.valueOf(endingTime);
        try{
        if(startLocal.getHour() < 8){
            throw new BusinessHoursException("Start time must be within business hours (8AM to 6PM");
        }
        if(startLocal.getHour() >= 18){
            throw new BusinessHoursException("Start time must be within business hours (8AM to 6PM");
        }
        
        if(endLocal.getHour() < 8){
            throw new BusinessHoursException("Start time must be within business hours (8AM to 6PM");
        }
        if(endLocal.getHour() >= 18){
            throw new BusinessHoursException("Start time must be within business hours (8AM to 6PM");
        }
        } catch(BusinessHoursException e) {
            e.printStackTrace();
            return;
        }
        try{
        for(CustomerAppointment a : CustomerAppointment.appointmentList) {
            LocalDateTime aStart = getDateTimeFromString(a.getStartTime());
            LocalDateTime aEnd = getDateTimeFromString(a.getEndTime());
            if(startLocal.isAfter(aStart) && startLocal.isBefore(aEnd)) {
                throw new OverlappingAppointmentException("Appointment times cannot overlap");
            }
            if(endLocal.isAfter(aStart) && endLocal.isBefore(aEnd)) {
                throw new OverlappingAppointmentException("Appointment times cannot overlap");
            }
        }
        } catch(OverlappingAppointmentException e) {
            e.printStackTrace();
            return;
        }
        
        
        java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String person = HomeController.currentUser;
        
        //Save new appointment to the database
        if(addAppointment(custId, apptTitle, apptDesc, apptLoc, apptContact, apptURL, startTime, endTime, person, today)) {
                //Close the current stage
                CustomerAppointment appointmentToAdd = new CustomerAppointment(apptID, custId, apptTitle, apptDesc, 
                        apptLoc, apptContact, apptURL, startTime, endTime, person);
                CustomerAppointment.appointmentList.add(appointmentToAdd);
                Stage stage = (Stage) NewApptSaveButton.getScene().getWindow();
                stage.close();
            } else {
                Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
                appointmentSaveAlert.setTitle("Invalid Action");
                appointmentSaveAlert.setHeaderText("There was a problem");
                appointmentSaveAlert.setContentText("Appointment information was not saved");

                //Close the window
                appointmentSaveAlert.showAndWait().ifPresent((response -> {
                    if (response == ButtonType.OK) {
                    }
                    }));
            } 
    }
    }
    
    
        private boolean addAppointment (int customerID, String appointmentTitle, String appointmentDescription, 
        String appointmentLocation, String appointmentContact, String appointmentURL, java.sql.Timestamp appointmentStartTime, java.sql.Timestamp appointmentEndTime, 
        String person, java.sql.Date today) throws SQLException{
        try{
            Class.forName("com.mysql.jdbc.Driver");

            String url = "jdbc:mysql://52.206.157.109/U04vDR";
            String user = "U04vDR";
            String pass = "53688357932";
            Connection conn = DriverManager.getConnection(url, user, pass);

            try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO appointment (customerId, title, description, location, contact, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                        stmt.setInt(1, customerID);
                        stmt.setString(2, appointmentTitle);
                        stmt.setString(3, appointmentDescription);
                        stmt.setString(4, appointmentLocation);
                        stmt.setString(5, appointmentContact);
                        stmt.setString(6, appointmentURL);
                        stmt.setTimestamp(7, appointmentStartTime);
                        stmt.setTimestamp(8, appointmentEndTime);
                        stmt.setDate(9, today);
                        stmt.setString(10, person);
                        stmt.setDate(11, today);
                        stmt.setString(12, person);
                        stmt.executeUpdate();
                        ResultSet rs = stmt.getGeneratedKeys();
                        if(rs.next()) {
                            int appointmentID = rs.getInt(1);
                            System.out.println("New appointment ID is: " + appointmentID);
                            apptID = appointmentID;
                        }
            } catch(Exception e){
                e.printStackTrace();
                return false;

            }
        } catch(Exception e){
                e.printStackTrace();
                return false;

            }
        return true;
        }
    
    LocalDateTime getAppointmentStartTimeUTC() throws ParseException {
        LocalDate localDate = NewApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time start = new Time(formatter.parse(NewApptStartTimeEntry.getText()).getTime());
        LocalTime startingTime = start.toLocalTime();
        ZoneId tz = ZoneId.systemDefault();
        TimeZone tzname = TimeZone.getDefault();
        ZonedDateTime startTimeAndZone = ZonedDateTime.of(localDate, startingTime, tz);
        ZonedDateTime startTimeAndZoneUTC = startTimeAndZone.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime startDateTimeLocal = LocalDateTime.ofInstant(startTimeAndZoneUTC.toInstant(), ZoneId.of("UTC"));
        
        return startDateTimeLocal;
    }
    
    LocalDateTime getAppointmentStartTime() throws ParseException {
        LocalDate localDate = NewApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time start = new Time(formatter.parse(NewApptStartTimeEntry.getText()).getTime());
        LocalTime startingTime = start.toLocalTime();
        LocalDateTime startTimeReturn = LocalDateTime.of(localDate, startingTime);
        
        return startTimeReturn;
    }
    
    LocalDateTime getAppointmentEndTimeUTC() throws ParseException {
        LocalDate localDate = NewApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time end = new Time(formatter.parse(NewApptEndTimeEntry.getText()).getTime());
        LocalTime endingTime = end.toLocalTime();
        ZoneId tz = ZoneId.systemDefault();
        ZonedDateTime endTimeAndZone = ZonedDateTime.of(localDate, endingTime, tz);
        ZonedDateTime endTimeAndZoneUTC = endTimeAndZone.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime endDateTimeLocal = LocalDateTime.ofInstant(endTimeAndZoneUTC.toInstant(), ZoneId.of("UTC"));
        
        return endDateTimeLocal;
    }
    
        LocalDateTime getAppointmentEndTime() throws ParseException {
        LocalDate localDate = NewApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time end = new Time(formatter.parse(NewApptEndTimeEntry.getText()).getTime());
        LocalTime endingTime = end.toLocalTime();
        LocalDateTime endTimeReturn = LocalDateTime.of(localDate, endingTime);
        
        return endTimeReturn;
    }
        
    public LocalDateTime getDateTimeFromString(String date) throws ParseException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M-d-yyyy hh:mm a");
        LocalDateTime dateToReturn = LocalDateTime.parse(date, format);
        return dateToReturn;
    }

    
}

