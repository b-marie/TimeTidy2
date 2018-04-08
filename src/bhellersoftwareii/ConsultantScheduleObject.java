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
public final class ConsultantScheduleObject {
    SimpleStringProperty consultantName = new SimpleStringProperty("");
    SimpleStringProperty appointmentStart = new SimpleStringProperty("");
    SimpleStringProperty appointmentTitle = new SimpleStringProperty("");
    
    
    public ConsultantScheduleObject(String name, String start, String title) {
        this.setConsultantName(name);
        this.setAppointmentStart(start);
        this.setAppointmentTitle(title);
    }
    
    public ConsultantScheduleObject() {
        
    }
    
     public String getConsultantName() {
        return consultantName.get();
    }
    
    public void setConsultantName(String start){
        consultantName.set(start);
    }
    
    public StringProperty ConsultantNameProperty(){
        return consultantName;
    }
    
    
    public String getAppointmentStart() {
        return appointmentStart.get();
    }
    
    public void setAppointmentStart(String start){
        appointmentStart.set(start);
    }
    
    public StringProperty AppointmentStartProperty(){
        return appointmentStart;
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
    
    
}
