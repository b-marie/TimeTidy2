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
public final class CustomerInteractionObject {
    SimpleStringProperty consultantName = new SimpleStringProperty("");
    SimpleStringProperty customerName = new SimpleStringProperty("");
    SimpleStringProperty dateAdded = new SimpleStringProperty("");
    
    
    public CustomerInteractionObject(String name, String custName, String date) {
        this.setConsultantName(name);
        this.setCustomerName(custName);
        this.setDateAdded(date);
    }
    
    public CustomerInteractionObject() {
        
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
    
    
    public String getCustomerName() {
        return customerName.get();
    }
    
    public void setCustomerName(String start){
        customerName.set(start);
    }
    
    public StringProperty CustomerNameProperty(){
        return customerName;
    }
    
    
    public String getDateAdded(){
        return dateAdded.get();
    }
    
    public void setDateAdded(String title){
        dateAdded.set(title);
    }
    
    public StringProperty DateAddedProperty(){
        return dateAdded;
    }
    
    
}
