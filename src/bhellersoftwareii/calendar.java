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

}
