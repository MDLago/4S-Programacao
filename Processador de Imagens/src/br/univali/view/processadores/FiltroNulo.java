
package br.univali.view.processadores;

import java.awt.image.BufferedImage;

public class FiltroNulo implements Filtro{

    @Override
    public BufferedImage processa(BufferedImage imagemOriginal) throws Exception {
        return imagemOriginal; //retorna a imagem original sem modificá-la
    }
    
}
