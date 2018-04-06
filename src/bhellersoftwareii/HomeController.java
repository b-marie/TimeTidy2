/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import static bhellersoftwareii.calendar.currentYearMonth;
import static bhellersoftwareii.calendar.getMonthVal;
import static bhellersoftwareii.calendar.getYr;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        
        
        //Set Labels
        TimeZoneLabel.setText(calendar.getTimeZone());
        CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
//        CalendarDateLabel.setText(referenceDate.getMonth() + " " + referenceDate.getDayOfMonth() + ", " + referenceDate.getYear());
//        CalendarDatePicker.setValue(referenceDate.toLocalDate());
        NameLabel.setText(currentUser);
        
        //Initialize month calendar based on reference day
        thisMonth.setCalendarMonth(CalendarMonthGrid, referenceDate);
        thisWeek.setCalendarWeek(WeekViewGrid, referenceDate);
        thisMonth.addMonthAppointments(CalendarMonthGrid, referenceDate);
        
        //Set customer data table
        CustomerNameCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerName"));
        CustomerAddressCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText"));
        CustomerAddress2Col.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText2"));
        CustomerCityCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCity"));
        CustomerCountryCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCountry"));
        CustomerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerPhoneNumber"));


        buildCustomerDataTable();
        
        //Initialize Day View Table
        LocalDate todaysDate = LocalDate.now();
        LocalDate startOfWeek = todaysDate.minusDays(todaysDate.getDayOfWeek().getValue() - 1);
        LocalDate endOfWeek = startOfWeek.plusDays(6);
        
//        CalendarDayTableTimeCol.setCellValueFactory(start -> {
//            SimpleStringProperty startProperty = new SimpleStringProperty();
//            DateFormat timeFormat = new SimpleDateFormat("H:mm");
//            startProperty.setValue(timeFormat.format(start.getValue()));
//            return startProperty;
//        });
//        CalendarDayTableAppointmentCol.setCellValueFactory(new PropertyValueFactory<appointment, SimpleStringProperty>("appointmentTitle"));
        
//        for(LocalDate date = startOfWeek; !date.isAfter(endOfWeek); date = date.plusDays(1)){
//            int slotIndex = 1;
//            
//            for(LocalDateTime startTime = date.atTime(firstSlotStart); !startTime.isAfter(date.atTime(lastSlotStart)); startTime = startTime.plus(slotLength)) {
//                TimeSlot timeSlot = new TimeSlot(startTime, slotLength);
//                timeSlots.add(timeSlot);
//                
////                registerDragHandlers(timeSlot, mouseAnchor);
//                
//////                CalendarDayTable.setItems(timeSlots);
////            }
//        }
    }    
    
    //Variables
    static String currentUser = LoginController.currentUser;
    public static LocalDate currentCal;
    public static LocalDate currentWeek;
//    LocalTime firstSlotStart = LocalTime.of(0,0);
//    Duration slotLength = Duration.ofMinutes(15);
//    LocalTime lastSlotStart = LocalTime.of(23, 59);
//    ObservableList<TimeSlot> timeSlots = FXCollections.observableArrayList();
    
    @FXML
    private Tab CalendarTab;

    @FXML
    GridPane CalendarMonthGrid = new GridPane();

//    @FXML
//    private TableView<TimeSlot> CalendarDayTable;
//
//    @FXML
//    private TableColumn<TimeSlot, LocalDateTime> CalendarDayTableTimeCol;
//
//    @FXML
//    private TableColumn<?, ?> CalendarDayTableAppointmentCol;

    @FXML
    Label CalendarMonthYearText;
    
    @FXML
    GridPane WeekViewGrid = new GridPane();

