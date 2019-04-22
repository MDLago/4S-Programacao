/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.s4.prog.m1.dotask.dominio;

import java.util.Date;
import java.util.TimerTask;
import javax.mail.MessagingException;
import br.univali.cc.s4.prog.m1.dotask.persistencia.GerenciadorDadosMYSQL;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author 5586658
 */
public class Tarefa extends TimerTask{
    private final int id;
    private final String nome;
    private final Date criadoEm;
    private final boolean lembrar;
    private final Date lembrarEm;
    private final String tag;
    private final String email;

    public Tarefa(int id, String nome, Date criadoEm, boolean lembrar, Date lembrarEm, String tags, String email) {
        this.id = id;
        this.nome = nome;
        this.criadoEm = criadoEm;
        this.lembrar = lembrar;
        this.lembrarEm = lembrarEm;
        this.tag = tags;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public Date getCriadoEm() {
        return criadoEm;
    }

    public String getEmail() {
        return email;
    }
    
    public boolean getLembrar(){
        return lembrar;
    }

    public Date getLembrarEm() {
        return lembrarEm;
    }

    public String getNome() {
        return nome;
    }

    public String getTag() {
        return tag;
    }

    public boolean isLembrar() {
        return lembrar;
    }

    @Override
    public void run() {
        boolean status;
        
        
        try{
           status = MailSender.enviarEmail(email, nome); 
        } catch (MessagingException e){
            status = Boolean.FALSE;
        }
        
        if (status) {
            try {
                GerenciadorDadosMYSQL gerenciador = new GerenciadorDadosMYSQL();
                gerenciador.alterarTarefa(nome, Boolean.FALSE, this.lembrarEm, this.tag, this.email, this.id);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        System.out.println("enviado com sucesso: "+this.nome);
        
    }

    
}
