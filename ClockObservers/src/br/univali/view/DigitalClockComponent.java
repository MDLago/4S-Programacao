package br.univali.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

public class DigitalClockComponent extends AbstractClockComponent{
    
    private Color corDaFonte = Color.RED;
    private static final int MARGEM = 8;
    
    public DigitalClockComponent() {
        inicializa();
    }
    
    private void inicializa(){
        setFont(new Font("Verdana", Font.BOLD, 16));
        addComponentListener(new ResizeListener());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
    }
    
    public void setCorDaFonte(Color novaCor){
        corDaFonte = novaCor;
        repaint();
    }
    

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        Rectangle2D tamanhosDaFonte = g2d.getFontMetrics().getStringBounds("M", g2d);
        //desenha sombra
        g2d.setColor(Color.DARK_GRAY);
        g2d.drawString( getCamposFormatados(), MARGEM-2, (int)tamanhosDaFonte.getHeight() );
        
        g2d.setColor(corDaFonte);
        g2d.drawString( getCamposFormatados(), MARGEM, (int)tamanhosDaFonte.getHeight() );
    }
    
    private String getCamposFormatados(){
        String horas = getCampoFormatado(getHora());
        String minutos = getCampoFormatado(getMinuto());
        String segundos = getCampoFormatado(getSegundo());
        return horas + ":" + minutos + ":" + segundos;
    }
    
    private String getCampoFormatado(int valorDoCampo){
        String resultado = String.valueOf(valorDoCampo);
        if(valorDoCampo <= 9){
            resultado = "0" + resultado;
        }
        return resultado;
    }
    
    private void ajustaTamanhoDaFonte(){
        String stringDeExibicao = getCamposFormatados(); 
        Rectangle2D bounds = this.getGraphics().getFontMetrics().getStringBounds(stringDeExibicao, this.getGraphics());
        //ajusta o tamanho da fonte pela largura do component
        double escalaDaFonte = (getWidth() - MARGEM*2)/bounds.getWidth();
        Font fonteAtual = getFont();
        setFont(new Font(fonteAtual.getFontName(), fonteAtual.getStyle(), (int)(fonteAtual.getSize2D() * escalaDaFonte)));
    }
    
    private class ResizeListener extends ComponentAdapter{

        @Override
        public void componentResized(ComponentEvent e) {
            ajustaTamanhoDaFonte();
            System.out.println("ajustou");
        }
        
    }
}
