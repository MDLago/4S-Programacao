package application;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)

import pathfind.Path;
import pathfind.estruturas.AbstractPathPoint;

//++++++++++++++++++++++++++++++++++++++++++
public class Heroi extends Sprite {

    public enum DirecaoDoHeroi {
        CIMA, BAIXO, ESQUERDA, DIREITA, PARADO
    }
    
    final short VELOCIDADE = 3;

    //++++++++++++++++++++++++++++++++++++++++++
    public Heroi(int tamanhoDosTilesDoMapa) {
        super("heroi.png", 16, 20, 16, 4, 20);
        this.x = 300;
        this.y = 300;
        setDirecao(DirecaoDoHeroi.PARADO);
        this.tamanhoDosTilesDoMapa = tamanhoDosTilesDoMapa;
        this.caminho = null;
    }
//++++++++++++++++++++++++++++++++++++++++++

    public void setCaminho(Path novoCaminho) {
        if (novoCaminho != null && novoCaminho != caminho) {
            caminho = novoCaminho;
            this.indiceDoPontoAlvo = 0;
            if (caminho.getNumberOfPoints() > 0) {
                AbstractPathPoint pontoInicialDoCaminho = caminho.getPointAt(0);
                this.x = (int) pontoInicialDoCaminho.getX() * tamanhoDosTilesDoMapa;
                this.y = (int) (pontoInicialDoCaminho.getY() * tamanhoDosTilesDoMapa);
            }
        }
    }
//++++++++++++++++++++++++++++++++++++++++++

    @Override
    public void update(long tempoDecorrido) {
        super.update(tempoDecorrido);
        ajustaQuadroAtual();//ajusta o quadro de acordo com a dire��o atual
        //vai para o pr�ximo ponto do caminho
        if (caminho != null && caminho.getNumberOfPoints() > 0) {
            AbstractPathPoint ultimoPontoDoCaminho = caminho.getPointAt(this.caminho.getNumberOfPoints() - 1);
            if (!chegouNoPonto(ultimoPontoDoCaminho)) {//se n�o chegou no final do caminho
                AbstractPathPoint pontoAlvo = caminho.getPointAt(indiceDoPontoAlvo);
                if (!chegouNoPonto(pontoAlvo)) {
                    movimentaEmDirecaoAoPontoAlvo(pontoAlvo);
                } else {
                    indiceDoPontoAlvo++;
                }
            } else {
                setDirecao(DirecaoDoHeroi.PARADO);
            }
        }
    }
//++++++++++++++++++++++++++++++++++++++++++

    void setDirecao(DirecaoDoHeroi novaDirecao) {
        if (novaDirecao != direcao) {
            this.direcao = novaDirecao;
            switch (direcao) {
                case DIREITA:
                    quadroAtual = 8;
                    dy = 0;
                    dx = VELOCIDADE;
                    break;
                case ESQUERDA:
                    quadroAtual = 4;
                    dy = 0;
                    dx = -VELOCIDADE;
                    break;
                case CIMA:
                    quadroAtual = 12;
                    dy = -VELOCIDADE;
                    dx = 0;
                    break;
                case BAIXO:
                    quadroAtual = 0;
                    dy = VELOCIDADE;
                    dx = 0;
                    break;
                default:
                    quadroAtual = 0;
                    dy = 0;
                    dx = 0;
            }
        }
    }
//++++++++++++++++++++++++++++++++++++++++++

    public boolean chegouNoPonto(AbstractPathPoint pontoAlvo) {
        int colunaDoHeroi = this.x / tamanhoDosTilesDoMapa;
        int linhaDoHeroi = this.y / tamanhoDosTilesDoMapa;
        return colunaDoHeroi == pontoAlvo.getX() && linhaDoHeroi == pontoAlvo.getY();
    }

    //++++++++++++++++++++++++++++++++++++++++++
    public DirecaoDoHeroi getDirecao() {
        return this.direcao;
    }
    //++++++++++++++++++++++++++++++++++++++++++
    
    
    protected Path caminho ;
    protected int indiceDoPontoAlvo;
    protected DirecaoDoHeroi direcao;
    protected int tamanhoDosTilesDoMapa;
//++++++++++++++++++++++++++++++++++++++++++

    protected  void movimentaEmDirecaoAoPontoAlvo(AbstractPathPoint pontoAlvo){
        int colunaDoHeroi = (this.x) / tamanhoDosTilesDoMapa;
        int linhaDoHeroi = (this.y) / tamanhoDosTilesDoMapa;
        if (colunaDoHeroi < pontoAlvo.getX()) {
            setDirecao(DirecaoDoHeroi.DIREITA);
        }

        if (pontoAlvo.getX() < colunaDoHeroi) {
            setDirecao(DirecaoDoHeroi.ESQUERDA);
        }

        if (linhaDoHeroi < pontoAlvo.getY()) {
            setDirecao(DirecaoDoHeroi.BAIXO);
        }

        if (linhaDoHeroi > pontoAlvo.getY()) {
            setDirecao(DirecaoDoHeroi.CIMA);
        }
    }
//++++++++++++++++++++++++++++++++++++++++++

    private void ajustaQuadroAtual() {
        switch (direcao) {
            case DIREITA:
                if (quadroAtual > 11) {
                    quadroAtual = 8;
                }
                break;
            case ESQUERDA:
                if (quadroAtual > 7) {
                    quadroAtual = 4;
                }
                break;
            case CIMA:
                if (quadroAtual < 12) {
                    quadroAtual = 12;
                }
                break;
            case BAIXO:
                if (quadroAtual > 3) {
                    quadroAtual = 0;
                }
                break;
            default:
                quadroAtual = 0;
        }
    }
    //++++++++++++++++++++++++++++++++++++++++++
}