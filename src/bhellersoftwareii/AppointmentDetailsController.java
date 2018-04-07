/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import bhellersoftwareii.CustomerAppointment;
import bhellersoftwareii.MonthAppointmentButton;
import bhellersoftwareii.HomeController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class AppointmentDetailsController implements Initializable {

    
    public static CustomerAppointment currentAppointment = new CustomerAppointment();
    String clickedButton = HomeController.getClickedButtonID();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("Appointment ID for this button is " + clickedButton);
        ApptIDLabel.setText(clickedButton);
        CustomerAppointment thisAppointment = CustomerAppointment.getAppointmentDetails(clickedButton);
        ApptTitleLabel.setText(thisAppointment.getAppointmentTitle());
        ApptDescriptionLabel.setText(thisAppointment.getAppointmentDescription());
        ApptLocationLabel.setText(thisAppointment.getAppointmentLocation());
        ApptContactLabel.setText(thisAppointment.getAppointmentContact());
        ApptURLLabel.setText(thisAppointment.getAppointmentURL());
        ApptStartTimeLabel.setText(thisAppointment.getStartTime());
        ApptEndTimeLabel.setText(thisAppointment.getEndTime());
        ApptLastUpdatedByLabel.setText(thisAppointment.getAppointmentLastUpdatedBy());
    }    
    
    @FXML
    private Button ApptDetailsCloseButton;

    @FXML
    private Button ApptDetailsEditButton;

    @FXML
    private Button ApptDetailsDeleteButton;

    @FXML
    private Label ApptTitleLabel;

    @FXML
    private Label ApptDescriptionLabel;

    @FXML
    private Label ApptLocationLabel;

    @FXML
    private Label ApptIDLabel;

    @FXML
    private Label ApptContactLabel;

    @FXML
    private Label ApptURLLabel;

    @FXML
    private Label ApptStartTimeLabel;

    @FXML
    private Label ApptEndTimeLabel;

    @FXML
    private Label ApptLastUpdatedByLabel;

    @FXML
    void ApptDetailsCloseButtonClicked(ActionEvent event) {
       Stage stage = (Stage) ApptDetailsCloseButton.getScene().getWindow();
       stage.close();
    }

    @FXML
    void ApptDetailsDeleteButtonClicked(ActionEvent event) {
        System.out.println("Appointment Delete button clicked");
        //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Confirm Delete Appointment");
        confirm.setContentText("Are you sure you wish to delete this appointment?");

        //Close the window
        confirm.showAndWait().ifPresent((response -> {
            int apptID = Integer.parseInt(ApptIDLabel.getText());
            if (response == ButtonType.OK) {
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
                            PreparedStatement apptUpdateQuery = conn.prepareStatement("DELETE FROM appointment WHERE appointmentId = ?");
                            apptUpdateQuery.setInt(1, apptID);
                            apptUpdateQuery.executeUpdate();
                            System.out.println("Appointment at ID " + apptID + " has been deleted!");
                            apptUpdateQuery.close();
                        }catch(Exception e) {
                            e.printStackTrace();
                        }
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }

                Stage stage = (Stage) ApptDetailsDeleteButton.getScene().getWindow();
                stage.close();
            }
            }));
        
    }

    @FXML
    void ApptDetailsEditButtonClicked(ActionEvent event) throws IOException {
        System.out.println("Appointment Edit button clicked");
        HomeController.setClickedButtonID(ApptIDLabel.getText());
        System.out.println("Clicked button ID in appointment details is " + HomeController.getClickedButtonID());
        Parent modAppt = FXMLLoader.load(getClass().getResource("ModifyAppointment.fxml"));
        Scene modApptScene = new Scene(modAppt);
        Stage modApptStage = new Stage();
        modApptStage.setScene(modApptScene);
        modApptStage.show();

    }
    
    
    
}
