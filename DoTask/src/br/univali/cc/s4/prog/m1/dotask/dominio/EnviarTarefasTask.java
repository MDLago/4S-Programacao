/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.s4.prog.m1.dotask.dominio;

import br.univali.cc.s4.prog.m1.dotask.persistencia.GerenciadorDadosMYSQL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

/**
 *
 * @author marco
 */
public class EnviarTarefasTask extends TimerTask{

    @Override
    public void run() {
        Collection<Tarefa> tarefas = new ArrayList<>();
        
        try{
            tarefas = new GerenciadorDadosMYSQL().getTarefasEnviar();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!tarefas.isEmpty()){
            Timer timer =  new Timer();
            for (Tarefa tarefa : tarefas) {
                timer.schedule(tarefa, 0);
            }
        }
    }
    
}
