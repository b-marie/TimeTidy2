/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import bhellersoftwareii.CustomerAppointment;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 *
 * @author Britt
 */
public class AlertReminder {
    
    
    
    
    public static void checkNext15MinutesAndAlert(){
        for(CustomerAppointment appt : CustomerAppointment.getAllAppointments()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M-d-yyyy hh:mm a");
            LocalDateTime appointmentTime = LocalDateTime.parse(appt.getStartTime(), formatter);
            LocalDateTime now = LocalDateTime.now();
            long minutes = ChronoUnit.MINUTES.between(now, appointmentTime);
            if(minutes <= 15 && minutes > 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reminder!");
                alert.setHeaderText("This is a reminder!");
                alert.setContentText("There's an appointment with " + appt.getAppointmentContact() + "  at " + appt.getStartTime());
            
                alert.showAndWait().ifPresent((response -> {
                        if (response == ButtonType.OK) {
                            System.out.println("You've been reminded!");
                        }
            }));
                
            }
        }
    }
    
}
