/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class CustomerInteractionReport implements Initializable {

    String reportTitle = "Appointment Type by Month Report";
    /**
     * Initializes the controller class.
     */
    
    
    private static ObservableList<CustomerInteractionObject> customerInteractionList = FXCollections.observableArrayList();
    
    @FXML
    private Label ReportLabel;

    @FXML
    private TableView<CustomerInteractionObject> ReportTableView;

    @FXML
    private TableColumn<CustomerInteractionObject, String> ConsultantCol;

    @FXML
    private TableColumn<CustomerInteractionObject, String> CustNameCol;

    @FXML
    private TableColumn<CustomerInteractionObject, String> DateAddedCol;

    @FXML
    private Button ReportCloseButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ConsultantCol.setCellValueFactory(new PropertyValueFactory<CustomerInteractionObject, String>("consultantName"));
        CustNameCol.setCellValueFactory(new PropertyValueFactory<CustomerInteractionObject, String>("customerName"));
        DateAddedCol.setCellValueFactory(new PropertyValueFactory<CustomerInteractionObject, String>("dateAdded"));
        consultantScheduleReport();

    }

    @FXML
    void ReportCloseButtonClicked(ActionEvent event) {
        //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Leaving Report");
        confirm.setContentText("Are you sure you wish to leave?");

        //Close the window
        confirm.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) ReportCloseButton.getScene().getWindow();
                stage.close();
            }
            }));
    }

    public void consultantScheduleReport() {
        customerInteractionList.clear();
        for(customer c : HomeController.data) {
            String consultantName = c.getCustomerAddedBy();
            String custName = c.getCustomerName();
            java.sql.Timestamp date = c.getCustomerDateAdded();
            String dateAdded = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            System.out.println(dateAdded);
            CustomerInteractionObject customerObject = new CustomerInteractionObject(consultantName, custName, dateAdded);
            customerInteractionList.add(customerObject);
        }
        
        ReportTableView.setItems(customerInteractionList);
        
        
    }
    
}
