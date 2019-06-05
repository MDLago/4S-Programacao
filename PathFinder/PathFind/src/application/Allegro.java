/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;

public class Allegro {

    public static Image screen;
    public static final Font font = new Font("Verdana", Font.BOLD, 14);
    public static int SCREEN_W;
    public static int SCREEN_H;

    public static Image load_bitmap(String path, int pallete){
        return null;
    }
    
    public static void rect_fill(Image img, int x, int y, int largura, int altura, Color cor) {
        System.out.println("IMPLEMENTAR rect fill");
    }

    public static void line(Image img, int x, int y, int x2, int y2, Color cor) {
        Graphics2D g2d = (Graphics2D)img.getGraphics();
        g2d.setColor(cor);
        g2d.drawLine(x, y, x2, y2);
    }

    public static void hline(Image img, int x, int y, int x2, Color cor) {
        Allegro.line(img, x, y, x2, y, cor);
    }

    public static void maskedBlit(Image source, Image dest, int source_x, int source_y, int dest_x, int dest_y, int largura, int altura) {
        System.out.println("IMPLEMENTAR MASKED BLIT");
    }

    public static void stretch_blit(Image source, Image dest, int xOrgigem, int yOrigem, int larguraOrigem, int alturaOrigem, int xDestino, int yDestino, int larguraDestino, int alturaDestino) {
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public static void circleFill(Image img, int x, int y, int largura, Color cor) {
        Graphics2D g2d = (Graphics2D)img.getGraphics();
        g2d.setColor(cor);
        g2d.fillOval(x, y, largura, largura);
    }

    public static void textprintf_centre_ex(Image img, Font font, int x, int y, Color corDoTexto, Color corDeFundo, String formato, Object texto) {
        Graphics2D g2d = (Graphics2D)img.getGraphics();
        FontMetrics metrics = g2d.getFontMetrics();
        String mensagem = texto.toString();
        int largura = metrics.charsWidth(mensagem.toCharArray(), 0, mensagem.toCharArray().length);
        if( corDeFundo != null ){
            g2d.setColor(corDeFundo);
            g2d.fillRect(x - largura/2, y-metrics.getHeight() + metrics.getLeading() + metrics.getDescent(), largura, metrics.getHeight());
        }
        g2d.setColor(corDoTexto);
        g2d.drawString(mensagem, x - largura/2, y);
    }

    public static void textout_centre_ex(Image img, Font font, String texto, int x, int y, Color corDoTexto, Color corDoFundo) {
        textprintf_centre_ex(img, font, x, y, corDoTexto, corDoFundo, "%s", texto);
    }

    public static void clear_to_color(Image img, Color color) {
        //img.getGraphics().setColor(color);
        //img.getGraphics().clearRect(0, 0, buffer.getWidth(null), buffer.getHeight(null));
    }

}
