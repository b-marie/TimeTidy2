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
public class ConsultantScheduleReport implements Initializable {

    String reportTitle = "Appointment Type by Month Report";
    /**
     * Initializes the controller class.
     */
    
    
    private static ObservableList<ConsultantScheduleObject> consultantScheduleList = FXCollections.observableArrayList();
    
    @FXML
    private Label ReportLabel;

    @FXML
    private TableView<ConsultantScheduleObject> ReportTableView;

    @FXML
    private TableColumn<ConsultantScheduleObject, String> ConsultantCol;

    @FXML
    private TableColumn<ConsultantScheduleObject, String> ApptStartCol;

    @FXML
    private TableColumn<ConsultantScheduleObject, String> ApptTitleCol;

    @FXML
    private Button ReportCloseButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ConsultantCol.setCellValueFactory(new PropertyValueFactory<ConsultantScheduleObject, String>("consultantName"));
        ApptStartCol.setCellValueFactory(new PropertyValueFactory<ConsultantScheduleObject, String>("appointmentStart"));
        ApptTitleCol.setCellValueFactory(new PropertyValueFactory<ConsultantScheduleObject, String>("appointmentTitle"));
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
        consultantScheduleList.clear();
        for(CustomerAppointment a : CustomerAppointment.appointmentList) {
            String consultantName = a.getAppointmentLastUpdatedBy();
            String startTime = a.getStartTime();
            String apptTitle = a.getAppointmentTitle();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("M-d-yyyy hh:mm a");
            LocalDate dateToReturn = LocalDate.parse(startTime, format);
            String dateInString = dateToReturn.toString();
            ConsultantScheduleObject appointmentType = new ConsultantScheduleObject(consultantName, dateInString, apptTitle);
            consultantScheduleList.add(appointmentType);
        }
        
        ReportTableView.setItems(consultantScheduleList);
        
        
    }
    
}
