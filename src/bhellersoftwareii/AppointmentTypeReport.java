/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.net.URL;
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
public class AppointmentTypeReport implements Initializable {

    String reportTitle = "Appointment Type by Month Report";
    /**
     * Initializes the controller class.
     */
    
    
    private static ObservableList<CustomerAppointmentMonthTitle> monthAppointmentList = FXCollections.observableArrayList();
    
    @FXML
    private Label ReportLabel;

    @FXML
    private TableView<CustomerAppointmentMonthTitle> ReportTableView;

    @FXML
    private TableColumn<CustomerAppointmentMonthTitle, String> ReportTableViewColumn1;

    @FXML
    private TableColumn<CustomerAppointmentMonthTitle, String> ReportTableViewColumn2;

    @FXML
    private Button ReportCloseButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReportTableViewColumn1.setCellValueFactory(new PropertyValueFactory<CustomerAppointmentMonthTitle, String>("appointmentMonth"));
        ReportTableViewColumn2.setCellValueFactory(new PropertyValueFactory<CustomerAppointmentMonthTitle, String>("appointmentTitle"));
        appointmentTypeByMonthReport();

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

    public void appointmentTypeByMonthReport() {
        monthAppointmentList.clear();
        for(CustomerAppointment a : CustomerAppointment.appointmentList) {
            System.out.println("Got to appointment maker!");
            String startTime = a.getStartTime();
            String apptTitle = a.getAppointmentTitle();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("M-d-yyyy hh:mm a");
            LocalDate dateToReturn = LocalDate.parse(startTime, format);
            Month month = dateToReturn.getMonth();
            String monthInString = month.toString();
            System.out.println(monthInString);
            CustomerAppointmentMonthTitle appointmentType = new CustomerAppointmentMonthTitle(monthInString, apptTitle);
            monthAppointmentList.add(appointmentType);
        }
        
        ReportTableView.setItems(monthAppointmentList);
        
        
    }
    
}
