/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import bhellersoftwareii.MonthAppointmentButton;
import bhellersoftwareii.CustomerAppointment;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Britt
 */
public class CalendarWeek {
    
    static ArrayList<CalendarDay> calendarWeek = new ArrayList<>(7);
    static ArrayList<MonthAppointmentButton> buttonsToAdd = new ArrayList<>();
    static ArrayList<ButtonObject> buttonIDs = new ArrayList<>();
    
    public void CalendarWeek(){
        LocalDateTime date;
    
    }
    
    public static void setCalendarWeek(GridPane gridPane, LocalDateTime day) throws IOException {
        calendarWeek.clear();
        LocalDate calendarDate = day.toLocalDate();
        ArrayList<Node> dayOfWeekArray = new ArrayList<>(7); 
        for(int k = 0; k <= 7; k++) {
            Node node = gridPane.getChildren().get(k);
            dayOfWeekArray.add(node);
        }
        gridPane.getChildren().clear();
        for(int l = 0; l <= 7; l++) {
            gridPane.getChildren().add(l, dayOfWeekArray.get(l));
        }
        while(!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) {
            calendarDate = calendarDate.minusDays(1);
        }
        for(int i = 1; i < 8; i++) {
            CalendarDay cd = new CalendarDay();
            cd.setPrefSize(20, 10);
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            cd.setDate(calendarDate);
            cd.setTopAnchor(txt, 5.0);
            cd.setLeftAnchor(txt, 5.0);
            cd.setXVal(i);
            cd.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
            gridPane.add(cd, i, 0);
            calendarWeek.add(cd);
        }
        clearAllAppointments(gridPane);
        setAllAppointments(gridPane);
    }
        
    public static void setAllAppointments(GridPane gridPane) throws IOException {
        for(CustomerAppointment a : CustomerAppointment.getAllAppointments()){
            int xval = 0;
            for(CalendarDay d : calendarWeek) {
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
                    gridPane.add(newAppointment, xval, 2);
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
