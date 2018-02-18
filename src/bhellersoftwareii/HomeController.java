/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.awt.event.MouseEvent;
import java.lang.reflect.Method;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

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
        
    //Get the current time zone of the user
    Calendar cal = Calendar.getInstance();
    long milliDiff = cal.get(Calendar.ZONE_OFFSET);
    //Local offset, now loop through available timezone IDs
    String [] ids = TimeZone.getAvailableIDs();
    String timezone = null;
    for(String id : ids){
        TimeZone tz = TimeZone.getTimeZone(id);
        if(tz.getRawOffset() == milliDiff) {
            //Found a match\
            timezone = id;
            break;
        }
    }
    System.out.println(timezone);
    
    
    //Get current date information
    LocalDateTime now = LocalDateTime.now();
    LocalDate today = LocalDate.now();
    int year = now.getYear();
    int monthValue = now.getMonthValue();
    String month = "";
        if(monthValue == 1){
            month = "January";
        } else if(monthValue == 2){
            month = "February";
        } else if(monthValue == 3){
            month = "March";
        } else if(monthValue == 4){
            month = "April";
        } else if(monthValue == 5){
            month = "May";
        } else if(monthValue == 6){
            month = "June";
        } else if(monthValue == 7){
            month = "July";
        } else if(monthValue == 8){
            month = "August";
        } else if(monthValue == 9){
            month = "September";
        } else if(monthValue == 10){
            month = "October";
        } else if(monthValue == 11){
            month = "November";
        } else if(monthValue == 12){
            month = "December";
        }
    int currentDay = now.getDayOfMonth();
    
    Date day = new Date();
    Calendar calendarDay = Calendar.getInstance();
    calendarDay.setTime(day);
    int dayOfWeek = calendarDay.get(Calendar.DAY_OF_WEEK);
    int weekOfMonth = (currentDay/7);
    if(weekOfMonth > 0 && (weekOfMonth%7)>0){
        weekOfMonth++;
    }
    //Set the label x and y position
    int labelXPos = dayOfWeek - 1;
    int labelYPos = weekOfMonth;
    String startingCalLabel = "CalendarLabel" + labelXPos + labelYPos;
    
    
    //Set Labels
    TimeZoneLabel.setText(timezone);
    CalendarMonthYearText.setText(month + " " + year);
    CalendarDateLabel.setText(month + " " + currentDay + ", " + year);
    CalendarDatePicker.setValue(today);
    //Set calendar
    System.out.println(startingCalLabel);
//    Label todayLabel = (Label) root.lookup("#startingCalLabel");
//    CalendarMonthGrid.getChildren();
    
//    Node result = null;
//    ObservableList<Node> calendarChildren = CalendarMonthGrid.getChildren();
//    for(Node calendarResult : calendarChildren) {
//        if(CalendarMonthGrid.getRowIndex(calendarResult) == labelXPos && CalendarMonthGrid.getColumnIndex(calendarResult) == labelYPos) {
//            result = calendarResult;
//            System.out.println(result);
//            break;
//        }
//    }
//    try {
//        Object instance = getClass().getDeclaredField(startingCalLabel).get(this);
//        Method m = instance.getClass().getMethod("setText", currentDay);
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
//    int startOfWeek = today.minusDays(today.getDayOfWeek().getValue() - 1) ;
//    System.out.println(startOfWeek);
//    LocalDate endOfWeek = startOfWeek.plusDays(6);
//    System.out.println(endOfWeek);
    }    

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
    private Label CalendarLabel01;

    @FXML
    private Label CalendarLabel11;

    @FXML
    private Label CalendarLabel21;

    @FXML
    private Label CalendarLabel31;

    @FXML
    private Label CalendarLabel41;

    @FXML
    private Label CalendarLabel51;

    @FXML
    private Label CalendarLabel61;

    @FXML
    private Label CalendarLabel02;

    @FXML
    private Label CalendarLabel12;

    @FXML
    private Label CalendarLabel22;

    @FXML
    private Label CalendarLabel32;

    @FXML
    private Label CalendarLabel42;

    @FXML
    private Label CalendarLabel52;

    @FXML
    private Label CalendarLabel62;

    @FXML
    private Label CalendarLabel03;

    @FXML
    private Label CalendarLabel13;

    @FXML
    private Label CalendarLabel23;

    @FXML
    private Label CalendarLabel33;

    @FXML
    private Label CalendarLabel43;

    @FXML
    private Label CalendarLabel53;

    @FXML
    private Label CalendarLabel63;

    @FXML
    private Label CalendarLabel04;

    @FXML
    private Label CalendarLabel14;

    @FXML
    private Label CalendarLabel24;

    @FXML
    private Label CalendarLabel34;

    @FXML
    private Label CalendarLabel44;

    @FXML
    private Label CalendarLabel54;

    @FXML
    private Label CalendarLabel64;

    @FXML
    private Label CalendarLabel05;

    @FXML
    private Label CalendarLabel15;

    @FXML
    private Label CalendarLabel25;

    @FXML
    private Label CalendarLabel35;

    @FXML
    private Label CalendarLabel45;

    @FXML
    private Label CalendarLabel55;

    @FXML
    private Label CalendarLabel65;

    @FXML
    private Label CalendarDateLabel;
    
    @FXML
    private Label TimeZoneLabel;

    @FXML
    private DatePicker CalendarDatePicker;

    @FXML
    private Label CalendarWeekViewText;
    
    @FXML
    private Label NameLabel;

    @FXML
    private Button CalendarNewButton;

    @FXML
    private Label CalendarMonthViewText;

    @FXML
    private Tab CustomerRecordsTab;

    @FXML
    private TableView<?> CustomerRecordsTable;

    @FXML
    private TableColumn<?, ?> CustomerNameCol;

    @FXML
    private TableColumn<?, ?> CustomerAddressCol;

    @FXML
    private TableColumn<?, ?> CustomerPhoneNumberCol;

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
    void AppointmentTypeToggleButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

    @FXML
    void CalendarDatePickerSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

//    @FXML
//    void CalendarMonthViewTextClicked(MouseEvent event) {
//        System.out.println("Something happened!");
//    }

    @FXML
    void CalendarNewButtonPressed(ActionEvent event) {
        System.out.println("Something happened!");
    }

//    @FXML
//    void CalendarWeekViewTextClicked(MouseEvent event) {
//        System.out.println("Something happened!");
//    }

    @FXML
    void ConsultantScheduleToggleButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

    @FXML
    void CustomReportToggleButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

    @FXML
    void CustomerRecordsAddButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

    @FXML
    void CustomerRecordsDeleteButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

    @FXML
    void CustomerRecordsModifyButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

    @FXML
    void ReportsGenerateButtonSelected(ActionEvent event) {
        System.out.println("Something happened!");
    }

    
}
