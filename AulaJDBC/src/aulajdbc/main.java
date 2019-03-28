/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aulajdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author 5586658
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        String url ="jdbc:mysql://localhost:3306/test";
        String usuario = "root";
        String senha = "";
        Connection con = DriverManager.getConnection(url, usuario, senha);
        
        String sql;
        /*
        String nome = "Elieser";
        String email = "teste@gmail";
        sql = "DELETE FROM clientes";
        */
        
        sql = "SELECT * FROM clientes";
        
        PreparedStatement st = con.prepareStatement(sql);
        
        /*
        st.execute();
        */
        
        ResultSet rs = st.executeQuery();
        
        while(rs.next()){
             int id = rs.getInt("id");
        String nome = rs.getString("nome");
        String email = rs.getString("email");
        
        System.out.println(id+"  "+nome+"  "+email);
        }
        con.close();
        
       
    }
    
}
