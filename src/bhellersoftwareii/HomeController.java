/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class HomeController implements Initializable {
    
    CalendarMonth thisMonth = new CalendarMonth();
    CalendarWeek thisWeek = new CalendarWeek();
    LocalDateTime referenceDate;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Generate reference date from which month and week calendars can be built on
        referenceDate = LocalDateTime.now();
        try {
            logLogin();
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        clickedButtonID = "";
        
        //Get all current appointments from database and set to appointments array
        CustomerAppointment.setAllAppointments();
        System.out.println(CustomerAppointment.appointmentList);
        
        //Check for appointments in the next 15 minutes
        AlertReminder.checkNext15MinutesAndAlert();
        
        //Set Labels
        TimeZoneLabel.setText(calendar.getTimeZone());
        CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
        NameLabel.setText(currentUser);
        
        try {
            //Initialize month calendar based on reference day
            thisMonth.setCalendarMonth(CalendarMonthGrid, referenceDate);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            thisWeek.setCalendarWeek(WeekViewGrid, referenceDate);
        } catch (IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Set customer data table
        CustomerNameCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerName"));
        CustomerAddressCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText"));
        CustomerAddress2Col.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText2"));
        CustomerCityCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCity"));
        CustomerCountryCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCountry"));
        CustomerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerPhoneNumber"));
        buildCustomerDataTable();
    }    
    
    //Variables
    public static String currentUser = LoginController.currentUser;
    public static LocalDate currentCal;
    public static LocalDate currentWeek;
    public static String clickedButtonID;
    
    public static String getClickedButtonID(){
        return clickedButtonID;
    }
    
    public static void setClickedButtonID(String apptID){
        clickedButtonID = apptID;
    }
    
    @FXML
    private Tab CalendarTab;

    @FXML
    GridPane CalendarMonthGrid = new GridPane();
    
    @FXML
    Label CalendarMonthYearText;
    
    @FXML
    GridPane WeekViewGrid = new GridPane();

    @FXML
    private Label TimeZoneLabel;

    @FXML
    DatePicker CalendarDatePicker;
    
    @FXML
    private Label NameLabel;
    
    @FXML
    private Button WeekViewButton;

    @FXML
    private Button CalendarNewButton;
    
    @FXML
    private Button BackwardButton;

    @FXML
    private Button ForwardButton;

    @FXML
    private static Label CalendarMonthViewText;

    @FXML
    private Tab CustomerRecordsTab;

    @FXML
    public TableView<customer> CustomerRecordsTable;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerNameCol;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerAddressCol;
    
    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerAddress2Col;
    
    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerCityCol;
    
    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerCountryCol;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerPhoneNumberCol;

    @FXML
    private Button CustomerRecordsDeleteButton;

    @FXML
    private Button CustomerRecordsModifyButton;

    @FXML
    private Button CustomerRecordsAddButton;

    @FXML
    private Tab ReportsTab;

    @FXML
    private RadioButton AppointmentTypeToggleButton;

    @FXML
    private ToggleGroup ReportsToggle;

    @FXML
    private Button ReportsGenerateButton;

    @FXML
    private RadioButton ConsultantScheduleToggleButton;

    @FXML
    private RadioButton CustomReportToggleButton;
    
    @FXML
    private Button MonthViewButton;
    
    @FXML
    private Hyperlink CustIDLink;
    
    @FXML
    private Button RefreshButton;
    
    public static ObservableList<customer> data = FXCollections.observableArrayList();
    public static ObservableList<customer> data2;
    public static customer selectedCustomer = new customer();
    public static String reportTitle = "";
    public Boolean appointmentTypeToggle = false;
    public Boolean consultantScheduleToggle = false;
    public Boolean customReportToggle = false;
    
    public void buildCustomerDataTable(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlurl = "jdbc:mysql://52.206.157.109/U04vDR";
                    String user = "U04vDR";
                    String pass = "53688357932";
                    Connection con = DriverManager.getConnection(sqlurl, user, pass);
                    if(con != null) {
                        String SQL = "SELECT customer.customerName, customer.createDate, customer.createdBy, address.address, address.address2, city.city, country.country, address.phone FROM U04vDR.customer JOIN address on customer.addressId = address.addressId JOIN city on address.cityId = city.cityId JOIN country on city.countryId = country.countryId";
                        ResultSet rs = con.createStatement().executeQuery(SQL);
                        System.out.println(rs);
                        while(rs.next()) {
                            customer cm = new customer();
//                            cm.getCustomerName();
                            cm.setCustomerName(rs.getString("customerName"));
                            cm.setCustomerAddressText(rs.getString("address"));
                            cm.setCustomerAddressText2(rs.getString("address2"));
                            cm.setCustomerCity(rs.getString("city"));
                            cm.setCustomerCountry(rs.getString("country"));
                            cm.setCustomerPhoneNumber(rs.getString("phone"));
                            cm.setCustomerDateAdded(rs.getTimestamp("createDate"));
                            cm.setCustomerAddedBy(rs.getString("createdBy"));
                            data.add(cm);
                            System.out.println(cm);
                        }
                        rs.close();
                    }
                    CustomerRecordsTable.setItems(data);
                    
                    con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void AppointmentTypeToggleButtonSelected(ActionEvent event) {
        System.out.println("AppointmentTypeToggleButton is selected");
        consultantScheduleToggle = false;
        customReportToggle = false;
        appointmentTypeToggle = true;
    }

    @FXML
    void CalendarNewButtonPressed(ActionEvent event) throws IOException {
        try{
            Parent newAppt = FXMLLoader.load(getClass().getResource("CustomerSearch.fxml"));
            Scene newApptScene = new Scene(newAppt);
            Stage newApptStage = new Stage();
            newApptStage.setScene(newApptScene);
            newApptStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ConsultantScheduleToggleButtonSelected(ActionEvent event) {
        System.out.println("ConsultantScheduleToggle selected");
        consultantScheduleToggle = true;
        customReportToggle = false;
        appointmentTypeToggle = false;
    }
    
    @FXML
    void BackwardButtonPressed(ActionEvent event) {
        if(CalendarMonthGrid.isVisible()) {
            System.out.println("Go to the last Month!");
            try{
                referenceDate = referenceDate.minusMonths(1);
                CalendarMonth.setCalendarMonth(CalendarMonthGrid, referenceDate);
                CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            System.out.println("Go to the last week!");
            try{
                referenceDate = referenceDate.minusDays(7);
                CalendarWeek.setCalendarWeek(WeekViewGrid, referenceDate);
                CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
    @FXML
    void ForwardButtonPressed(ActionEvent event) {
        if(CalendarMonthGrid.isVisible()){
            System.out.println("Go to the next Month!");
            try{
                referenceDate = referenceDate.plusMonths(1);
                CalendarMonth.setCalendarMonth(CalendarMonthGrid, referenceDate);
                CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Go to the next week!");
            try{
                referenceDate = referenceDate.plusDays(7);
                CalendarWeek.setCalendarWeek(WeekViewGrid, referenceDate);
                CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void CustomReportToggleButtonSelected(ActionEvent event) {
        System.out.println("CustomeReportToggle selected");
        consultantScheduleToggle = false;
        customReportToggle = true;
        appointmentTypeToggle = false;
    }

    @FXML
    void CustomerRecordsAddButtonSelected(ActionEvent event) throws IOException {
        System.out.println("Customer Records Add Button Selected!");
        Parent newCust = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        Scene newCustScene = new Scene(newCust);
        Stage homeStage = new Stage();
        homeStage.setScene(newCustScene);
        homeStage.show();
    }

    @FXML
    void CustomerRecordsDeleteButtonSelected(ActionEvent event) {
        System.out.println("Customer Records Delete Button Selected!");
        data2 = CustomerRecordsTable.getItems();
        selectedCustomer = CustomerRecordsTable.getSelectionModel().getSelectedItem();
        String selectedCustomerName = selectedCustomer.getCustomerName();
        //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Confirm Delete Customer");
        confirm.setContentText("Are you sure you wish to delete this customer?");

        //Close the window
        confirm.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                data.remove(selectedCustomer);
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
                            PreparedStatement apptUpdateQuery = conn.prepareStatement("DELETE FROM customer WHERE customerName = ?");
                            apptUpdateQuery.setString(1, selectedCustomerName);
                            apptUpdateQuery.executeUpdate();
                            System.out.println("Customer " + selectedCustomerName + " has been deleted!");
                            apptUpdateQuery.close();
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            }));
    }

    @FXML
    void CustomerRecordsModifyButtonSelected(ActionEvent event) throws IOException {
        System.out.println("Customer Records Modify Button Selected!");
        data2 = CustomerRecordsTable.getItems();
        selectedCustomer = CustomerRecordsTable.getSelectionModel().getSelectedItem();
        Parent modCust = FXMLLoader.load(getClass().getResource("ModifyCustomer.fxml"));
        Scene modCustScene = new Scene(modCust);
        Stage homeStage = new Stage();
        homeStage.setScene(modCustScene);
        homeStage.show();
    }

    @FXML
    void ReportsGenerateButtonSelected(ActionEvent event) {
        System.out.println("Generating report...");
        if(appointmentTypeToggle) {
            reportTitle = "Appointment Type by Month Report";
            try{
            Parent report = FXMLLoader.load(getClass().getResource("AppointmentTypeReport.fxml"));
            Scene reportScene = new Scene(report);
            Stage reportStage = new Stage();
            reportStage.setScene(reportScene);
            reportStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        } else if (consultantScheduleToggle) {
            reportTitle = "Consultant Schedule Report";
            try{
            Parent report = FXMLLoader.load(getClass().getResource("ConsultantScheduleReport.fxml"));
            Scene reportScene = new Scene(report);
            Stage reportStage = new Stage();
            reportStage.setScene(reportScene);
            reportStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        } else if (customReportToggle) {
            reportTitle = "CustomerInteractionReport";
            try{
            Parent report = FXMLLoader.load(getClass().getResource("CustomerInteractionReport.fxml"));
            Scene reportScene = new Scene(report);
            Stage reportStage = new Stage();
            reportStage.setScene(reportScene);
            reportStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        } else {
            System.out.println("Please select a report type");
        }
        
            
    }
    
    @FXML
    void WeekViewButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Week view button pressed!");
        WeekViewGrid.setVisible(true);
        WeekViewButton.setVisible(false);
        MonthViewButton.setVisible(true);
        CalendarMonthGrid.setVisible(false);
        CalendarWeek.setCalendarWeek(WeekViewGrid, referenceDate);
    }
    
    @FXML
    void MonthViewButtonPressed(ActionEvent event) throws IOException {
        System.out.println("Month view button pressed!");
        WeekViewGrid.setVisible(false);
        WeekViewButton.setVisible(true);
        MonthViewButton.setVisible(false);
        CalendarMonthGrid.setVisible(true);
        CalendarMonth.setCalendarMonth(CalendarMonthGrid, referenceDate);
    }
    
    public void refreshCalendars() throws IOException {
        CalendarMonth.setCalendarMonth(CalendarMonthGrid, referenceDate);
        CalendarWeek.setCalendarWeek(WeekViewGrid, referenceDate);
    }
    
    public void logLogin() throws Exception {
        String filename = "log.txt";
        String path = HomeController.class.getProtectionDomain().getCodeSource().getLocation().toString();
        File fullPath = new File(path, filename);
        
        File directory = new File(path);
        if(!directory.exists()){
            directory.mkdir();
        }
        try(FileWriter writer = new FileWriter(fullPath, true)) {
            writer.write(ZonedDateTime.now() + " : " + currentUser + " logged in \n");
        } catch(IOException ee) {
            ee.printStackTrace();
        } 

        }

}
