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
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class ModifyCustomerController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String custNameToModify = HomeController.selectedCustomer.getCustomerName();
        customer custToModify = findCustToModify(custNameToModify);
        System.out.println(custToModify.getCustomerID());
        ModCustCustNameEntry.setText(custToModify.getCustomerName());
        ModCustAddressEntry.setText(custToModify.getCustomerAddressText());
        ModCustAddress2Entry.setText(custToModify.getCustomerAddressText2());
        ModCustPhoneNumberEntry.setText(custToModify.getCustomerPhoneNumber());
        ModCustZipCodeEntry.setText(custToModify.getCustomerPostalCode());
        ModCustCityEntry.setText(custToModify.getCustomerCity());
        ModCustCountryEntry.setText(custToModify.getCustomerCountry());
        if(custToModify.getCustomerActive()) {
            SetCustomerInactiveCheckbox.setSelected(false);
        } else {
            SetCustomerInactiveCheckbox.setSelected(true);
        }
    }    
    
    @FXML
    private TextField ModCustCustNameEntry;

    @FXML
    private TextField ModCustAddressEntry;

    @FXML
    private TextField ModCustAddress2Entry;

    @FXML
    private TextField ModCustPhoneNumberEntry;

    @FXML
    private TextField ModCustZipCodeEntry;

    @FXML
    private TextField ModCustCityEntry;

    @FXML
    private TextField ModCustCountryEntry;

    @FXML
    private Button ModCustSaveButton;

    @FXML
    private Button ModCustCancelButton;
    
    @FXML
    private CheckBox SetCustomerInactiveCheckbox;

    @FXML
    void ModCustCancelButtonClicked(ActionEvent event) {
        //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Customer changes will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        //Close the window
        confirm.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) ModCustCancelButton.getScene().getWindow();
                stage.close();
            }
            }));
    }

    @FXML
    void ModCustSaveButtonClicked(ActionEvent event) {
        String custNameToModify = HomeController.selectedCustomer.getCustomerName();
        customer custToModify = findCustToModify(custNameToModify);
        HomeController.data.remove(HomeController.selectedCustomer);
        System.out.println("Modify customer save button pressed");
        //Collect Updated Customer information
        String custName = ModCustCustNameEntry.getText();
        String custAddress1 = ModCustAddressEntry.getText();
        String custAddress2 = ModCustAddress2Entry.getText();
        String custPhoneNum = ModCustPhoneNumberEntry.getText();
        String custZipCode = ModCustZipCodeEntry.getText();
        String custCity = ModCustCityEntry.getText();
        String custCountry = ModCustCountryEntry.getText();
        Boolean active = true;
        if(SetCustomerInactiveCheckbox.isSelected()) {
            active = false;
        } else {
        }
        java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        String person = HomeController.currentUser;

        //Save new customer information to the database
        if(updateCustomer(custToModify.getCustomerID(), custCountry, custToModify.getCustomerAddressID(), custToModify.getCustomerCityID(), custToModify.getCustomerCountryID(), today, person, 
        custCity, custAddress1, custAddress2, custZipCode, 
        custPhoneNum, custName, active)) {
                //Close the current stage
                Stage stage = (Stage) ModCustSaveButton.getScene().getWindow();
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

    
    customer findCustToModify(String custName) {
        customer customerToModify = new customer();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String sqlurl = "jdbc:mysql://52.206.157.109/U04vDR";
            String user = "U04vDR";
            String pass = "53688357932";
                    Connection con = DriverManager.getConnection(sqlurl, user, pass);
                    if(con != null) {
                        PreparedStatement SQL = con.prepareStatement("SELECT customer.customerId, customer.customerName, customer.addressId, customer.active, address.address, address.address2, address.cityId, address.postalCode, city.city, city.countryId, country.country, address.phone FROM U04vDR.customer JOIN address on customer.addressId = address.addressId JOIN city on address.cityId = city.cityId JOIN country on city.countryId = country.countryId WHERE customer.customerName = ? ");
                        SQL.setString(1, custName);
                        ResultSet rs = SQL.executeQuery();
                        System.out.println(rs);
                        while(rs.next()) {
                            customerToModify.setCustomerID(rs.getInt("customerId"));
                            customerToModify.setCustomerName(rs.getString("customerName"));
                            customerToModify.setCustomerAddressID(rs.getInt("addressId"));
                            customerToModify.setCustomerActive(rs.getBoolean("active"));
                            customerToModify.setCustomerAddressText(rs.getString("address"));
                            customerToModify.setCustomerAddressText2(rs.getString("address2"));
                            customerToModify.setCustomerCityID(rs.getInt("cityId"));
                            customerToModify.setCustomerPostalCode(rs.getString("postalCode"));
                            customerToModify.setCustomerCity(rs.getString("city"));
                            customerToModify.setCustomerCountry(rs.getString("country"));
                            customerToModify.setCustomerCountryID(rs.getInt("countryId"));
                            customerToModify.setCustomerPhoneNumber(rs.getString("phone"));
                            System.out.println(customerToModify);
                        }
                        rs.close();
                    }
                    con.close();
        } catch(Exception e){
            e.printStackTrace();

        }

        return customerToModify;
    }
    
    private boolean updateCustomer(int customerID, String custCountry, int addressID, int cityID, int countryID, java.sql.Date today, String person, 
        String custCity, String custAddress1, String custAddress2, String custZipCode, 
        String custPhoneNum, String custName, Boolean active){
        try{
                    Class.forName("com.mysql.jdbc.Driver");

                    String url = "jdbc:mysql://52.206.157.109/U04vDR";
                    String user = "U04vDR";
                    String pass = "53688357932";
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
                        try(PreparedStatement stmt = conn.prepareStatement("UPDATE address SET address = ?, address2 = ?, cityId = ?, postalCode = ?, phone = ?, lastUpdate = ?, lastUpdateBy = ? WHERE addressId = ?")) {
                                stmt.setString(1, custAddress1);
                                stmt.setString(2, custAddress2);
                                stmt.setInt(3, cityID);
                                stmt.setString(4, custZipCode);
                                stmt.setString(5, custPhoneNum);
                                stmt.setDate(6, today);
                                stmt.setString(7, person);
                                stmt.setInt(8, addressID);
                                stmt.executeUpdate();
                                } 
                        //Insert person with address ID
                        try(PreparedStatement stmt = conn.prepareStatement("UPDATE customer SET customerName = ?, active = ?, lastUpdate = ?, lastUpdateBy = ? WHERE customerId = ?")) {
                                stmt.setString(1, custName);
                                stmt.setBoolean(2, active);
                                stmt.setDate(3, today);
                                stmt.setString(4, person);
                                stmt.setInt(5, customerID);
                                stmt.executeUpdate();
                                System.out.println("Customer at ID " + customerID + " has been updated!");
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
