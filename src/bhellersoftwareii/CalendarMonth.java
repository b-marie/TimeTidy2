/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import bhellersoftwareii.CustomerAppointment;
import bhellersoftwareii.MonthAppointmentButton;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javax.swing.text.DateFormatter;

/**
 *
 * @author Britt
 */
public class CalendarMonth {
    
    static ArrayList<CalendarDay> allCalendarDays = new ArrayList<>(35);
    static ArrayList<MonthAppointmentButton> buttonsToAdd = new ArrayList<>();
    
    
    public void CalendarMonth(){
        LocalDateTime date;
    
    }
    
    public static void setCalendarMonth(GridPane gridPane, LocalDateTime day) throws IOException {
        allCalendarDays.clear();
        LocalDate calendarDate = LocalDate.of(day.getYear(), day.getMonth(), 1);
        ArrayList<Node> dayOfWeekArray = new ArrayList<>(7); 
        for(int k = 0; k <= 6; k++) {
            Node node = gridPane.getChildren().get(k);
            dayOfWeekArray.add(node);
        }
        gridPane.getChildren().clear();
        
        for(int l = 0; l <= 6; l++) {
            gridPane.getChildren().add(l, dayOfWeekArray.get(l));
        }
        while(!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) {
            calendarDate = calendarDate.minusDays(1);
        }
        for(int i = 1; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                CalendarDay cd = new CalendarDay();
                cd.setPrefSize(200, 200);
                Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
                cd.setDate(calendarDate);
                cd.setTopAnchor(txt, 5.0);
                cd.setLeftAnchor(txt, 5.0);
                cd.setXVal(j);
                cd.setYVal(i);
                cd.getChildren().add(txt);
                calendarDate = calendarDate.plusDays(1);
                gridPane.add(cd, j, i);
                allCalendarDays.add(cd);
            }
        }
        clearAllAppointments(gridPane);
        setAllAppointments(gridPane);        
    }
    
    public static void setAllAppointments(GridPane gridPane) throws IOException {
        for(CustomerAppointment a : CustomerAppointment.getAllAppointments()){
            int xval = 0;
            int yval = 0;
            for(CalendarDay d : allCalendarDays) {
                LocalDate dateToCompare = d.getDate();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy hh:mm a");
                LocalDate appointmentDateToCompare = LocalDate.parse(a.getStartTime(), formatter);
                if(d.getDate().isEqual(appointmentDateToCompare)) {
                    String appointmentID = Integer.toString(a.getAppointmentID());
                    String appointmentTitle = a.getAppointmentTitle();
                    MonthAppointmentButton newAppointment = new MonthAppointmentButton(appointmentTitle, appointmentID);
                    newAppointment.setMinWidth(50);
                    newAppointment.setText(appointmentTitle);
                    newAppointment.getStyleClass().add("monthButton");
                    buttonsToAdd.add(newAppointment);
                    xval = d.getXVal();
                    yval = d.getYVal();
                    gridPane.add(newAppointment, xval, yval);
                }
            }
    }
}
    
    public static void clearAllAppointments(GridPane gridPane) {
        for(MonthAppointmentButton b : buttonsToAdd) {
            gridPane.getChildren().removeAll(b);
        }
    }
}
