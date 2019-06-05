package br.univali.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Clock implements Runnable {
    private List<ClockListener> listeners = new ArrayList<>();
    
    public void addListener(ClockListener l){
        listeners.add(l);
    }
    //++++++++++++++++++++++++++++++++++

    private static Clock instancia = null;
    private Date tempoInterno;
    private boolean estaExecutando = false;

    //+++++++++++++++++++++++++++++++++
    public static Clock getInstancia() {
        if (instancia == null) {
            instancia = new Clock();
        }
        return instancia;
    }
    //+++++++++++++++++++++++++++++++++
    private Clock() {
        tempoInterno = new Date();
        new Thread(this).start();
    }
//+++++++++++++++++++++++++++++++++

    public int getHora() {
        return tempoInterno.getHours();
    }
//+++++++++++++++++++++++++++++++++

    public int getMinutos() {
        return tempoInterno.getMinutes();
    }
//+++++++++++++++++++++++++++++++++

    public int getSegundos() {
        return tempoInterno.getSeconds();
    }
//+++++++++++++++++++++++++++++++++

    @Override
    public void run() {
        if (!estaExecutando) {
            estaExecutando = true;

            while (estaExecutando) {
                tempoInterno = new Date();//pega o tempo do sistema novamente
                ClockEvent ce = new ClockEvent(tempoInterno.getHours(), tempoInterno.getMinutes(), tempoInterno.getSeconds());
                for (ClockListener listener : listeners) {
                    listener.clockTick(ce);
                }
                try {
                    Thread.sleep(1000);//espera 1 segundo para atualizar novamente
                } catch (Exception e) {
                    estaExecutando = false;
                }
            }
        }
    }
}
