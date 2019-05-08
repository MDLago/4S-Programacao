package br.univali.view.processadores;

import java.awt.image.BufferedImage;

/**
 *
 * @author Elieser
 */
public class GeradorDeCinza implements Filtro {

    private final Filtro filtroDecorado;

    public GeradorDeCinza() {
        this.filtroDecorado = new FiltroNulo();
    }

    public GeradorDeCinza(Filtro filtroDecorado) {
        this.filtroDecorado = filtroDecorado;
    }
    
    @Override
    public BufferedImage processa(BufferedImage imagemOriginal) throws Exception {
        int largura = imagemOriginal.getWidth(null);
        int altura = imagemOriginal.getHeight(null);
        int tipoDeImagem = BufferedImage.TYPE_BYTE_GRAY;
        BufferedImage imagemTonsDeCinza = new BufferedImage(largura, altura, tipoDeImagem);

        imagemTonsDeCinza.getGraphics().drawImage(imagemOriginal, 0, 0, null);
        return filtroDecorado.processa(imagemTonsDeCinza);
    }

}
