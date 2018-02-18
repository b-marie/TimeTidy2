/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Britt
 */
public class BHellerSoftwareII extends Application {
    public static Scene mainScene;
    static Stage stage1;
    
//    static Stage stage2;
    
    
    @Override
    public void start(Stage stage) throws IOException {
        DBConnection db=new DBConnection();
//        db.connect;
        
        
        stage1 = stage;
        
        Parent main = FXMLLoader.load(getClass().getResource("login.fxml"));
        
        mainScene = new Scene(main);
        
        stage.setScene(mainScene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
