/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.s4.prog.m1.dotask.persistencia;

import br.univali.cc.s4.prog.m1.dotask.dominio.Tarefa;
import br.univali.cc.s4.prog.m1.dotask.util.DateConverter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author 5586658
 */
public class GerenciadorDadosMYSQL {
    private Connection criarConexao() throws SQLException{
        String url = "jdbc:mysql://localhost/test";
        String user = "root";
        String pass = "";
        
        return DriverManager.getConnection(url, user, pass);
    }
    
    public Tarefa getTarefa(int id) throws SQLException {
        String where = "WHERE id = "+id;
        ArrayList<Tarefa> tarefas = new ArrayList<>(getTarefas(where));
        
        if (tarefas.isEmpty()){
            return null;
        }else{
            return tarefas.get(0);
        }
    }
    
    private Collection<Tarefa> getTarefas(String filtro) throws SQLException {
        Collection<Tarefa> tarefas = new ArrayList<>();
        
        Connection con = criarConexao();
        
        String sql = "SELECT * FROM tarefas "+filtro;
        
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next()){
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            Date criadoEm = DateConverter.dateSqlToJava(rs.getTimestamp("criado_em"));
            boolean lembrar = rs.getBoolean("lembrar");
            Date lembrarEm = DateConverter.dateSqlToJava(rs.getTimestamp("lembrar_em"));
            String tag = rs.getString("tag");
            String email = rs.getString("email");
            
            tarefas.add(new Tarefa(id, nome, criadoEm, lembrar, lembrarEm, tag, email));
        }
        
        return tarefas;
    }
    public Collection<Tarefa> getTodasTarefas() throws SQLException{
        String where = "";
        
        return getTarefas(where);
    }
    
    public Collection<Tarefa> getTarefasLembrar() throws SQLException{
        String where = "WHERE lembrar_em >= CURRENT_TIMESTAMP AND LEMBRAR = TRUE;";
        
        return getTarefas(where);
    }
    
    public Collection<Tarefa> getTarefasEnviar() throws SQLException{
        String where = "WHERE CURRENT_TIMESTAMP + INTERVAL 15 MINUTE >= lembrar_em AND lembrar = TRUE";
        
        return getTarefas(where);
    }
    public Collection<Tarefa> getTarefasTag(String tag) throws SQLException{
        String where = "WHERE tag = "+"'"+tag+"'";
        
        return getTarefas(where);
    }
    
    public void cadastrarTarefa
            (String nome, boolean lembrar, Date data_lembrete, String tag, String email)
                throws SQLException{
        
        Connection con = criarConexao();
        String sql = "INSERT INTO `tarefas` (`nome`, `lembrar`, `lembrar_em`, `tag`,`email`) VALUES (?, ?, ?,?, ?);";
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setString(1, nome);
        ps.setBoolean(2, lembrar);
        ps.setTimestamp(3, DateConverter.dateJavaToSql(data_lembrete));
        ps.setString(4, tag);
        ps.setString(5, email);
        
        ps.execute();
        
    }
    
    public void alterarTarefa
        (String nome, boolean lembrar, Date data_lembrete, String tag, String email, int where) 
                throws SQLException{
            
        String sql = "UPDATE `tarefas` SET `nome`= ? ,`lembrar`= ?,`lembrar_em`=?,`tag`=?,`email`=? WHERE id  = ?;";
        
        Connection con = criarConexao();
        PreparedStatement ps = con.prepareStatement(sql);     
        
        ps.setString(1, nome);
        ps.setBoolean(2, lembrar);
        ps.setTimestamp(3, DateConverter.dateJavaToSql(data_lembrete));
        ps.setString(4, tag);
        ps.setString(5, email);
        ps.setInt(6, where);
        
         ps.execute();
    }
    
    public void apagarTarefa(int id) throws SQLException{
        String sql = "DELETE FROM `tarefas` WHERE id = ?;";
        
        Connection con = criarConexao();
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.setInt(1, id);
        
        ps.execute();
    }
    
   
}