//    @FXML
//     Label CalendarDateLabel;
//    
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
    
    public static ObservableList<customer> data = FXCollections.observableArrayList();
    public static ObservableList<customer> data2;
    static customer selectedCustomer = new customer();
    
    public void buildCustomerDataTable(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlurl = "jdbc:mysql://52.206.157.109/U04vDR";
                    String user = "U04vDR";
                    String pass = "53688357932";
                    Connection con = DriverManager.getConnection(sqlurl, user, pass);
                    if(con != null) {
                        String SQL = "SELECT customer.customerName, address.address, address.address2, city.city, country.country, address.phone FROM U04vDR.customer JOIN address on customer.addressId = address.addressId JOIN city on address.cityId = city.cityId JOIN country on city.countryId = country.countryId";
                        ResultSet rs = con.createStatement().executeQuery(SQL);
                        System.out.println(rs);
                        while(rs.next()) {
                            customer cm = new customer();
                            cm.customerName.set(rs.getString("customerName"));
                            cm.customerAddressText.set(rs.getString("address"));
                            cm.customerAddressText2.set(rs.getString("address2"));
                            cm.customerCity.set(rs.getString("city"));
                            cm.customerCountry.set(rs.getString("country"));
                            cm.customerPhoneNumber.set(rs.getString("phone"));
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
        System.out.println("Something happened!");
    }

//    @FXML
//    void CalendarDatePickerSelected(ActionEvent event) {
//        LocalDate pickerDate = CalendarDatePicker.getValue();
//        System.out.println(pickerDate);
//        CalendarDateLabel.setText(pickerDate.getMonth() + " " + pickerDate.getDayOfMonth() + ", " + pickerDate.getYear());
//    }


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
        System.out.println("Something happened!");
    }
    
    @FXML
    void BackwardButtonPressed(ActionEvent event) {
        if(CalendarMonthGrid.isVisible()) {
            System.out.println("Go to the last Month!");
            try{
                referenceDate = referenceDate.minusMonths(1);
                CalendarMonth.setCalendarMonth(CalendarMonthGrid, referenceDate);
                CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
                CalendarMonth.addMonthAppointments(CalendarMonthGrid, referenceDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else {
            System.out.println("Go to the last week!");
            try{
                referenceDate = referenceDate.minusDays(7);
                CalendarWeek.setCalendarWeek(WeekViewGrid, referenceDate);
                CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
                int numDays = -7;
                CalendarWeek.addWeekAppointment(WeekViewGrid, referenceDate, numDays);
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
                CalendarMonth.addMonthAppointments(CalendarMonthGrid, referenceDate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Go to the next week!");
            try{
                referenceDate = referenceDate.plusDays(7);
                CalendarWeek.setCalendarWeek(WeekViewGrid, referenceDate);
                CalendarMonthYearText.setText(referenceDate.getMonth() + " " + referenceDate.getYear());
                int numDays = 7;
                CalendarWeek.addWeekAppointment(WeekViewGrid, referenceDate, numDays);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        
    }

    @FXML
    void CustomReportToggleButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
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
        System.out.println("Something happened!");
    }
    
    @FXML
    void WeekViewButtonPressed(ActionEvent event) {
        System.out.println("Week view button pressed!");
        WeekViewGrid.setVisible(true);
        WeekViewButton.setVisible(false);
        MonthViewButton.setVisible(true);
        CalendarMonthGrid.setVisible(false);
        CalendarWeek.setCalendarWeek(WeekViewGrid, referenceDate);
        int numDays = 7;
        CalendarWeek.addWeekAppointment(WeekViewGrid, referenceDate, numDays);
    }
    
    @FXML
    void MonthViewButtonPressed(ActionEvent event) {
        System.out.println("Month view button pressed!");
        WeekViewGrid.setVisible(false);
        WeekViewButton.setVisible(true);
        MonthViewButton.setVisible(false);
        CalendarMonthGrid.setVisible(true);
        CalendarMonth.setCalendarMonth(CalendarMonthGrid, referenceDate);
        CalendarMonth.addMonthAppointments(CalendarMonthGrid, referenceDate);
    }
    

    
}
