/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import bhellersoftwareii.customer;
import bhellersoftwareii.HomeController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class CustomerSearchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CustomerSearchNameCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerName"));
        CustomerSearchAddressCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText"));
        CustomerSearchAddress2Col.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText2"));
        CustomerSearchCityCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCity"));
        CustomerSearchCountryCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCountry"));
        CustomerSearchPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerPhoneNumber"));
        CustomerRecordsSearchTable.setItems(HomeController.data);
        
//        FXMLLoader firstLoader = new FXMLLoader(getClass().getResource("AddAppointmentController.java"));
//        Parent first = firstLoader.load();
//        FirstController firstController = firstLoader.getController();
        
//        selectedValueProperty().addListener((obs, oldValue, newValue) -> 
//            AddAppointmentController.setCustID(newValue));
//        
    }    
    
    public static String custID = "0";
    
    @FXML
    private TableView<customer> CustomerRecordsSearchTable;
    
    @FXML
    private TableView<customer> CustomerRecordsSearchTable2;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerSearchNameCol;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerSearchAddressCol;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerSearchAddress2Col;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerSearchCityCol;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerSearchCountryCol;

    @FXML
    private TableColumn<customer, SimpleStringProperty> CustomerSearchPhoneNumberCol;

    @FXML
    private Button CustomerSearchSearchButtonon;

    @FXML
    private TextField CustomerSearchTextEntry;

    @FXML
    private Button CustomerSearchSelectButton;

    @FXML
    private Button CustomerSearchCancelButton;
    
//    StringProperty selectedValue = new SimpleStringProperty(this, "selectedValue", "");
//    
//    StringProperty selectedValueProperty() {
//        return selectedValue;
//    }
//    
//    void setSelectedValue(String value){
//        selectedValue.set(value);
//    }
//    
//    String getSelectedValue() {
//        return selectedValue.get();
//    }
    
    static customer selectCustomer = new customer();
    static int selectedCustomerID = 0;
    public ObservableList<customer> data2 = FXCollections.observableArrayList();

    @FXML
    void CustomerSearchCancelButtonClicked(ActionEvent event) {
        System.out.println("Customer Search Cancel Button was clicked!");
        Stage stage = (Stage) CustomerSearchCancelButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void CustomerSearchSearchButtonClicked(ActionEvent event) {
        System.out.println("Customer Search Search Button was clicked!");
        
        String searchItem=CustomerSearchTextEntry.getText(); 
          if (searchItem.equals("")){
            data2 = CustomerRecordsSearchTable.getItems();
            data2.add(CustomerRecordsSearchTable.getSelectionModel().getSelectedItem()); 
                    CustomerSearchNameCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerName"));
                    CustomerSearchAddressCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText"));
                    CustomerSearchAddress2Col.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText2"));
                    CustomerSearchCityCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCity"));
                    CustomerSearchCountryCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCountry"));
                    CustomerSearchPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerPhoneNumber"));

                CustomerRecordsSearchTable.setItems(data2);
        }
          else{
    boolean found=false;
    try{
        String custName = searchItem;
        for(customer p: HomeController.data){
            if(p.getCustomerName() ==  custName){
                System.out.println("This is customer "+ custName);
                found=true;

                data2 = CustomerRecordsSearchTable.getItems();
                data2.add(p);

                    CustomerSearchNameCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerName"));
                    CustomerSearchAddressCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText"));
                    CustomerSearchAddress2Col.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText2"));
                    CustomerSearchCityCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCity"));
                    CustomerSearchCountryCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCountry"));
                    CustomerSearchPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerPhoneNumber"));

                CustomerRecordsSearchTable.setItems(data2);
            
            }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error!");
            alert.setContentText("Customer not found");

            alert.showAndWait();
        }
    }
    catch(NumberFormatException e){
        for(customer p: HomeController.data){
            if(p.getCustomerName().equals(searchItem)){
                System.out.println("This is customer  " + p.getCustomerName());
                found=true;

               data2 = CustomerRecordsSearchTable.getItems();
                data2.add(p);
  

                CustomerSearchNameCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerName"));
                CustomerSearchAddressCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText"));
                CustomerSearchAddress2Col.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerAddressText2"));
                CustomerSearchCityCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCity"));
                CustomerSearchCountryCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerCountry"));
                CustomerSearchPhoneNumberCol.setCellValueFactory(new PropertyValueFactory<customer, SimpleStringProperty>("customerPhoneNumber"));
                CustomerRecordsSearchTable.setItems(data2);
            }
            
        }
            if (found==false){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Error");
            alert.setContentText("Customer not found");

            alert.showAndWait();
        }
    }
    } 
    }
    
//    public void updateIDInApptController(String value) {
//        AddAppointmentController.setCustID(value);
//    }

    @FXML
    public void CustomerSearchSelectButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Customer Search Select Button was clicked!");
        selectCustomer = CustomerRecordsSearchTable.getSelectionModel().getSelectedItem();
        System.out.println(selectCustomer.getCustomerName());
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String sqlurl = "jdbc:mysql://52.206.157.109/U04vDR";
            String user = "U04vDR";
            String pass = "53688357932";
                    Connection con = DriverManager.getConnection(sqlurl, user, pass);
                    if(con != null) {
                        PreparedStatement SQL = con.prepareStatement("SELECT customer.customerId FROM U04vDR.customer WHERE customer.customerName = ? ");
                        SQL.setString(1, selectCustomer.getCustomerName());
                        ResultSet rs = SQL.executeQuery();
                        System.out.println(rs);
                        while(rs.next()) {
                            selectedCustomerID = (rs.getInt("customerId"));
                        }
                        rs.close();
                    }
                    con.close();
        } catch(Exception e){
            e.printStackTrace();

        }
        //Bring customer ID back into the appointment box
        System.out.println(selectedCustomerID);
        custID = Integer.toString(selectedCustomerID);
//        setSelectedValue(Integer.toString(selectedCustomerID));
//        AddAppointmentsController.ID = (Integer.toString(selectedCustomerID));
//        AddAppointmentController.setCustID(AddAppointmentController.ID);
        Parent newAppt = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        Scene newApptScene = new Scene(newAppt);
        Stage stage = (Stage) CustomerSearchSelectButton.getScene().getWindow();
        stage.setScene(newApptScene);
        stage.show();
    }
    
}
