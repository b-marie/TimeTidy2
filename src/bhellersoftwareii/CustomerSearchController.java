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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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
    }    
    
    @FXML
    private TableView<?> CustomerRecordsSearchTable;

    @FXML
    private TableColumn<?, ?> CustomerSearchNameCol;

    @FXML
    private TableColumn<?, ?> CustomerSearchAddressCol;

    @FXML
    private TableColumn<?, ?> CustomerSearchAddress2Col;

    @FXML
    private TableColumn<?, ?> CustomerSearchCityCol;

    @FXML
    private TableColumn<?, ?> CustomerSearchCountryCol;

    @FXML
    private TableColumn<?, ?> CustomerSearchPhoneNumberCol;

    @FXML
    private Button CustomerSearchSearchButtonon;

    @FXML
    private TextField CustomerSearchTextEntry;

    @FXML
    private Button CustomerSearchSelectButton;

    @FXML
    private Button CustomerSearchCancelButton;

    @FXML
    void CustomerSearchCancelButtonClicked(ActionEvent event) {

    }

    @FXML
    void CustomerSearchSearchButtonClicked(ActionEvent event) {

    }

    @FXML
    void CustomerSearchSelectButtonClicked(ActionEvent event) {

    }
    
}
