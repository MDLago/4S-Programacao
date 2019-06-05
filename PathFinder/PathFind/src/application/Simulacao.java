/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import pathfind.estruturas.VerticeDoGrafo;
import pathfind.estruturas.Mapa;
import pathfind.estruturas.AbstractPathPoint;
import pathfind.estruturas.TileSet;
import pathfind.estrategias.DepthFirstPathFinderStrategy;
import pathfind.estrategias.ManhatamHeiristic;
import pathfind.estrategias.PathFinderStrategy;
import pathfind.estrategias.AStarPathFinderStrategy;
import pathfind.estrategias.EuclidianHeiristic;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import pathfind.Path;
import pathfind.PathFinder;

public class Simulacao extends MouseAdapter implements Runnable {

    private PathFinderStrategy algoritmos[] = {
        new AStarPathFinderStrategy(new EuclidianHeiristic()),
        new AStarPathFinderStrategy(new ManhatamHeiristic()),
        new DepthFirstPathFinderStrategy()
    };
    private VerticeDoGrafo[][] vertices = new VerticeDoGrafo[Globals.LINHAS_DO_MAPA][Globals.COLUNAS_DO_MAPA];
    private PathFinder pathFinder = new PathFinder(algoritmos[0]);
    private Path caminhosEncontrados[];
    private Color coresDosCaminhos[];// = {new Color(255, 0, 0), new Color(255, 255, 255), new Color(0, 0, 255)};
    private Heroi heroi;
    private Image buffer;
    TileSet tileset;
    private VerticeDoGrafo origem;
    private VerticeDoGrafo destino;
    private Mapa mapa;
    private Component screen;
    
    //++++++++++++++++++++++++++
    @Override
    public void mousePressed(MouseEvent e) {
        if (heroiCompletouOCaminho()) {//ou se nem iniciou um caminho
            int colunaDoMouse = (e.getX() / Globals.TAMANHO_DOS_TILES);
            int linhaDoMouse = (e.getY() / Globals.TAMANHO_DOS_TILES);
            if (VerticeDoGrafo.indicesDoVerticeSaoValidos(linhaDoMouse, colunaDoMouse) && vertices[linhaDoMouse][colunaDoMouse].isWalkable()) {
                origem = destino;
                destino = vertices[linhaDoMouse][colunaDoMouse];
                caminhosEncontrados = new Path[algoritmos.length];
                for (int i = 0; i < algoritmos.length; i++) {
                    //seta um novo algoritmo no buscador de caminhos
                    pathFinder.setPathFindingStrategy(algoritmos[i]);
                    caminhosEncontrados[i] = pathFinder.getPath(destino, origem);//precisei inverter? Why?
                }
                Path menorCaminho = getMenorCaminhoEncontrado();
                if (menorCaminho != null && menorCaminho.getNumberOfPoints() > 0) {
                    heroi.setCaminho(menorCaminho);
                    //o heroi sempre segue o menor caminho encontrado
                }
            }
        }
    }
//++++++++

