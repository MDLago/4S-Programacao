/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package padraoprojetos;

import java.sql.Connection;

/**
 *
 * @author 5586658
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws Exception {
        /* Singleton
        for (int i = 0; i < 10; i++) {
            SingleTon teste = SingleTon.getInstance();
        }*/
        
        Connection c = simpleFactory.createJDBCConnection();
  
    }
    
}
