/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

/**
 *
 * @author Britt
 */
public class CustomerAppointment {
    public static ArrayList<CustomerAppointment> appointmentList = new ArrayList<>();
    SimpleIntegerProperty appointmentID = new SimpleIntegerProperty(0);
    SimpleIntegerProperty appointmentCustomerID = new SimpleIntegerProperty(0);
    SimpleStringProperty appointmentTitle = new SimpleStringProperty("");
    SimpleStringProperty appointmentDescription = new SimpleStringProperty("");
    SimpleStringProperty appointmentLocation = new SimpleStringProperty("");
    SimpleStringProperty appointmentContact = new SimpleStringProperty("");
    SimpleStringProperty appointmentURL = new SimpleStringProperty("");
    String appointmentStart;
    String appointmentEnd;
    LocalDateTime appointmentReminderTime;
    SimpleIntegerProperty appointmentSnoozeIncrement = new SimpleIntegerProperty(0);
    SimpleStringProperty appointmentLastUpdatedBy = new SimpleStringProperty("");
    TimeZone tz = TimeZone.getDefault();
    ZoneId tzID = ZoneId.systemDefault();
    ZoneOffset tzOffset = OffsetDateTime.now().getOffset();
    
    private static final String DATE_FORMAT = "M-d-yyyy hh:mm a";
    DateTimeFormatter format = DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(tzID);
    
    public CustomerAppointment(){
        
    }
    
    public CustomerAppointment(int apptID, int apptCustID, String apptTitle, String apptDesc, 
            String apptLoc, String apptContact, String apptURL, java.sql.Timestamp apptStart, 
            java.sql.Timestamp apptEnd, String updatedby) {
        this.setAppointmentID(apptID);
        this.setAppointmentCustomerID(apptCustID);
        this.setAppointmentTitle(apptTitle);
        this.setAppointmentDescription(apptDesc);
        this.setAppointmentLocation(apptLoc);
        this.setAppointmentContact(apptContact);
        this.setAppointmentURL(apptURL);
        this.setStartTime(apptStart);
        this.setEndTime(apptEnd);
        this.setAppointmentLastUpdatedBy(updatedby);
        
    }
    
    public int getAppointmentID(){
        return appointmentID.get();
    }
    
    public void setAppointmentID(int ID){
        appointmentID.set(ID);
    }
    
    int getAppointmentCustomerID(){
        return appointmentCustomerID.get();
    }
    
    public void setAppointmentCustomerID(int ID){
        appointmentCustomerID.set(ID);
    }
    
    public String getAppointmentTitle(){
        return appointmentTitle.get();
    }
    
    public void setAppointmentTitle(String title){
        appointmentTitle.set(title);
    }
    
    public String getAppointmentDescription(){
        return appointmentDescription.get();
    }
    
    public void setAppointmentDescription(String description){
        appointmentDescription.set(description);
    }
    
    public String getAppointmentLocation(){
        return appointmentLocation.get();
    }
    
    public void setAppointmentLocation(String location){
        appointmentLocation.set(location);
    }
    
    public String getAppointmentContact(){
        return appointmentContact.get();
    }
    
    public void setAppointmentContact(String contact){
        appointmentContact.set(contact);
    }
    
    public String getAppointmentURL(){
        return appointmentURL.get();
    }
    
    public void setAppointmentURL(String url){
        appointmentURL.set(url);
    }
    
    public String getStartTime() {
        return appointmentStart;
    }
    
    public void setStartTime(java.sql.Timestamp time){
        //Convert from UTC time zone that is stored in database to local Time Zone
        ZonedDateTime startInUTC = time.toLocalDateTime().atZone(ZoneId.of("UTC"));
        ZonedDateTime startInLocal = startInUTC.withZoneSameInstant(tzID);
        String timeToDisplay = format.format(startInLocal);
        appointmentStart = timeToDisplay;
    }
    
    public String getEndTime() {
        return appointmentEnd;
    }
    
    public void setEndTime(java.sql.Timestamp time){
        ZonedDateTime endInUTC = time.toLocalDateTime().atZone(ZoneId.of("UTC"));
        ZonedDateTime endInLocal = endInUTC.withZoneSameInstant(tzID);
        String timeToDisplay = format.format(endInLocal);
        appointmentEnd = timeToDisplay;
    }
    
