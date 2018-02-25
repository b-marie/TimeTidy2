/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Britt
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameEntry;

    @FXML
    private PasswordField passwordEntry;

    @FXML
    private Button loginButton;

    @FXML
    private Button frenchButton;

    @FXML
    void frenchButtonPressed(ActionEvent event) {
        System.out.println("French button was pressed!");
    }
    
    private boolean validateLogin(String username, String password) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            String url = "jdbc:mysql://52.206.157.109/U04vDR";
            String user = "U04vDR";
            String pass = "53688357932";
            Connection conn = DriverManager.getConnection(url, user, pass);
            if(conn != null) {
                System.out.println("Connected to the database!");
                PreparedStatement pst = conn.prepareStatement("Select * from user where userName=? and password=?");
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                
                if(rs.next()) {
                    return true;
                } else {
                    return false;
            }
            } else {
                System.out.println("Something didn't work");
                return false;
            }
            
        } catch(Exception e){
        e.printStackTrace();
        return false;
    }
}

    @FXML
    void loginButtonPressed(ActionEvent event) throws IOException {
        
        String userName = usernameEntry.getText();
        String password = passwordEntry.getText();
        
        if(userName.isEmpty()){
           Alert usernameEntryAlert = new Alert(Alert.AlertType.WARNING);
           usernameEntryAlert.setTitle("Invalid Username");
           usernameEntryAlert.setHeaderText("There was a problem");
           usernameEntryAlert.setContentText("You must enter a username!");
           
           usernameEntryAlert.showAndWait();
        } else if(password.isEmpty()) {
           Alert passwordEntryAlert = new Alert(Alert.AlertType.WARNING);
           passwordEntryAlert.setTitle("Invalid Password");
           passwordEntryAlert.setHeaderText("There was a problem");
           passwordEntryAlert.setContentText("You must enter a password!");
           
           passwordEntryAlert.showAndWait();
        } else {
            if(validateLogin(userName, password)) {
                //Open the home page
                Parent home = FXMLLoader.load(getClass().getResource("home.fxml"));
                Scene homePageScene = new Scene(home);
                Stage imsStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                imsStage.setScene(homePageScene);
                imsStage.show();
            } else {
                Alert invalidCredentialsAlert = new Alert(Alert.AlertType.WARNING);
                invalidCredentialsAlert.setTitle("Invalid Login Credentials");
                invalidCredentialsAlert.setHeaderText("There was a problem");
                invalidCredentialsAlert.setContentText("Your login credentials are invalid.");

                invalidCredentialsAlert.showAndWait();
            } 
    }
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
