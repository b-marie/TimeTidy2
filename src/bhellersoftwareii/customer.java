/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.time.LocalDateTime;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Britt
 */
public class customer {
    static ObservableList<customer> customerList = FXCollections.observableArrayList();
    SimpleIntegerProperty customerID = new SimpleIntegerProperty(0);
    SimpleStringProperty customerName = new SimpleStringProperty("");
    SimpleIntegerProperty customerAddressID = new SimpleIntegerProperty(0);
    Boolean customerActive = true;
    SimpleStringProperty customerAddressText = new SimpleStringProperty("");
    SimpleStringProperty customerAddressText2 = new SimpleStringProperty("");
    SimpleStringProperty customerPhoneNumber = new SimpleStringProperty("");
    SimpleStringProperty customerPostalCode = new SimpleStringProperty("");
    SimpleIntegerProperty customerCityID = new SimpleIntegerProperty(0);
    SimpleStringProperty customerCity = new SimpleStringProperty("");
    SimpleIntegerProperty customerCountryID = new SimpleIntegerProperty(0);
    SimpleStringProperty customerCountry = new SimpleStringProperty("");
    java.sql.Timestamp dateAdded;
    SimpleStringProperty addedBy = new SimpleStringProperty("");
    
    public customer(int customerID, String customerName, int customerAddressID, Boolean customerActive,
            String customerAddressText, String customerAddressText2, String customerPhoneNumber,
            String customerPostalCode, int customerCityID, String customerCity, 
            int customerCountryID, String customerCountry, java.sql.Timestamp dateAdded, String addedBy) {
        setCustomerID(customerID);
        setCustomerName(customerName);
        setCustomerAddressID(customerAddressID);
        setCustomerActive(customerActive);
        setCustomerAddressText(customerAddressText);
        setCustomerAddressText2(customerAddressText2);
        setCustomerPhoneNumber(customerPhoneNumber);
        setCustomerPostalCode(customerPostalCode);
        setCustomerCityID(customerCityID);
        setCustomerCity(customerCity);
        setCustomerCountryID(customerCountryID);
        setCustomerCountry(customerCountry);
        setCustomerDateAdded(dateAdded);
        setCustomerAddedBy(addedBy);
        
    }

    public customer() {

    }
            
    public int getCustomerID(){
        return customerID.get();
    }
    
    public void setCustomerID(int CustomerID) {
        customerID.set(CustomerID);
    }        
    
    public String getCustomerName(){
        return customerName.get();
    }
    
    public void setCustomerName(String CustomerName) {
        customerName.set(CustomerName);
    }      
     
    public int getCustomerAddressID(){
        return customerAddressID.get();
    }
    
    public void setCustomerAddressID(int CustomerAddressID) {
        customerAddressID.set(CustomerAddressID);
    }      
    
    public Boolean getCustomerActive(){
        return customerActive;
    }
    
    public void setCustomerActive(Boolean custActive) {
        customerActive = custActive;
    }    
    
    public String getCustomerAddressText(){
        return customerAddressText.get();
    }
    
    public void setCustomerAddressText(String CustomerAddressText) {
        customerAddressText.set(CustomerAddressText);
    }      
    
    public String getCustomerAddressText2(){
        return customerAddressText2.get();
    }
    
    public void setCustomerAddressText2(String CustomerAddressText2) {
        customerAddressText2.set(CustomerAddressText2);
    }    
    
    public String getCustomerPostalCode(){
        return customerPostalCode.get();
    }
    
    public void setCustomerPostalCode(String CustomerPostalCode) {
        customerPostalCode.set(CustomerPostalCode);
    }    
    
    public String getCustomerPhoneNumber(){
        return customerPhoneNumber.get();
    }
    
    public void setCustomerPhoneNumber(String CustomerPhoneNumber) {
        customerPhoneNumber.set(CustomerPhoneNumber);
    }    
    
    public int getCustomerCityID(){
        return customerCityID.get();
    }
    
    public void setCustomerCityID(int CustomerCityID) {
        customerCityID.set(CustomerCityID);
    }      
    
    public String getCustomerCity(){
        return customerCity.get();
    }
    
    public void setCustomerCity(String CustomerCity) {
        customerCity.set(CustomerCity);
    }    
    
    public int getCustomerCountryID(){
        return customerCountryID.get();
    }
    
    public void setCustomerCountryID(int CustomerCountryID) {
        customerCountryID.set(CustomerCountryID);
    }      
    
    public String getCustomerCountry(){
        return customerCountry.get();
    }
    
    public void setCustomerCountry(String CustomerCountry) {
        customerCountry.set(CustomerCountry);
    }    
    
    public java.sql.Timestamp getCustomerDateAdded(){
        return dateAdded;
    }
    
    public void setCustomerDateAdded(java.sql.Timestamp date) {
        dateAdded = date;
    }    
    public String getCustomerAddedBy(){
        return addedBy.get();
    }
    
    public void setCustomerAddedBy(String person) {
        addedBy.set(person);
    }    
}
