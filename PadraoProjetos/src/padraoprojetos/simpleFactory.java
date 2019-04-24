/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padraoprojetos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 5586658
 */
public class simpleFactory {
    
    public static Connection createJDBCConnection() throws SQLException{
        String url = "";
        String user = "";
        String pass = "";
        
        return DriverManager.getConnection(url, user, pass);
    }
}
