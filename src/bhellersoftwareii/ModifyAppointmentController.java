/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class ModifyAppointmentController implements Initializable {

    String clickedButton = HomeController.getClickedButtonID();
   CustomerAppointment thisAppointment = new CustomerAppointment().getAppointmentDetails(clickedButton);
   String appointmentID = "";
   String customerID = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        thisAppointment = CustomerAppointment.getAppointmentDetails(clickedButton);
        appointmentID = Integer.toString(thisAppointment.getAppointmentID());
        customerID = Integer.toString(thisAppointment.getAppointmentCustomerID());
        ModApptApptIDEntry.setText(appointmentID);
        ModApptCustIDEntry.setText(customerID);
        ModApptTitleTextEntry.setText(thisAppointment.getAppointmentTitle());
        ModApptDescTextEntry.setText(thisAppointment.getAppointmentDescription());
        ModApptLocTextEntry.setText(thisAppointment.getAppointmentLocation());
        ModApptContactTextEntry.setText(thisAppointment.getAppointmentContact());
        ModApptURLTextEntry.setText(thisAppointment.getAppointmentURL());
        try {
            ModApptDatePicker.setValue(getDateFromString(thisAppointment.getStartTime()));
        } catch (ParseException ex) {
            Logger.getLogger(ModifyAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModApptStartTimeEntry.setText(getTimeFromString(thisAppointment.getStartTime()));
        ModApptEndTimeEntry.setText(getTimeFromString(thisAppointment.getEndTime()));
    }    
    
    @FXML
    private TextField ModApptApptIDEntry;

    @FXML
    private TextField ModApptCustIDEntry;

    @FXML
    private DatePicker ModApptDatePicker;

    @FXML
    private TextField ModApptStartTimeEntry;

    @FXML
    private TextField ModApptEndTimeEntry;

    @FXML
    private TextField ModApptTitleTextEntry;

    @FXML
    private TextArea ModApptDescTextEntry;

    @FXML
    private TextField ModApptLocTextEntry;

    @FXML
    private TextField ModApptContactTextEntry;

    @FXML
    private TextField ModApptURLTextEntry;

    @FXML
    private Button ModifyApptCancelButton;

    @FXML
    private Button ModifyApptSaveButton;

    @FXML
    void ModifyApptCancelButtonPressed(ActionEvent event) {
         //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Appointment will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        //Close the window
        confirm.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) ModifyApptCancelButton.getScene().getWindow();
                stage.close();
            }
            }));

    }

    @FXML
    public void ModifyApptSaveButtonPressed(ActionEvent event) throws ParseException, IOException {
        //Check for exceptions
        if(ModApptCustIDEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Customer ID field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(ModApptTitleTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Title field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(ModApptDescTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Description field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(ModApptLocTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Location field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(ModApptContactTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Contact field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(ModApptURLTextEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment URL field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(ModApptDatePicker.getValue().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment Date field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(ModApptStartTimeEntry.getText().equals("")){
            Alert appointmentSaveAlert = new Alert(Alert.AlertType.WARNING);
            appointmentSaveAlert.setTitle("Invalid Action");
            appointmentSaveAlert.setHeaderText("There was a problem");
            appointmentSaveAlert.setContentText("Appointment start field cannot be empty");

            //Close the window
            appointmentSaveAlert.showAndWait().ifPresent((response -> {
                if (response == ButtonType.OK) {
                }
                })); 
        }else if(ModApptEndTimeEntry.getText().equals("")){
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


        //Collect Updated Appointment information
        int appointmentID = Integer.parseInt(ModApptApptIDEntry.getText());
        String custID = ModApptCustIDEntry.getText();
        int customerID = Integer.parseInt(custID);
        String apptTitle = ModApptTitleTextEntry.getText();
        String apptDescription = ModApptDescTextEntry.getText();
        String apptLocation = ModApptLocTextEntry.getText();
        String apptContact = ModApptContactTextEntry.getText();
        String apptURL = ModApptURLTextEntry.getText();
        LocalDateTime startingTime = getAppointmentStartTimeUTC();
        java.sql.Timestamp startTime = java.sql.Timestamp.valueOf(startingTime);
        LocalDateTime startLocal = getAppointmentStartTime();
        LocalDateTime endingTime = getAppointmentEndTimeUTC();
        java.sql.Timestamp endTime = java.sql.Timestamp.valueOf(endingTime);
        LocalDateTime endLocal = getAppointmentEndTime();
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

        //Save new customer information to the database
        if(updateAppointment(appointmentID, customerID, startTime, endTime, apptTitle, apptDescription, apptLocation, 
        apptContact, apptURL, today, person)) {
                //Close the current stage
                Stage stage = (Stage) ModifyApptSaveButton.getScene().getWindow();
                stage.close();
                
            } else {
                Alert invalidCredentialsAlert = new Alert(Alert.AlertType.WARNING);
                invalidCredentialsAlert.setTitle("Invalid Action");
                invalidCredentialsAlert.setHeaderText("There was a problem");
                invalidCredentialsAlert.setContentText("Customer information was not saved");

                invalidCredentialsAlert.showAndWait();
            } 
            
    }
    }
    private boolean updateAppointment(int apptID, int custID, java.sql.Timestamp startTime, java.sql.Timestamp endTime, String apptTitle, String apptDescription, String apptLocation, 
        String apptContact, String apptURL, java.sql.Date date, String person){
        try{
                    Class.forName("com.mysql.jdbc.Driver");

                    String url = "jdbc:mysql://52.206.157.109/U04vDR";
                    String user = "U04vDR";
                    String pass = "53688357932";
                    Connection conn = DriverManager.getConnection(url, user, pass);
                    if(conn != null) {
                        System.out.println("Connected to the database!");
                        //Query if country exists or not
                        try{
                            PreparedStatement apptUpdateQuery = conn.prepareStatement("UPDATE appointment SET customerId = ?, title = ?, description = ?, location = ?, contact = ?, url = ?, start = ?, end = ?, lastUpdate = ?, lastUpdateBy = ? WHERE appointmentId = ?");
                            apptUpdateQuery.setInt(1, custID);
                            apptUpdateQuery.setString(2, apptTitle);
                            apptUpdateQuery.setString(3, apptDescription);
                            apptUpdateQuery.setString(4, apptLocation);
                            apptUpdateQuery.setString(5, apptContact);
                            apptUpdateQuery.setString(6, apptURL);
                            apptUpdateQuery.setTimestamp(7, startTime);
                            apptUpdateQuery.setTimestamp(8, endTime);
                            apptUpdateQuery.setDate(9, date);
                            apptUpdateQuery.setString(10, person);
                            apptUpdateQuery.setInt(11, apptID);
                            apptUpdateQuery.executeUpdate();
                            System.out.println("Appointment at ID " + apptID + " has been updated!");
                            apptUpdateQuery.close();
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    
    public LocalDate getDateFromString(String date) throws ParseException {
//        LocalDate dateToReturn = null;
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M-d-yyyy hh:mm a");
        LocalDate dateToReturn = LocalDate.parse(date, format);
        return dateToReturn;
    }
    
    public String getTimeFromString(String time) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("M-d-yyyy hh:mm a");
        LocalTime timeToReturn = LocalTime.parse(time, format);
        String timeInString = timeToReturn.toString();
        return timeInString; 
    }
    
    LocalDateTime getAppointmentStartTimeUTC() throws ParseException {
        LocalDate localDate = ModApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time start = new Time(formatter.parse(ModApptStartTimeEntry.getText()).getTime());
        LocalTime startingTime = start.toLocalTime();
        ZoneId tz = ZoneId.systemDefault();
        TimeZone tzname = TimeZone.getDefault();
        ZonedDateTime startTimeAndZone = ZonedDateTime.of(localDate, startingTime, tz);
        ZonedDateTime startTimeAndZoneUTC = startTimeAndZone.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime startDateTimeLocal = LocalDateTime.ofInstant(startTimeAndZoneUTC.toInstant(), ZoneId.of("UTC"));
        
        return startDateTimeLocal;
    }
    
    LocalDateTime getAppointmentStartTime() throws ParseException {
        LocalDate localDate = ModApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time start = new Time(formatter.parse(ModApptStartTimeEntry.getText()).getTime());
        LocalTime startingTime = start.toLocalTime();
        LocalDateTime startTimeReturn = LocalDateTime.of(localDate, startingTime);
        
        return startTimeReturn;
    }
    
    LocalDateTime getAppointmentEndTimeUTC() throws ParseException {
        LocalDate localDate = ModApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time end = new Time(formatter.parse(ModApptEndTimeEntry.getText()).getTime());
        LocalTime endingTime = end.toLocalTime();
        ZoneId tz = ZoneId.systemDefault();
        ZonedDateTime endTimeAndZone = ZonedDateTime.of(localDate, endingTime, tz);
        ZonedDateTime endTimeAndZoneUTC = endTimeAndZone.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime endDateTimeLocal = LocalDateTime.ofInstant(endTimeAndZoneUTC.toInstant(), ZoneId.of("UTC"));
        
        return endDateTimeLocal;
    }
    
        LocalDateTime getAppointmentEndTime() throws ParseException {
        LocalDate localDate = ModApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time end = new Time(formatter.parse(ModApptEndTimeEntry.getText()).getTime());
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
