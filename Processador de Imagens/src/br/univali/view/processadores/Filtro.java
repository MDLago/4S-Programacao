package br.univali.view.processadores;

import java.awt.image.BufferedImage;

public interface Filtro {
    
    BufferedImage processa(BufferedImage imagemOriginal) throws Exception;
   
}
