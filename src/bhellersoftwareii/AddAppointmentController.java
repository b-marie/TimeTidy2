/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    }    
    
    @FXML
    private TextField NewApptApptIDEntry;

    @FXML
    private TextField NewApptCustIDEntry;

    @FXML
    private TextField NewApptTitleTextEntry;

    @FXML
    private TextField NewApptDescTextEntry;

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
        System.out.println("Cancel button pressed");
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Please confirm");
        confirm.setHeaderText("Appointment will not be saved");
        confirm.setContentText("Are you sure you wish to exit without saving?");

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
    }
}
