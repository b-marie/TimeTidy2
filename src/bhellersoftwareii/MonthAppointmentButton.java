/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 *
 * @author Britt
 */
public class MonthAppointmentButton extends Button {
    
    @FXML
    private Button newApptButton;
    private String apptID;
    private String btnText;
    public static MonthAppointmentButton clickedButton = new MonthAppointmentButton();
    

    MonthAppointmentButton(String buttonText, String apptID) throws IOException {
        try {
           newApptButton = new Button(buttonText);
            newApptButton.setText("Appointment");
            newApptButton.setId(apptID);
            newApptButton.getStyleClass().add("monthButton");
            this.btnText = buttonText;
            this.apptID = apptID;
            this.setOnMouseClicked(e -> {
                   try {
                       MonthAppointmentButtonClicked();
                   } catch (IOException ex) {
                       Logger.getLogger(MonthAppointmentButton.class.getName()).log(Level.SEVERE, null, ex);
                   }
               }); 
            } catch(Exception e) {
            e.printStackTrace();
            }       
        
    }

    MonthAppointmentButton() {
        
    }
    
    public Button getButton(){
        return this.newApptButton;
    }
    
    public Button appointmentButton (int apptID, String text){
        Button apptBtn = new Button();
        String appointmentID = Integer.toString(apptID);
        apptBtn.setId(appointmentID);
        apptBtn.setText(text);
        return apptBtn;
    }
    
    public String getApptID() {
        return this.apptID;
    }
    
    public void setApptID(String apptID) {
        this.apptID = apptID;
    }
    
    public void MonthAppointmentButtonClicked() throws IOException {
       System.out.println("Button " + apptID + " was clicked!");
       try{
           clickedButton.setApptID(apptID);
            Parent apptDetails = FXMLLoader.load(getClass().getResource("AppointmentDetails.fxml"));
            Scene apptDetailsScene = new Scene(apptDetails);
            Stage apptDetailsStage = new Stage();
            apptDetailsStage.setScene(apptDetailsScene);
            apptDetailsStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    
}
