/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.s4.prog.m1.dotask.apresentacao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import br.univali.cc.s4.prog.m1.dotask.persistencia.GerenciadorDadosMYSQL;
import br.univali.cc.s4.prog.m1.dotask.dominio.Tarefa;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 5586658
 */
public class TelaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        
        setLocationRelativeTo(null);
        
        GerenciadorDadosMYSQL gerenciadorDadosMYSQL = new GerenciadorDadosMYSQL();
        
        Collection<Tarefa> tarefas = new ArrayList<>();
        try{
            tarefas = gerenciadorDadosMYSQL.getTodasTarefas();
        }catch (SQLException e){
            JOptionPane.showMessageDialog(rootPane, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
        jTable.setModel(new TarefaTableModel(tarefas));
        
        
        botaoNovaTarefa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroTarefa cadastroTarefa = new CadastroTarefa();
                cadastroTarefa.setVisible(true);
            }
        });
        
        botaoAlterarTarefa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AlterarTarefa alterarTarefa = new AlterarTarefa();
                alterarTarefa.setVisible(true);
            }
        });
        
        botaoApagarTarefa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.valueOf((JOptionPane.showInputDialog(rootPane, "Insira o ID a ser apagado")));
                
                GerenciadorDadosMYSQL gerenciadorDadosMYSQL = new GerenciadorDadosMYSQL();
                
                try{
                    gerenciadorDadosMYSQL.apagarTarefa(id);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane, ex.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                JOptionPane.showMessageDialog(rootPane, "Apagado com sucesso", "Informativo", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        
        
        botaoExibirTarefasAgendadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collection<Tarefa> tarefas = new ArrayList<>();
                
                GerenciadorDadosMYSQL gerenciadorDadosMYSQL = new GerenciadorDadosMYSQL();
                
                try{
                    tarefas = gerenciadorDadosMYSQL.getTarefasLembrar();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane, ex.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                jTable.setModel(new TarefaTableModel(tarefas));
            }
        });
        
        botaoExibirTarefaTag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tag = JOptionPane.showInputDialog(rootPane, "Insira a tag");
                
                Collection<Tarefa> tarefas = new ArrayList<>();
                
                GerenciadorDadosMYSQL gerenciadorDadosMYSQL = new GerenciadorDadosMYSQL();
                
                try{
                    tarefas = gerenciadorDadosMYSQL.getTarefasTag(tag);
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane, ex.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                jTable.setModel(new TarefaTableModel(tarefas));
            }          
        });
        
        botaoExibirTodasTarefas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Collection<Tarefa> tarefas = new ArrayList<>();
                
                GerenciadorDadosMYSQL gerenciadorDadosMYSQL = new GerenciadorDadosMYSQL();
                
                try{
                    tarefas = gerenciadorDadosMYSQL.getTodasTarefas();
                }catch(SQLException ex){
                    JOptionPane.showMessageDialog(rootPane, ex.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                jTable.setModel(new TarefaTableModel(tarefas));
            }
        });
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelSuperior = new javax.swing.JPanel();
        botaoNovaTarefa = new javax.swing.JButton();
        botaoAlterarTarefa = new javax.swing.JButton();
        botaoApagarTarefa = new javax.swing.JButton();
        panelCentral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        panelInferior = new javax.swing.JPanel();
        botaoExibirTarefasAgendadas = new javax.swing.JButton();
        botaoExibirTarefaTag = new javax.swing.JButton();
        botaoExibirTodasTarefas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DoTask");
        setPreferredSize(new java.awt.Dimension(800, 600));

        panelSuperior.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 0, 0, 0));
        panelSuperior.setLayout(new javax.swing.BoxLayout(panelSuperior, javax.swing.BoxLayout.LINE_AXIS));

        botaoNovaTarefa.setText("Nova Tarefa");
        panelSuperior.add(botaoNovaTarefa);

        botaoAlterarTarefa.setText("Alterar Tarefa");
        panelSuperior.add(botaoAlterarTarefa);

        botaoApagarTarefa.setText("Apagar Tarefa");
        panelSuperior.add(botaoApagarTarefa);

        getContentPane().add(panelSuperior, java.awt.BorderLayout.NORTH);

        panelCentral.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 0, 20, 0));
        panelCentral.setLayout(new java.awt.BorderLayout());

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable);

        panelCentral.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelCentral, java.awt.BorderLayout.CENTER);

        panelInferior.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panelInferior.setLayout(new javax.swing.BoxLayout(panelInferior, javax.swing.BoxLayout.LINE_AXIS));

        botaoExibirTarefasAgendadas.setText("Exibir tarefas agendadas");
        panelInferior.add(botaoExibirTarefasAgendadas);

        botaoExibirTarefaTag.setText("Exibir tarefas por Tag");
        panelInferior.add(botaoExibirTarefaTag);

        botaoExibirTodasTarefas.setText("Exibir todas as tarefas");
        panelInferior.add(botaoExibirTodasTarefas);

        getContentPane().add(panelInferior, java.awt.BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botaoAlterarTarefa;
    private javax.swing.JButton botaoApagarTarefa;
    private javax.swing.JButton botaoExibirTarefaTag;
    private javax.swing.JButton botaoExibirTarefasAgendadas;
    private javax.swing.JButton botaoExibirTodasTarefas;
    private javax.swing.JButton botaoNovaTarefa;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    private javax.swing.JPanel panelCentral;
    private javax.swing.JPanel panelInferior;
    private javax.swing.JPanel panelSuperior;
    // End of variables declaration//GEN-END:variables
}
