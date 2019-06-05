/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfind.estruturas;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import application.Globals;
import java.awt.Color;

public class Mapa {


    private int linhas;
    private int colunas;
    private int dados[][];
    private TileSet tileSet;

    public Mapa(int linhas, int colunas, TileSet tileSet) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.dados = new int[this.linhas][this.colunas];
        this.tileSet = tileSet;
    }

    public Mapa(int linhas, int colunas, TileSet tileSet, String arquivoComOsDados) throws URISyntaxException,FileNotFoundException {
        this(linhas, colunas, tileSet);
        carregaDadosDoArquivo(arquivoComOsDados);
    }

    public void renderiza(Graphics g){
        //desenha os tiles de acordo com o c�digo no mapa
        
        Image tileset = tileSet.getImagem();
        for (int i = Globals.LINHAS_DO_MAPA - 1; i >= 0; i--) {
            for (int j = Globals.COLUNAS_DO_MAPA - 1; j >= 0; j--) {
                int colunaDoTile = ((dados[i][j] - 1) % Globals.COLUNAS_NO_TILESET);
                int source_x = colunaDoTile * tileSet.getTamanhoDosTiles();//+ (colunaDoTile+1);
                int linhaDoTile = ((dados[i][j] - 1) / Globals.COLUNAS_NO_TILESET);
                int source_y = linhaDoTile * tileSet.getTamanhoDosTiles();//+ (linhaDoTile + 1) ;
                int dest_x = j * tileSet.getTamanhoDosTiles();
                int dest_y = i * tileSet.getTamanhoDosTiles();
                
                g.drawImage(tileset, 
                        dest_x, dest_y, 
                        dest_x + tileSet.getTamanhoDosTiles(),
                        dest_y + tileSet.getTamanhoDosTiles(),
                        source_x, source_y,
                        source_x + tileSet.getTamanhoDosTiles(),
                        source_y + tileSet.getTamanhoDosTiles(),
                        null);
                
                //desenha os retângulos
                g.setColor(new Color(166, 198, 140));
                g.drawRect( 
                        dest_x, dest_y, 
                        dest_x + tileSet.getTamanhoDosTiles(),
                        dest_y + tileSet.getTamanhoDosTiles()
                        );
                
            }
        }
    }
    
    private void carregaDadosDoArquivo(String path) throws FileNotFoundException, URISyntaxException {
        URI uri = Mapa.class.getClassLoader().getResource(path).toURI();
        Scanner scanner = new Scanner(new File(uri));
        int linhaAtual = 0;
        while(scanner.hasNext()){
            String linha = scanner.nextLine();
            String tiles[] = linha.split(",");
            for( int i=0; i < tiles.length; i++  ){
                String dadoDoTile = tiles[i];
                if( !("".equals(dadoDoTile)) ){
                    dadoDoTile = dadoDoTile.trim();
                    dados[linhaAtual][i] = Integer.parseInt(dadoDoTile);
                }
            }
            linhaAtual++;
        }
    }

    public boolean isWalkable(int linha, int coluna){
        return dados[linha][coluna] == 3;
    }
    
    public void setTile(int linha, int coluna, int tile) {
        dados[linha][coluna] = tile;
    }

    public int getTile(int linha, int coluna) {
        return dados[linha][coluna];
    }
}
