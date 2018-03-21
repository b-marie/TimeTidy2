/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class AddAppointmentController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        NewApptCustIDEntry = new TextField();
        NewApptCustIDEntry.setText(CustomerSearchController.custID);
    }    
    
//    static String ID = "0";
    
    @FXML
    private TextField NewApptApptIDEntry;
    
    @FXML
    Label custIDLabel;

    @FXML
    static TextField NewApptCustIDEntry;

    @FXML
    private TextField NewApptTitleTextEntry;

    @FXML
    private TextArea NewApptDescTextEntry;

    @FXML
    private TextField NewApptLocTextEntry;

    @FXML
    private TextField NewApptContactTextEntry;

    @FXML
    private TextField NewApptURLTextEntry;

    @FXML
    private DatePicker NewApptDatePicker;

    @FXML
    private TextField NewApptStartTimeEntry;

    @FXML
    private TextField NewApptEndTimeEntry;

    @FXML
    private Button NewApptCancelButton;

    @FXML
    private Button NewApptSaveButton;

    @FXML
    void NewApptCancelButtonPressed(ActionEvent event) throws IOException {
        //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Appointment will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        //Close the window
        Optional<ButtonType> result = confirm.showAndWait();
        if (result.get() == ButtonType.OK){
            Stage stage = (Stage) NewApptCancelButton.getScene().getWindow();
            stage.close();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    void NewApptSaveButtonPressed(ActionEvent event) {
        System.out.println("Save button pressed");
        //Save data from this form to database
//        System.out.println("New customer save button pressed");
//        //Collect New Customer information
//        String custName = NewCustCustNameEntry.getText();
//        String custAddress1 = NewCustAddressEntry.getText();
//        String custAddress2 = NewCustAddress2Entry.getText();
//        String custPhoneNum = NewCustPhoneNumberEntry.getText();
//        String custZipCode = NewCustZipCodeEntry.getText();
//        String custCity = NewCustCityEntry.getText();
//        String custCountry = NewCustCountryEntry.getText();
//        Boolean active = true;
//        java.sql.Date today = new java.sql.Date(Calendar.getInstance().getTime().getTime());
//        String person = HomeController.currentUser;
//
//        //Save new customer information to the database
//        if(addCustomer(custCountry, today, person, 
//        custCity, custAddress1, custAddress2, custZipCode, 
//        custPhoneNum, custName, active)) {
//                //Close the current stage
//                Stage stage = (Stage) NewCustSaveButton.getScene().getWindow();
//                stage.close();
//            } else {
//                Alert invalidCredentialsAlert = new Alert(Alert.AlertType.WARNING);
//                invalidCredentialsAlert.setTitle("Invalid Action");
//                invalidCredentialsAlert.setHeaderText("There was a problem");
//                invalidCredentialsAlert.setContentText("Customer information was not saved");
//
//                invalidCredentialsAlert.showAndWait();
//            } 
//           //Update the Customer Records Table
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            String sqlurl = "jdbc:mysql://52.206.157.109/U04vDR";
//                    String user = "U04vDR";
//                    String pass = "53688357932";
//                    Connection conn = DriverManager.getConnection(sqlurl, user, pass);
//                    if(conn != null) {
//                        PreparedStatement SQL = conn.prepareStatement("SELECT customer.customerName, address.address, address.address2, city.city, country.country, address.phone FROM U04vDR.customer JOIN address on customer.addressId = address.addressId JOIN city on address.cityId = city.cityId JOIN country on city.countryId = country.countryId WHERE customer.customerName = ? ");
//                        SQL.setString(1, custName);
//                        ResultSet rs = SQL.executeQuery();
//                        System.out.println(rs);
//                        while(rs.next()) {
//                            customer cm = new customer();
//                            cm.customerName.set(rs.getString("customerName"));
//                            cm.customerAddressText.set(rs.getString("address"));
//                            cm.customerAddressText2.set(rs.getString("address2"));
//                            cm.customerCity.set(rs.getString("city"));
//                            cm.customerCountry.set(rs.getString("country"));
//                            cm.customerPhoneNumber.set(rs.getString("phone"));
//                            HomeController.data.add(cm);
//                            System.out.println(cm);
//                        }
//                    }
//                    conn.close();
////                    HomeController.CustomerRecordsTable.setItems(HomeController.data);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    
    @FXML
    void CustIDLinkPressed(ActionEvent event) throws IOException {
        System.out.println("Cust ID Link Pressed");
        //Open another window with a search/table of customers and allow someone to select customers
        Parent newCustSearch = FXMLLoader.load(getClass().getResource("CustomerSearch.fxml"));
        Scene newCustSearchScene = new Scene(newCustSearch);
        Stage custSearchStage = new Stage();
        custSearchStage.setScene(newCustSearchScene);
        custSearchStage.show();
    }
    
    public static void setCustID(String custID) {
        NewApptCustIDEntry.setText(custID);
        System.out.println(NewApptCustIDEntry);
    }
}