    public Simulacao(Component screen) throws FileNotFoundException, URISyntaxException {
        this.tileset = new TileSet(Globals.LINHAS_NO_TILESET,
                Globals.COLUNAS_NO_TILESET,
                Globals.TAMANHO_DOS_TILES,
                "tileset.png");

        mapa = new Mapa(Globals.LINHAS_DO_MAPA, Globals.COLUNAS_DO_MAPA, tileset, "mapa.ini");

        this.screen = screen;

        buffer = screen.getGraphicsConfiguration().createCompatibleImage(
                Globals.LARGURA_DA_TELA, Globals.ALTURA_DA_TELA + Globals.ALTURA_DO_FOOTER, BufferedImage.TYPE_INT_RGB);
        //((Graphics2D)buffer.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //((Graphics2D)screen.getGraphics()).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }
    //++++++++++++++++++++++++++++++

    @Override
    public void run() {

        inicializaVariaveis();

        long ultimoClock = System.currentTimeMillis();
        while (true) {//main loop
            heroi.update(System.currentTimeMillis() - ultimoClock);
            atualizaTela();
            ultimoClock = System.currentTimeMillis();
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
    }
    //+++++++++++++++++

    public void desenhaTiles() {
        //teste
        //buffer.getGraphics().drawOval(100, 100, 100, 100);
        mapa.renderiza(buffer.getGraphics());
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void desenhaInicioEFimDosCaminhos() {
        final int TAMANHO_DOS_CIRCULOS = (int)(Globals.TAMANHO_DOS_TILES * 0.8);
        for (int i = 0; i < algoritmos.length; i++) {
            if (caminhosEncontrados[i] != null) {
                AbstractPathPoint inicio = caminhosEncontrados[i].getPointAt(0);
                if (inicio != null) {
                    int xInicio = (int) inicio.getX() * Globals.TAMANHO_DOS_TILES + Globals.TAMANHO_DOS_TILES/2 - TAMANHO_DOS_CIRCULOS/2;
                    int yInicio = (int) inicio.getY() * Globals.TAMANHO_DOS_TILES + Globals.TAMANHO_DOS_TILES/2 -TAMANHO_DOS_CIRCULOS/2;
                    Allegro.circleFill(buffer, xInicio, yInicio, TAMANHO_DOS_CIRCULOS, new Color(0, 255, 60));
                }
                AbstractPathPoint fim = caminhosEncontrados[i].getPointAt(caminhosEncontrados[i].getNumberOfPoints() - 1);
                if (fim != null) {
                    int xFim = (int) fim.getX() * Globals.TAMANHO_DOS_TILES + Globals.TAMANHO_DOS_TILES/2 -TAMANHO_DOS_CIRCULOS/2;
                    int yFim = (int) fim.getY() * Globals.TAMANHO_DOS_TILES + Globals.TAMANHO_DOS_TILES/2 -TAMANHO_DOS_CIRCULOS/2;
                    Allegro.circleFill(buffer, xFim, yFim, TAMANHO_DOS_CIRCULOS, new Color(255, 0, 0));
                }
                return;
            }
        }
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void desenhaCaminhoEncontrado() {
        if (caminhosEncontrados != null) {
            for (int i = 0; i < algoritmos.length; i++) {
                if (caminhosEncontrados[i] != null) {
                    int numeroDePontosNoCaminho = caminhosEncontrados[i].getNumberOfPoints();
                    if (numeroDePontosNoCaminho > 1) {
                        AbstractPathPoint verticeAnterior = caminhosEncontrados[i].getPointAt(0);
                        AbstractPathPoint verticeAtual;
                        int contadorDePontosDoCaminho = 1;
                        while (contadorDePontosDoCaminho < numeroDePontosNoCaminho) {
                            verticeAtual = caminhosEncontrados[i].getPointAt(contadorDePontosDoCaminho);
                            int x1 = (int) verticeAnterior.getX() * Globals.TAMANHO_DOS_TILES + Globals.TAMANHO_DOS_TILES / 4 + i * 2;
                            int y1 = (int) verticeAnterior.getY() * Globals.TAMANHO_DOS_TILES + Globals.TAMANHO_DOS_TILES / 4 + i * 2;
                            int x2 = (int) verticeAtual.getX() * Globals.TAMANHO_DOS_TILES + Globals.TAMANHO_DOS_TILES / 4 + i * 2;
                            int y2 = (int) verticeAtual.getY() * Globals.TAMANHO_DOS_TILES + Globals.TAMANHO_DOS_TILES / 4 + i * 2;
                            Allegro.line(buffer, x1, y1, x2, y2, coresDosCaminhos[i]);
                            verticeAnterior = verticeAtual;
                            ++contadorDePontosDoCaminho;
                        }
                    }
                }
            }
        }
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public void desenhaFooter() {
        int margemSuperior = 45;
        Graphics g = buffer.getGraphics();
        g.setColor(new Color(0, 40, 0));
        g.fillRect(0, Globals.ALTURA_DA_TELA, Globals.LARGURA_DA_TELA, Globals.ALTURA_DO_FOOTER);
        //Allegro.rect_fill(buffer, 0, Globals.ALTURA_DA_TELA, Globals.LARGURA_DA_TELA, Globals.ALTURA_DA_TELA + Globals.ALTURA_DO_FOOTER, new Color(0, 40, 0));
        String titulosDasColunas[] = {"Legenda", "Algoritmo", "Tamanho do caminho", "Iteracoes"};
        int margemEsquerda = 50;
        int larguraDasLinhas = 20;

        int espacamentoVertical = 15;
        for (int i = 0; i < algoritmos.length; i++) {
            if (caminhosEncontrados != null && caminhosEncontrados[i] != null) {
                for (int k = 0; k < 4; k++) {
                    Allegro.textprintf_centre_ex(buffer, Allegro.font, k * Globals.LARGURA_DA_TELA / 4 + margemEsquerda, Globals.ALTURA_DA_TELA + margemSuperior, new Color(120, 180, 170), null, "%s", titulosDasColunas[k]);
                }
                int y = Globals.ALTURA_DA_TELA + espacamentoVertical + i * espacamentoVertical + margemSuperior + 5;
                int x = margemEsquerda;//+ larguraDasLinhas;
                Allegro.hline(buffer, margemEsquerda, y, x + larguraDasLinhas, coresDosCaminhos[i]);
                Allegro.hline(buffer, margemEsquerda, y + 1, x + larguraDasLinhas, coresDosCaminhos[i]);

                x += Globals.LARGURA_DA_TELA / 4;
                Allegro.textprintf_centre_ex(buffer, Allegro.font, x, y, coresDosCaminhos[i], null, "%s", algoritmos[i].getStrategyName());
                x += Globals.LARGURA_DA_TELA / 4;
                if (caminhosEncontrados[i] != null) {
                    Allegro.textprintf_centre_ex(buffer, Allegro.font, x, y, coresDosCaminhos[i], null, "%i", caminhosEncontrados[i].getNumberOfPoints());
                    x += Globals.LARGURA_DA_TELA / 4;
                    Allegro.textprintf_centre_ex(buffer, Allegro.font, x, y, coresDosCaminhos[i], null, "%i", caminhosEncontrados[i].getNumberOfIterationsToFindThePath());
                }
            }
        }

        Color corDoTexto = new Color(120, 180, 255);
        Color corDoFundo = null;//new Color(0, 0, 0);
        Allegro.textout_centre_ex(buffer, Allegro.font, "Path Finding - Clique na grama para calcular um caminho!", Globals.LARGURA_DA_TELA / 2, Globals.ALTURA_DA_TELA + 15, new Color(255, 0, 0), corDoFundo);
        Allegro.textout_centre_ex(buffer, Allegro.font, "Elieser Ademir de Jesus - elieser@univali.br", Globals.LARGURA_DA_TELA / 2, Globals.ALTURA_DA_TELA + Globals.ALTURA_DO_FOOTER - 10, corDoTexto, corDoFundo);
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public void atualizaTela() {
        //buffer.getGraphics().setColor(Color.red);//new Color(144, 184, 113));
        //buffer.getGraphics().fillRect(0, 0, buffer.getWidth(null), buffer.getHeight(null));
        desenhaTiles();
        if (caminhosEncontrados != null) {
            desenhaCaminhoEncontrado();
            desenhaInicioEFimDosCaminhos();
        }
        desenhaFooter();
        
        heroi.desenha(buffer);
        Graphics tela = screen.getGraphics();
        if (tela != null) {
            tela.drawImage(buffer, 0, 0, null);
            tela.dispose();
        }
        //show_mouse(buffer);
        //Allegro.stretch_blit(buffer, Allegro.screen, 0, 0, buffer.getWidth(null), buffer.getHeight(null), 0, 0, Allegro.screen.getWidth(null), Allegro.screen.getHeight(null));    //atualiza tela
    }
    //++++++++++++++++++++++++++++++

    public Mapa getMapa() {
        return this.mapa;
    }
    //++++++++++++++++++++++++++++

    private void inicializaVariaveis() {

        coresDosCaminhos = new Color[algoritmos.length];
        for (int i = 0; i < algoritmos.length; i++) {
            int r = 255;//(int) (Math.random() * 180);
            int g = (int) (Math.random() * 180);
            int b = (int) (Math.random() * 180);
            coresDosCaminhos[i] = new Color(r, g, b);
        }

        //inicializa vertices
        for (int n = 0; n < Globals.LINHAS_DO_MAPA; n++) {
            for (int m = 0; m < Globals.COLUNAS_DO_MAPA; m++) {
                vertices[n][m] = new VerticeDoGrafo(n, m, mapa.isWalkable(n, m));
            }
        }

        //inicializa adjacências
        for (int n = 0; n < Globals.LINHAS_DO_MAPA; n++) {
            for (int m = 0; m < Globals.COLUNAS_DO_MAPA; m++) {
                List<AbstractPathPoint> verticesAdjacentes = new ArrayList<AbstractPathPoint>();//
                for (int linha = -1; linha <= 1; linha++) {
                    for (int coluna = -1; coluna <= 1; coluna++) {
                        if (linha + coluna != 0) {//confere se é um dos vizinhos das laterais ou de cima ou de baixo
                            if (VerticeDoGrafo.indicesDoVerticeSaoValidos(n + linha, m + coluna)) {
                                if (vertices[n + linha][m + coluna].isWalkable()) {
                                    verticesAdjacentes.add(vertices[n + linha][m + coluna]);
                                }
                            }
                        }
                    }
                }
                AbstractPathPoint[] points = new AbstractPathPoint[verticesAdjacentes.size()];
                int i = 0;
                for (AbstractPathPoint point : verticesAdjacentes) {
                    points[i++] = point;
                }
                vertices[n][m].setAdjacentPoints(points);
            }
        }

        //tileset = Allegro.load_bitmap("tileset4.bmp", 0);
        destino = vertices[ Globals.LINHAS_DO_MAPA - 1][ Globals.COLUNAS_DO_MAPA - 1];
        origem = vertices[0][0];
        heroi = new Heroi(Globals.TAMANHO_DOS_TILES);
        heroi.x = (int) origem.getX() * Globals.TAMANHO_DOS_TILES;
        heroi.y = (int) origem.getY() * Globals.TAMANHO_DOS_TILES;


    }
//----------------------------------------------------------------

    private Path getMenorCaminhoEncontrado() {

        if (caminhosEncontrados == null) {
            return null;
        }

        Path menorCaminho = caminhosEncontrados[0];
        for (int i = 0; i < algoritmos.length; i++) {
            if (caminhosEncontrados[i] != null) {
                if (caminhosEncontrados[i].getNumberOfPoints() < menorCaminho.getNumberOfPoints()) {
                    menorCaminho = caminhosEncontrados[i];
                }
            }
        }
        return menorCaminho;
    }

//+++++++++++++++++++++++++++++++++++++++++++++++++++++
    private boolean heroiCompletouOCaminho() {
        Path menorCaminho = getMenorCaminhoEncontrado();
        if (menorCaminho != null) {
            AbstractPathPoint ultimoPonto = menorCaminho.getPointAt(menorCaminho.getNumberOfPoints() - 1);
            return heroi.chegouNoPonto(ultimoPonto);
        }
        return heroi.getDirecao() == Heroi.DirecaoDoHeroi.PARADO;
    }
//------------------------------------------------------------
}
