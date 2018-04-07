/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class ModifyAppointmentController implements Initializable {

    String clickedButton = HomeController.getClickedButtonID();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        NewApptApptIDEntry.setText(clickedButton);
        CustomerAppointment thisAppointment = new CustomerAppointment().getAppointmentDetails(clickedButton);
        NewApptTitleTextEntry.setText(thisAppointment.getAppointmentTitle());
        NewApptDescTextEntry.setText(thisAppointment.getAppointmentDescription());
        NewApptLocTextEntry.setText(thisAppointment.getAppointmentLocation());
        NewApptContactTextEntry.setText(thisAppointment.getAppointmentContact());
        NewApptURLTextEntry.setText(thisAppointment.getAppointmentURL());
        NewApptStartTimeEntry.setText(thisAppointment.getStartTime());
        NewApptEndTimeEntry.setText(thisAppointment.getEndTime());
    }    
    
    @FXML
    private TextField NewApptApptIDEntry;

    @FXML
    private TextField NewApptCustIDEntry;

    @FXML
    private DatePicker NewApptDatePicker;

    @FXML
    private TextField NewApptStartTimeEntry;

    @FXML
    private TextField NewApptEndTimeEntry;

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
    private Button ModifyApptCancelButton;

    @FXML
    private Button ModifyApptSaveButton;

    @FXML
    void ModifyApptCancelButtonPressed(ActionEvent event) {
         //Confirm they want to cancel
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Appointment will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

        //Close the window
        confirm.showAndWait().ifPresent((response -> {
            if (response == ButtonType.OK) {
                Stage stage = (Stage) ModifyApptCancelButton.getScene().getWindow();
                stage.close();
            }
            }));

    }

    @FXML
    void ModifyApptSaveButtonPressed(ActionEvent event) {

    }
    
}
