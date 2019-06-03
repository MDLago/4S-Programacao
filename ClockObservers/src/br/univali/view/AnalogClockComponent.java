package br.univali.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

//elieser@univali.br 2012

public class AnalogClockComponent extends AbstractClockComponent {

    private static final int MARGEM = 5;//5 pixels de margem
    
    
    
    //método que desenha o componente
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        desenhaFundo(g2d);
        desenhaMarcacoesDasHoras(g2d, Color.DARK_GRAY);
        
        
        //desenha os ponteiros, cada um com uma espessura e cor diferente
        desenhaPonteiro(g2d, Color.WHITE, 3.0f, getPosicaoDoPonteiroDaHora(), 0.6f, true);
        desenhaPonteiro(g2d, Color.BLUE, 1.5f, getMinuto(), 0.85f);
        desenhaPonteiro(g2d, Color.DARK_GRAY, 1.0f, getSegundo(), 0.92f);
        
        desenhaCirculoCentral(g2d);
    }
    
    private void desenhaCirculoCentral(Graphics2D g){
        int centroX = getWidth()/2;
        int centroY = getHeight()/2;
        
        final int TAMANHO = (int)(Math.min(getWidth(), getHeight()) * 0.05);//5% do tamanho do componente
        
        g.setColor(Color.DARK_GRAY);
        g.fillOval(centroX - TAMANHO/2, centroY - TAMANHO/2, TAMANHO, TAMANHO);
    }
    
    private void desenhaMarcacoesDasHoras(Graphics2D g, Color cor){
        double angulo = 0;//em graus
        g.setColor(cor);
        
        int centroX = getWidth()/2;
        int centroY = getHeight()/2;
        
        int raio = Math.min(getWidth()/2, getHeight()/2);
        double anguloEmRadianos = 0;
        while( angulo < 360 ){
            anguloEmRadianos = Math.toRadians(angulo);
            g.drawLine(
                    (int)(centroX + (raio * 0.8) * Math.cos(anguloEmRadianos)), 
                    (int)(centroY + (raio * 0.8) * Math.sin(anguloEmRadianos)), 
                    (int)(centroX + (raio * 0.9) * Math.cos(anguloEmRadianos)), 
                    (int)(centroY + (raio * 0.9) * Math.sin(anguloEmRadianos)) );
            angulo += 30;
        }
    }
    
    private double getPosicaoDoPonteiroDaHora(){
        return this.getHora() + (this.getMinuto()/60.0f);
    }

    /***
     * 
     * @param g
     * @param cor
     * @param espessuraDaLinha
     * @param valorDoPonteiro o valor em graus da posição do ponteiro
     * @param tamanhoDoPonteiro Valor entre 0 e 1
     */
    private void desenhaPonteiro(Graphics2D g, Color cor, float espessuraDaLinha, double valorDoPonteiro, double tamanhoDoPonteiro){
        desenhaPonteiro(g, cor, espessuraDaLinha, valorDoPonteiro, tamanhoDoPonteiro, false);
    }
    
    private void desenhaPonteiro(Graphics2D g, Color cor, float espessuraDaLinha, double valorDoPonteiro, double tamanhoDoPonteiro, boolean ehPonteiroDaHora){
        //calcula o ângulo e corrige o quadrante
        double anguloDoPonteiro = Math.toRadians( valorDoPonteiro * 6 - 90);
        if( ehPonteiroDaHora ){
            anguloDoPonteiro = Math.toRadians( valorDoPonteiro * 30 - 90);
        }
        
        int centroX = getWidth()/2;
        int centroY = getHeight()/2;
        
        double raio = Math.min(getWidth()/2, getHeight()/2) * tamanhoDoPonteiro;
        
        g.setColor(cor);
        g.setStroke( new BasicStroke(espessuraDaLinha));
        
        //desenha a linha maior
        g.drawLine( centroX, centroY, //centro do relógio
                    (int)(centroX + raio * Math.cos(anguloDoPonteiro)), 
                    (int)(centroY + raio * Math.sin(anguloDoPonteiro)));
        
        //desenha a linha menor, a parte traseira do ponteiro
        g.drawLine( centroX, centroY, //centro do relógio
                    (int)(centroX + (raio * 0.3f * tamanhoDoPonteiro) * Math.cos(anguloDoPonteiro + Math.PI)), 
                    (int)(centroY + (raio * 0.3f * tamanhoDoPonteiro) * Math.sin(anguloDoPonteiro + Math.PI)));
    }
    

    

    private void desenhaFundo(Graphics2D g2d) {
        int raio = Math.min(getHeight(), getWidth());
        int centroX = getWidth()/2;
        int centroY = getHeight()/2;
        final int TAMANHO = raio - MARGEM*2-1;
       
        //pinta fundo
        g2d.setColor(new Color(200, 200, 200));
        g2d.fillOval(
                centroX - raio/2 + MARGEM, 
                centroY - raio/2 + MARGEM, 
                TAMANHO, TAMANHO);
        
         //desenha borda
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawOval(
                centroX - raio/2 + MARGEM, 
                centroY - raio/2 + MARGEM, 
                TAMANHO, TAMANHO);
    }
    
}
