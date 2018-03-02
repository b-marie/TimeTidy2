/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

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
    

    
    //Get month start day
    
//    Date day = new Date();
//    Calendar calendarDay = Calendar.getInstance();
//    calendarDay.setTime(day);
//    int dayOfWeek = calendarDay.get(Calendar.DAY_OF_WEEK);
//    int weekOfMonth = (currentDay/7);
//    if(weekOfMonth > 0 && (weekOfMonth%7)>0){
//        weekOfMonth++;
//    }
//    //Set the label x and y position
//    int labelXPos = dayOfWeek - 1;
//    int labelYPos = weekOfMonth;
//    String startingCalLabel = "CalendarLabel" + labelXPos + labelYPos;
    
    //Get number of days in month

//    //Set calendar
//    System.out.println(startingCalLabel);
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
