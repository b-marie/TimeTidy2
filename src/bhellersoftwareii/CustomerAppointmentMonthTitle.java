/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Britt
 */
public final class CustomerAppointmentMonthTitle {
    SimpleStringProperty appointmentMonth = new SimpleStringProperty("");
    SimpleStringProperty appointmentTitle = new SimpleStringProperty("");
    
    
    public CustomerAppointmentMonthTitle(String month, String title) {
        this.setAppointmentMonth(month);
        this.setAppointmentTitle(title);
    }
    
    public CustomerAppointmentMonthTitle() {
        
    }
    
    public String getAppointmentTitle(){
        return appointmentTitle.get();
    }
    
    public void setAppointmentTitle(String title){
        appointmentTitle.set(title);
    }
    
    public StringProperty AppointmentTitleProperty(){
        return appointmentTitle;
    }
    
    public String getAppointmentMonth() {
        return appointmentMonth.get();
    }
    
    public void setAppointmentMonth(String start){
        appointmentMonth.set(start);
    }
    
    public StringProperty AppointmentMonthProperty(){
        return appointmentMonth;
    }
    
    
}
