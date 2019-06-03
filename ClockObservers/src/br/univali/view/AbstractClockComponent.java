package br.univali.view;

import javax.swing.JComponent;

public class AbstractClockComponent extends JComponent{
    private int hora = (int)(Math.random() * 24);
    private int minuto = (int)(Math.random() * 60);
    private int segundo = (int)(Math.random() * 60);
    
    public void setHora(int hora) {
        if( hora>=0 && hora <= 23 ){
            this.hora = hora;
            repaint();
        }
    }

    public void setMinuto(int minuto) {
        if( minuto >=0 && minuto <= 59 ){
            this.minuto = minuto;
            repaint();
        }
    }

    public void setSegundo(int segundo) {
        if( segundo >=0 && getMinuto() <= 59 ){
            this.segundo = segundo;
            repaint();
        }
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    public int getSegundo() {
        return segundo;
    }
}
