/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import bhellersoftwareii.customer;
import bhellersoftwareii.HomeController;
import static bhellersoftwareii.HomeController.data;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Britt
 */
public class AddCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    public static Date asDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }
    
    @FXML
    private TextField NewCustCustNameEntry;

    @FXML
    private TextField NewCustAddressEntry;

    @FXML
    private TextField NewCustAddress2Entry;

    @FXML
    private TextField NewCustPhoneNumberEntry;

    @FXML
    private TextField NewCustZipCodeEntry;

    @FXML
    private TextField NewCustCityEntry;

    @FXML
    private TextField NewCustCountryEntry;

    @FXML
    private Button NewCustSaveButton;

    @FXML
    private Button NewCustCancelButton;

    @FXML
    void NewCustCancelButtonClicked(ActionEvent event) {
        //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Customer will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        //Close the window
        confirm.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) NewCustCancelButton.getScene().getWindow();
                stage.close();
            }
            }));
    }

    @FXML
    void NewCustSaveButtonClicked(ActionEvent event) {
        System.out.println("New customer save button pressed");
        //Collect New Customer information
        String custName = NewCustCustNameEntry.getText();
        String custAddress1 = NewCustAddressEntry.getText();
        String custAddress2 = NewCustAddress2Entry.getText();
        String custPhoneNum = NewCustPhoneNumberEntry.getText();
        String custZipCode = NewCustZipCodeEntry.getText();
        String custCity = NewCustCityEntry.getText();
        String custCountry = NewCustCountryEntry.getText();
        Boolean active = true;
        java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String person = HomeController.currentUser;

        //Save new customer information to the database
        if(addCustomer(custCountry, today, person, 
        custCity, custAddress1, custAddress2, custZipCode, 
        custPhoneNum, custName, active)) {
                //Close the current stage
                Stage stage = (Stage) NewCustSaveButton.getScene().getWindow();
                stage.close();
            } else {
                Alert invalidCredentialsAlert = new Alert(Alert.AlertType.WARNING);
                invalidCredentialsAlert.setTitle("Invalid Action");
                invalidCredentialsAlert.setHeaderText("There was a problem");
                invalidCredentialsAlert.setContentText("Customer information was not saved");

                invalidCredentialsAlert.showAndWait();
            } 
           //Update the Customer Records Table
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlurl = "jdbc:mysql://52.206.157.109/U04vDR";
                    String user = "U04vDR";
                    String pass = "53688357932";
                    Connection conn = DriverManager.getConnection(sqlurl, user, pass);
                    if(conn != null) {
                        PreparedStatement SQL = conn.prepareStatement("SELECT customer.customerName, address.address, address.address2, city.city, country.country, address.phone FROM U04vDR.customer JOIN address on customer.addressId = address.addressId JOIN city on address.cityId = city.cityId JOIN country on city.countryId = country.countryId WHERE customer.customerName = ? ");
                        SQL.setString(1, custName);
                        ResultSet rs = SQL.executeQuery();
                        System.out.println(rs);
                        while(rs.next()) {
                            customer cm = new customer();
                            cm.setCustomerName(rs.getString("customerName"));
                            cm.setCustomerAddressText(rs.getString("address"));
                            cm.setCustomerAddressText2(rs.getString("address2"));
                            cm.setCustomerCity(rs.getString("city"));
                            cm.setCustomerCountry(rs.getString("country"));
                            cm.setCustomerPhoneNumber(rs.getString("phone"));
                            HomeController.data.add(cm);
                            System.out.println(cm);
                        }
                    }
                    conn.close();
//                    HomeController.CustomerRecordsTable.setItems(HomeController.data);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    private boolean addCustomer(String custCountry, java.sql.Date today, String person, 
        String custCity, String custAddress1, String custAddress2, String custZipCode, 
        String custPhoneNum, String custName, Boolean active){
        try{
                    Class.forName("com.mysql.jdbc.Driver");

                    String url = "jdbc:mysql://52.206.157.109/U04vDR";
                    String user = "U04vDR";
                    String pass = "53688357932";
                    int countryID = 1;
                    int cityID = 1;
                    int addressID = 1;
                    int customerID = 1;
                    Connection conn = DriverManager.getConnection(url, user, pass);
                    if(conn != null) {
                        System.out.println("Connected to the database!");
                        //Query if country exists or not
                        try{
                            PreparedStatement countryQuery = conn.prepareStatement("SELECT countryId FROM country WHERE country = ?");
                            countryQuery.setString(1, custCountry);
                            ResultSet returnedCountry = countryQuery.executeQuery();
                            if(returnedCountry.next()) {
                                //If it exists, return the country ID
                                    countryID = returnedCountry.getInt(1);
                                    System.out.println("Found country ID is: " + countryID);
                            } else {
                                //Else insert the country
                                try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO country (country, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                                stmt.setString(1, custCountry);
                                stmt.setDate(2, today);
                                stmt.setString(3, person);
                                stmt.setDate(4, today);
                                stmt.setString(5, person);
                                stmt.executeUpdate();
                                ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()) {
                                    countryID = rs.getInt(1);
                                    System.out.println("New country ID is: " + countryID);
                                }
                                }  
                            }
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                        try{
                            //Query if city exists or not
                            PreparedStatement cityQuery = conn.prepareStatement("SELECT cityId FROM city WHERE city = ?");
                            cityQuery.setString(1, custCity);
                            ResultSet returnedCity = cityQuery.executeQuery();
                            if(returnedCity.next()) {
                                //If it exists, return the city ID
                                    cityID = returnedCity.getInt(1);
                                    System.out.println("Found city ID is: " + cityID);
                            } else {
                                //Else insert the city
                                try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO city (city, countryId, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                                stmt.setString(1, custCity);
                                stmt.setInt(2, countryID);
                                stmt.setDate(3, today);
                                stmt.setString(4, person);
                                stmt.setDate(5, today);
                                stmt.setString(6, person);
                                stmt.executeUpdate();
                                ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()) {
                                    cityID = rs.getInt(1);
                                    System.out.println("New city ID is: " + cityID);
                                }
                                }  
                            }
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                        //Insert address with city ID
                        try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                                stmt.setString(1, custAddress1);
                                stmt.setString(2, custAddress2);
                                stmt.setInt(3, cityID);
                                stmt.setString(4, custZipCode);
                                stmt.setString(5, custPhoneNum);
                                stmt.setDate(6, today);
                                stmt.setString(7, person);
                                stmt.setDate(8, today);
                                stmt.setString(9, person);
                                stmt.executeUpdate();
                                ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()) {
                                    addressID = rs.getInt(1);
                                    System.out.println("New address ID is: " + addressID);
                                }
                                } 
                        //Insert person with address ID
                        try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
                                stmt.setString(1, custName);
                                stmt.setInt(2, addressID);
                                stmt.setBoolean(3, active);
                                stmt.setDate(4, today);
                                stmt.setString(5, person);
                                stmt.setDate(6, today);
                                stmt.setString(7, person);
                                stmt.executeUpdate();
                                ResultSet rs = stmt.getGeneratedKeys();
                                if(rs.next()) {
                                    customerID = rs.getInt(1);
                                    System.out.println("New customer ID is: " + customerID);
                                }
                                rs.close();
                                stmt.close();
                                } 
                        //Return true
                        conn.close();
                        return true;
                    } else {
                        conn.close();
                        System.out.println("Something didn't work");
                        return false;
                    }

                } catch(Exception e){
                e.printStackTrace();
                return false;

        }
    }
    
    
    }
    
    
    
