package pathfind.estruturas;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)

import application.Globals;

public class VerticeDoGrafo extends AbstractPathPoint {

    private boolean isWalkable;
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public VerticeDoGrafo(int linhaDoVerticeNoMapa, int colunaDoVerticeNoMapa, boolean walkable) {
        this.x = colunaDoVerticeNoMapa;
        this.y = linhaDoVerticeNoMapa;
        this.isWalkable = walkable;
    }
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //m�todos sobrescritos da classe base para poder funcionar com a classe PathFinder
    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    @Override
    public boolean isWalkable() {
        int linha = (int) this.getY();
        int coluna = (int) this.getX();
        return indicesDoVerticeSaoValidos(linha, coluna) && this.isWalkable;
    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static boolean indicesDoVerticeSaoValidos(int linha, int coluna) {
        return linha >= 0 && linha < Globals.LINHAS_DO_MAPA && coluna >= 0 && coluna < Globals.COLUNAS_DO_MAPA;
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}
