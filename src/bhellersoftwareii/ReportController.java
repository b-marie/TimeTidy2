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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class ReportController implements Initializable {

    String reportTitle = "";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ReportLabel.setText(HomeController.reportTitle);
        reportTitle = HomeController.reportTitle;
    }
    @FXML
    private Label ReportLabel;

    @FXML
    private TableView<CustomerAppointment> ReportTableView;

    @FXML
    private TableColumn<?, ?> ReportTableViewColumn1;

    @FXML
    private TableColumn<?, ?> ReportTableViewColumn2;

    @FXML
    private Button ReportCloseButton;

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
        
    }
    
    public void consultantScheduleReport() {
        
    }
}
