/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.univali.view.processadores;

import com.jhlabs.image.GaussianFilter;
import java.awt.image.BufferedImage;

/**
 *
 * @author 5586658
 */
public class Borrador implements Filtro{

    private final Filtro filtroDecorador;
    private final GaussianFilter blur = new GaussianFilter(14);

    public Borrador() {
        this.filtroDecorador = new FiltroNulo();
    }

    public Borrador(Filtro filtroDecorador) {
        this.filtroDecorador = filtroDecorador;
    }
    
    @Override
    public BufferedImage processa(BufferedImage imagemOriginal) throws Exception {
        blur.filter(imagemOriginal, imagemOriginal);
        
        return filtroDecorador.processa(imagemOriginal);
    }
    
}
