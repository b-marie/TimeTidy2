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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.TimeZone;
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
    }    

    static ArrayList<CalendarDay> allCalendarDays = new ArrayList<>(35);
    
    
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
    private TableView<?> CustomerRecordsTable;

    @FXML
    private TableColumn<?, ?> CustomerNameCol;

    @FXML
    private TableColumn<?, ?> CustomerAddressCol;
    
    @FXML
    private TableColumn<?, ?> CustomerAddress2Col;
    
    @FXML
    private TableColumn<?, ?> CustomerCityCol;
    
    @FXML
    private TableColumn<?, ?> CustomerCountryCol;

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
    private TableView<?> WeekViewTable;
    
    @FXML
    private Button MonthViewButton;
    
    @FXML
    private Hyperlink CustIDLink;

    
    
    
    @FXML
    void CustIDLinkPressed(ActionEvent event) {
        System.out.println("Customer ID Link Pressed - Add Search Functoin to Find Customer");
    }
    
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
    void CustomerRecordsAddButtonSelected(ActionEvent event) {
        System.out.println("Customer Records Add Button Selected!");
    }

    @FXML
    void CustomerRecordsDeleteButtonSelected(ActionEvent event) {
        System.out.println("Customer Records Delete Button Selected!");
    }

    @FXML
    void CustomerRecordsModifyButtonSelected(ActionEvent event) {
        System.out.println("Customer Records Modify Button Selected!");
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
