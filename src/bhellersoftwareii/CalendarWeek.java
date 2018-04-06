/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import static bhellersoftwareii.CalendarMonth.allCalendarDays;
import static bhellersoftwareii.calendar.currentYearMonth;
import static bhellersoftwareii.calendar.today;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
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
    
    public void CalendarWeek(){
        LocalDateTime date;
    
    }
    
    public static void setCalendarWeek(GridPane gridPane, LocalDateTime day) {
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
            cd.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
            gridPane.add(cd, i, 0);
            calendarWeek.add(cd);
        }
    }
    
    
    public static void addWeekAppointment(GridPane gridPane, LocalDateTime date, int numDays) {
        ArrayList<MonthAppointmentButton> buttonsToAdd = new ArrayList<>();
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
            if(btn.getTime().getDayOfYear() <= date.getDayOfYear() + numDays){
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
                                    for(CalendarDay d : calendarWeek) {
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
