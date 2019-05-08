package br.univali.view.processadores;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 *
 * @author Elieser
 */
public class Riscador implements Filtro {

    private final Filtro filtroDecorado;

    public Riscador(Filtro filtroDecorado) {
        this.filtroDecorado = filtroDecorado;
    }

    public Riscador() {
        filtroDecorado = new FiltroNulo();
    }
    
    @Override
    public BufferedImage processa(BufferedImage imagemOriginal) throws Exception {
        
        Graphics g = imagemOriginal.getGraphics();
        g.setColor(Color.blue);
        
        for (int i = 0; i < 100; i++) { //100 riscos/linhas na imagem
            
            int x = (int) (Math.random() * imagemOriginal.getWidth());
            int y = (int) (Math.random() * imagemOriginal.getHeight());
            int x2 = (int) (Math.random() * imagemOriginal.getWidth());
            int y2 = (int) (Math.random() * imagemOriginal.getHeight());
            g.drawLine(x, y, x2, y2);
        }
        return filtroDecorado.processa(imagemOriginal);
    }

}
