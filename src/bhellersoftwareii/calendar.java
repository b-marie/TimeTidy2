/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import javafx.scene.text.Text;

/**
 *
 * @author Britt
 */
public class calendar {
    
    //Class constructors
    public static String timezone = "";
    public static LocalDateTime now = LocalDateTime.now();
    public static LocalDate today = LocalDate.now();
    public int year = 1990;
    public static int monthValue = 1;
    public static int currentDay = 1;
    public static int firstDayVal = 1;
    public static YearMonth currentYearMonth;
    
    
    
    
    public static int getCurrentDay() {
        return now.getDayOfMonth();
    }
    
    public static int getYr(){
        return now.getYear();
    }
    
    public static int getMonthVal() {
        return now.getMonthValue();
    }
    
    public static String getMonth(){
    String month = "";
    int monthVal = getMonthVal();
        if(monthVal == 1){
            month = "January";
        } else if(monthVal == 2){
            month = "February";
        } else if(monthVal == 3){
            month = "March";
        } else if(monthVal == 4){
            month = "April";
        } else if(monthVal == 5){
            month = "May";
        } else if(monthVal == 6){
            month = "June";
        } else if(monthVal == 7){
            month = "July";
        } else if(monthVal == 8){
            month = "August";
        } else if(monthVal == 9){
            month = "September";
        } else if(monthVal == 10){
            month = "October";
        } else if(monthVal == 11){
            month = "November";
        } else if(monthVal == 12){
            month = "December";
        }
      return month;  
    }
    
    public static String getTimeZone() {
    //Get the current time zone of the user
    Calendar cal = Calendar.getInstance();
    long milliDiff = cal.get(Calendar.ZONE_OFFSET);
    //Local offset, now loop through available timezone IDs
    String [] ids = TimeZone.getAvailableIDs();
    for(String id : ids){
        TimeZone tz = TimeZone.getTimeZone(id);
        if(tz.getRawOffset() == milliDiff) {
            //Found a match\
            timezone = id;
            break;
        }
    }
    return timezone;
    }
    
    public static LocalDate getFirstDayVal(){
        LocalDate calendarDate = LocalDate.of(getYr(), getMonthVal(), 1);
        return calendarDate;
    }
    
    public static void populateCalendar(YearMonth yearMonth) {
        //Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(getYr(), getMonthVal(), 1);
        //Dial back the day until it is SUNDAY
        while(!calendarDate.getDayOfWeek().toString().equals("SUNDAY")) {
            calendarDate = calendarDate.minusDays(1);
        }
        for(CalendarDay cd : HomeController.allCalendarDays) {
            if(cd.getChildren().size() != 0) {
                cd.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            cd.setDate(calendarDate);
            cd.setTopAnchor(txt, 5.0);
            cd.setLeftAnchor(txt, 5.0);
            cd.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
    }
    
    static void previousMonth() {
        System.out.println("Go to the last month!");
        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);
    }
    
    static void nextMonth() {
        System.out.println("Go to the next month!");
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);
    }
    
}
