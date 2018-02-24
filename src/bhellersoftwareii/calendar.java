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
//    TimeZoneLabel.setText(timezone);
//    CalendarMonthYearText.setText(month + " " + year);
//    CalendarDateLabel.setText(month + " " + currentDay + ", " + year);
//    CalendarDatePicker.setValue(today);
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
