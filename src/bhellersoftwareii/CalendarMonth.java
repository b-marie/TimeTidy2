/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import static bhellersoftwareii.calendar.currentYearMonth;
import static bhellersoftwareii.calendar.getMonthVal;
import static bhellersoftwareii.calendar.getYr;
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
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

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
    
    public static void setCalendarMonth(GridPane gridPane, LocalDateTime day) {
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
        
    }
    
    public static void addMonthAppointments(GridPane gridPane, LocalDateTime date) {
        ArrayList<ButtonObject> buttonIDs = new ArrayList<>();
        //Get list of all button objects to add
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://52.206.157.109/U04vDR";
            String user = "U04vDR";
            String pass = "53688357932";
            Connection conn = DriverManager.getConnection(url, user, pass);
            try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM appointment")) {
                        ResultSet rs = stmt.executeQuery();
                        while(rs.next()) {
                            int appointmentID = rs.getInt("appointmentId");
                            String btnText = rs.getString("title");
                            java.sql.Timestamp start = rs.getTimestamp("start");
                            LocalDateTime startTime = start.toLocalDateTime();
                            ButtonObject buttonInfo = new ButtonObject(appointmentID, btnText, startTime);
                            buttonIDs.add(buttonInfo);
                            System.out.println(buttonInfo.getButtonID());
                        }
            } catch(Exception e){
                e.printStackTrace();
            }
        conn.close();
        } catch(Exception e){
                e.printStackTrace();    
            }
        //find button objects in current month
        for(ButtonObject btn : buttonIDs){
            if(btn.getTime().getMonth() == date.getMonth()){
                int btnID = btn.getButtonID();
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://52.206.157.109/U04vDR";
                    String user = "U04vDR";
                    String pass = "53688357932";
                    Connection conn = DriverManager.getConnection(url, user, pass);
                    try(PreparedStatement stmt = conn.prepareStatement("SELECT * FROM appointment WHERE appointmentId = ?")) {
                                stmt.setInt(1, btnID);
                                ResultSet rs = stmt.executeQuery();
                                if(rs.next()) {
                                    int appointmentID = rs.getInt("appointmentId");
                                    String btnText = rs.getString("title");
                                    java.sql.Timestamp start = rs.getTimestamp("start");
                                    String appointID = Integer.toString(appointmentID);
                                    MonthAppointmentButton newAppointment = new MonthAppointmentButton(btnText, appointID);
                                    newAppointment.setId(appointID);
                                    buttonsToAdd.add(newAppointment);
                                    LocalDateTime startTime = start.toLocalDateTime();
                                    LocalDate apptDate = startTime.toLocalDate();
                                    int xVal = 0;
                                    int yVal = 0;
                                    for(CalendarDay d : allCalendarDays) {
                                        LocalDate dateToCompare = d.getDate();
                                        if(d.getDate().isEqual(apptDate)) {
                                            xVal = d.getXVal();
                                            yVal = d.getYVal();
                                        }
                                    }
                                    gridPane.add(newAppointment, xVal, yVal);
                                }
                    } catch(Exception e){
                        e.printStackTrace();

                    }
                conn.close();
                } catch(Exception e){
                        e.printStackTrace();    
                    }
            }
        }
        

        
        
    }
    
}
