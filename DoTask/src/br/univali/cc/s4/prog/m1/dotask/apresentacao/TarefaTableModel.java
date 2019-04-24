/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.s4.prog.m1.dotask.apresentacao;

import java.util.Collection;
import javax.swing.table.AbstractTableModel;
import br.univali.cc.s4.prog.m1.dotask.dominio.Tarefa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 5586658
 */
public class TarefaTableModel extends AbstractTableModel{
    
    private final List<Tarefa> tarefas;
    private final String[] colunas = {"ID","Nome","Criado em","Lembrar","Lembrar em", "TAG","Email"};
            
    public TarefaTableModel(Collection<Tarefa> tarefas) {
        this.tarefas = new ArrayList<>(tarefas);
    }

    @Override
    public int getRowCount() {
        return tarefas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column]; 
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch(coluna){
            case 0: return tarefas.get(linha).getId();
            case 1: return tarefas.get(linha).getNome();
            case 2: return tarefas.get(linha).getCriadoEm();
            case 3: return tarefas.get(linha).getLembrar();
            case 4: return tarefas.get(linha).getLembrarEm();
            case 5: return tarefas.get(linha).getTag();
            case 6: return tarefas.get(linha).getEmail();
        }
        
        return null;
    }
 
    
}
