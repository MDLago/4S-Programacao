/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.cc.s4.prog.m1.dotask.dominio;

import java.util.Timer;

/**
 *
 * @author 5586658
 */
public class Scheduler {

    public void atualizador(){

        Timer timer =  new Timer();
        
        long frequencia = 1000*30;
        timer.scheduleAtFixedRate(new EnviarTarefasTask(), 0, frequencia);
    }
}