    public String getAppointmentLastUpdatedBy(){
        return appointmentLastUpdatedBy.get();
    }
    
    public void setAppointmentLastUpdatedBy(String person){
        appointmentLastUpdatedBy.set(person);
    }
    
    public static void setAllAppointments() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://52.206.157.109/U04vDR";
            String user = "U04vDR";
            String pass = "53688357932";
            Connection conn = DriverManager.getConnection(url, user, pass);
            try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM appointment")) {
                ResultSet rs = stmt.executeQuery();
                while(rs.next()) {
                    CustomerAppointment appointmentToAdd = new CustomerAppointment();
                    int appointmentID = rs.getInt("appointmentId");
                    appointmentToAdd.setAppointmentID(appointmentID);
                    int customerID = rs.getInt("customerId");
                    appointmentToAdd.setAppointmentCustomerID(customerID);
                    String appointmentTitle = rs.getString("title");
                    appointmentToAdd.setAppointmentTitle(appointmentTitle);
                    String appointmentDescription = rs.getString("description");
                    appointmentToAdd.setAppointmentDescription(appointmentDescription);
                    String appointmentLocation = rs.getString("location");
                    appointmentToAdd.setAppointmentLocation(appointmentLocation);
                    String appointmentContact = rs.getString("contact");
                    appointmentToAdd.setAppointmentContact(appointmentContact);
                    String appointmentURL = rs.getString("url");
                    appointmentToAdd.setAppointmentURL(appointmentURL);
                    java.sql.Timestamp start = rs.getTimestamp("start");
                    java.sql.Timestamp end = rs.getTimestamp("end");
                    appointmentToAdd.setStartTime(start);
                    appointmentToAdd.setEndTime(end);
                    String appointmentLastUpdatedBy = rs.getString("lastUpdateBy");
                    appointmentToAdd.setAppointmentLastUpdatedBy(appointmentLastUpdatedBy);
                    appointmentList.add(appointmentToAdd);
                }
            } catch(Exception e){
                e.printStackTrace();

            }
        conn.close();
        } catch(Exception e){
                e.printStackTrace();    
            }
    }
    
    public static ArrayList<CustomerAppointment> getAllAppointments() {
        return appointmentList;
    }
    
    
    public static CustomerAppointment getAppointmentDetails(String apptID){
        int appointmentIDInt = Integer.parseInt(apptID);
        CustomerAppointment appointment = new CustomerAppointment();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://52.206.157.109/U04vDR";
            String user = "U04vDR";
            String pass = "53688357932";
            Connection conn = DriverManager.getConnection(url, user, pass);
            try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM appointment WHERE appointmentId = ?")) {
                stmt.setInt(1, appointmentIDInt);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()) {
                    int appointmentID = rs.getInt("appointmentId");
                    appointment.setAppointmentID(appointmentID);
                    int customerID = rs.getInt("customerId");
                    appointment.setAppointmentCustomerID(customerID);
                    String appointmentTitle = rs.getString("title");
                    appointment.setAppointmentTitle(appointmentTitle);
                    String appointmentDescription = rs.getString("description");
                    appointment.setAppointmentDescription(appointmentDescription);
                    String appointmentLocation = rs.getString("location");
                    appointment.setAppointmentLocation(appointmentLocation);
                    String appointmentContact = rs.getString("contact");
                    appointment.setAppointmentContact(appointmentContact);
                    String appointmentURL = rs.getString("url");
                    appointment.setAppointmentURL(appointmentURL);
                    java.sql.Timestamp start = rs.getTimestamp("start");
                    java.sql.Timestamp end = rs.getTimestamp("end");
                    appointment.setStartTime(start);
                    appointment.setEndTime(end);
                    String appointmentLastUpdatedBy = rs.getString("lastUpdateBy");
                    appointment.setAppointmentLastUpdatedBy(appointmentLastUpdatedBy);
                }
            } catch(Exception e){
                e.printStackTrace();

            }
        conn.close();
        } catch(Exception e){
                e.printStackTrace();    
            }
        return appointment;
    }
    
public static void resetAllAppointments() {
    appointmentList.clear();
    setAllAppointments();
}
}
