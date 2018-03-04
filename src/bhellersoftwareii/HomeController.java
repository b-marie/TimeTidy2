/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import static bhellersoftwareii.calendar.currentYearMonth;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        //Set Labels
        TimeZoneLabel.setText(calendar.getTimeZone());
        CalendarMonthYearText.setText(calendar.getMonth() + " " + calendar.getYr());
        CalendarDateLabel.setText(calendar.getMonth() + " " + calendar.getCurrentDay() + ", " + calendar.getYr());
        CalendarDatePicker.setValue(calendar.today);
        NameLabel.setText(currentUser);
        
        
        //Set calendar
        //Rows and columns for calendar grid pane
        for(int i = 1; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                CalendarDay cd = new CalendarDay();
                cd.setPrefSize(200, 200);
                CalendarMonthGrid.add(cd, j, i);
                allCalendarDays.add(cd);
            }
        }
        calendar.populateCalendar(currentYearMonth);
        
        
        //Set customer data table
        CustomerNameCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerName"));
        CustomerAddressCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText"));
        CustomerAddress2Col.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText2"));
        CustomerCityCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCity"));
        CustomerCountryCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCountry"));
        CustomerPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerPhoneNumber"));
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlurl = "jdbc:mysql://52.206.157.109/U04vDR";
                    String user = "U04vDR";
                    String pass = "53688357932";
                    Connection conn = DriverManager.getConnection(sqlurl, user, pass);
                    if(conn != null) {
                    }
        } catch (Exception ex) {
            ex.printStackTrace();
        } 

        buildCustomerDataTable();
    }    

    static ArrayList<CalendarDay> allCalendarDays = new ArrayList<>(35);
    
    static String currentUser = LoginController.currentUser;
    
    @FXML
    private Tab CalendarTab;

    @FXML
    private GridPane CalendarMonthGrid;

    @FXML
    private TableView<?> CalendarDayTable;

    @FXML
    private TableColumn<?, ?> CalendarDayTableTimeCol;

    @FXML
    private TableColumn<?, ?> CalendarDayTableAppointmentCol;

    @FXML
    private Label CalendarMonthYearText;

    @FXML
    private Label CalendarDateLabel;
    
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
    private Label CalendarMonthViewText;

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
    private TableView<?> WeekViewTable;
    
    @FXML
    private Button MonthViewButton;
    
    @FXML
    private Hyperlink CustIDLink;
    
    public static ObservableList<customer> data;
    public static ObservableList<customer> data2;
    static customer selectedCustomer = new customer();
    
    public void buildCustomerDataTable(){
        data = FXCollections.observableArrayList();
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


//    @FXML
//    void CustIDLinkPressed(ActionEvent event) {
//        System.out.println("Customer ID Link Pressed - Add Search Function to Find Customer");
//    }
    
    @FXML
    void AppointmentTypeToggleButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

    @FXML
    void CalendarDatePickerSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }


    @FXML
    void CalendarNewButtonPressed(ActionEvent event) throws IOException {
        Parent newAppt = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        Scene newApptScene = new Scene(newAppt);
        Stage homeStage = new Stage();
        homeStage.setScene(newApptScene);
        homeStage.show();
    }

    @FXML
    void ConsultantScheduleToggleButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }
    
    @FXML
    void BackwardButtonPressed(ActionEvent event) {
        calendar.previousMonth();
        CalendarMonthYearText.setText(calendar.getMonth() + " " + calendar.getYr());
        CalendarDateLabel.setText(calendar.getMonth() + " " + calendar.getCurrentDay() + ", " + calendar.getYr());
        CalendarDatePicker.setValue(calendar.today);
    }
    
    @FXML
    void ForwardButtonPressed(ActionEvent event) {
        calendar.nextMonth();
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
        WeekViewTable.setVisible(true);
        WeekViewButton.setVisible(false);
        MonthViewButton.setVisible(true);
        CalendarMonthGrid.setVisible(false);
    }
    
    @FXML
    void MonthViewButtonPressed(ActionEvent event) {
        System.out.println("Month view button pressed!");
        WeekViewTable.setVisible(false);
        WeekViewButton.setVisible(true);
        MonthViewButton.setVisible(false);
        CalendarMonthGrid.setVisible(true);
    }

    
}
