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
import java.util.Calendar;
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
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) NewApptCancelButton.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    void NewApptSaveButtonPressed(ActionEvent event) throws ParseException, SQLException {
        System.out.println("Save button pressed");
        //Save data from this form to database
        System.out.println("New appointment save button pressed");
        //Collect New Appointment information
        String customerID = NewApptCustIDEntry.getText();
        int custId = Integer.parseInt(customerID);
        String apptTitle = NewApptTitleTextEntry.getText();
        String apptDesc = NewApptDescTextEntry.getText();
        String apptLoc = NewApptLocTextEntry.getText();
        String apptContact = NewApptContactTextEntry.getText();
        String apptURL = NewApptURLTextEntry.getText();
        LocalDateTime startingTime = getAppointmentStartTime();
        java.sql.Timestamp startTime = java.sql.Timestamp.valueOf(startingTime);
        LocalDateTime endingTime = getAppointmentEndTime();
        java.sql.Timestamp endTime = java.sql.Timestamp.valueOf(endingTime);
        java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String person = HomeController.currentUser;

        //Save new appointment to the database
        if(addAppointment(custId, apptTitle, apptDesc, apptLoc, apptContact, apptURL, startTime, endTime, person, today)) {
                //Close the current stage
                Stage stage = (Stage) NewApptSaveButton.getScene().getWindow();
                stage.close();
            } else {
                Alert invalidCredentialsAlert = new Alert(Alert.AlertType.WARNING);
                invalidCredentialsAlert.setTitle("Invalid Action");
                invalidCredentialsAlert.setHeaderText("There was a problem");
                invalidCredentialsAlert.setContentText("Customer information was not saved");

                invalidCredentialsAlert.showAndWait();
            } 
//           //Update the Customer Records Table
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String sqlurl = "jdbc:mysql://52.206.157.109/U04vDR";
//                    String user = "U04vDR";
//                    String pass = "53688357932";
//                    Connection conn = DriverManager.getConnection(sqlurl, user, pass);
//                    if(conn != null) {
//                        PreparedStatement SQL = conn.prepareStatement("SELECT customer.customerName, address.address, address.address2, city.city, country.country, address.phone FROM U04vDR.customer JOIN address on customer.addressId = address.addressId JOIN city on address.cityId = city.cityId JOIN country on city.countryId = country.countryId WHERE customer.customerName = ? ");
//                        SQL.setString(1, custName);
//                        ResultSet rs = SQL.executeQuery();
//                        System.out.println(rs);
//                        while(rs.next()) {
//                            customer cm = new customer();
//                            cm.customerName.set(rs.getString("customerName"));
//                            cm.customerAddressText.set(rs.getString("address"));
//                            cm.customerAddressText2.set(rs.getString("address2"));
//                            cm.customerCity.set(rs.getString("city"));
//                            cm.customerCountry.set(rs.getString("country"));
//                            cm.customerPhoneNumber.set(rs.getString("phone"));
//                            HomeController.data.add(cm);
//                            System.out.println(cm);
//                        }
//                    }
//                    conn.close();
////                    HomeController.CustomerRecordsTable.setItems(HomeController.data);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
    
        private boolean addAppointment(int customerID, String appointmentTitle, String appointmentDescription, 
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
    
    LocalDateTime getAppointmentStartTime() throws ParseException {
        LocalDate localDate = NewApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time start = new Time(formatter.parse(NewApptStartTimeEntry.getText()).getTime());
        LocalTime startingTime = start.toLocalTime();
        ZoneId tz = ZoneId.systemDefault();
        TimeZone tzname = TimeZone.getDefault();
        ZonedDateTime startTimeAndZone = ZonedDateTime.of(localDate, startingTime, tz);
        ZonedDateTime startTimeAndZoneUTC = startTimeAndZone.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime startDateTimeLocal = LocalDateTime.ofInstant(startTimeAndZoneUTC.toInstant(), ZoneId.of("UTC"));
//        java.util.Date startDateTime = java.util.Date.from(startDateTimeLocal.atZone(ZoneId.of("UTC")).toInstant());
//        java.util.Date gmtStartTime = (startDateTime.getTime() - tzname.getRawOffset());
//        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String startTime = sdf.format(startDateTime);
        
        return startDateTimeLocal;
    }
    
    LocalDateTime getAppointmentEndTime() throws ParseException {
        LocalDate localDate = NewApptDatePicker.getValue();
        DateFormat formatter = new SimpleDateFormat("HH:mm");
        Time end = new Time(formatter.parse(NewApptEndTimeEntry.getText()).getTime());
        LocalTime endingTime = end.toLocalTime();
        ZoneId tz = ZoneId.systemDefault();
        ZonedDateTime endTimeAndZone = ZonedDateTime.of(localDate, endingTime, tz);
//        java.util.Date endDateTime = java.util.Date.from(endTimeAndZone.toInstant());
//        ZonedDateTime endTimeAndZoneUTC = ZonedDateTime.of(localDate, endingTime, ZoneId.of("UTC"));
        ZonedDateTime endTimeAndZoneUTC = endTimeAndZone.withZoneSameInstant(ZoneOffset.UTC);
        LocalDateTime endDateTimeLocal = LocalDateTime.ofInstant(endTimeAndZoneUTC.toInstant(), ZoneId.of("UTC"));
//        java.util.Date endDateTime = java.util.Date.from(endTimeAndZoneUTC.toInstant());
//        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String endTime = sdf.format(endDateTime);
        
        
        return endDateTimeLocal;
    }
    
}

