/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bhellersoftwareii;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Britt
 */
public class DBConnection {
    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
    
public DBConnection() {}

public void connect() throws SQLException{
    try {
        //Load the driver
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        //Create a connection
        con=DriverManager.getConnection("jdbc.mysql://52.206.157.109/U04vDR?+user=U04vDR&password=53688357932");
                
    }   catch (Exception e) {
            e.printStackTrace();
        }
}


}
