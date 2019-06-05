/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfind.estruturas;

import java.awt.Image;
import javax.swing.ImageIcon;

public class TileSet {
    
//    public static final int COLUNAS_NO_TILESET = 5;
//public static final int LINHAS_NO_TILESET = 5;
//public static final int TAMANHO_DOS_TILES = 16;
    
    private int linhas;
    private int colunas;
    private int tamanhoDosTiles;
    
    private Image imagem;

    public TileSet(int linhas, int colunas, int tamanhoDosTiles, String pathDaImagem) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.tamanhoDosTiles = tamanhoDosTiles;
        imagem = carregaImagem(pathDaImagem);
    }
    
    public int getTamanhoDosTiles(){
        return tamanhoDosTiles;
    }
    
    private Image carregaImagem(String path) {
        return new ImageIcon( TileSet.class.getClassLoader().getResource(path)).getImage();
    }
    
    public Image getImagem(){
        return this.imagem;
    }

}
