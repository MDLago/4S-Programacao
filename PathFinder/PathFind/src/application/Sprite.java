package application;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)

import java.awt.Image;
import javax.swing.ImageIcon;



public class Sprite{

    
        private static Image loadBitmap(String nomeDaImagem){
            return new ImageIcon( Sprite.class.getClassLoader().getResource(nomeDaImagem)).getImage();
        }
   
        public Sprite(String nomeDaImagem){
                bitmap = loadBitmap(nomeDaImagem);
                altura = bitmap.getHeight(null);
                largura = bitmap.getWidth(null);
                totalDeQuadros = 1;
                quadroAtual = 0;
                x = y = 0;
                dx = dy = 0.f;
                tempoEntreQuadros = 0;
        }

        public Sprite(String nomeDaImagem, int largura, int altura, int totalDeQuadros, int colunasDoSpriteSheet, int quadrosPorSegundo){
            bitmap = loadBitmap(nomeDaImagem);
            this.totalDeQuadros =   totalDeQuadros;
            this.colunasNoSpriteSheet = colunasDoSpriteSheet;
            tempoDeCadaQuadro = (short)(1000/quadrosPorSegundo);
            this.altura = altura;
            this.largura = largura;
            quadroAtual = 0;
            x = y = 0;
            dx = dy = 0.f;
            tempoEntreQuadros = 0;
        }

        public void desenha( Image buffer){
            int sourceX = (quadroAtual % colunasNoSpriteSheet) * largura;
            int sourceY = (quadroAtual / colunasNoSpriteSheet) * altura;
            buffer.getGraphics().drawImage(bitmap, 
                    this.x - largura/2, this.y-altura/2,
                    this.x + largura/2, this.y+altura/2, 
                    sourceX, sourceY, sourceX+largura, sourceY+altura, null );
            //masked_blit( bitmap, buffer, sourceX, sourceY, this.x - largura/2, this.y-altura/2, largura, altura);//desenha centralizado
        }

        void update(long tempoDecorrido){
            int quadrosQueSeraoAvancados = (int)( (tempoEntreQuadros + tempoDecorrido) / tempoDeCadaQuadro);
            tempoEntreQuadros = (tempoEntreQuadros + tempoDecorrido) % tempoDeCadaQuadro;
            quadroAtual = (short)((quadroAtual + quadrosQueSeraoAvancados) % totalDeQuadros);
            this.x += (int)dx;
            this.y += (int)dy;
        }

        protected int x, y;
        protected float dx, dy;

    
        protected Image bitmap;
        protected int totalDeQuadros;
        protected int quadroAtual;
        protected int altura;
        protected int largura;
        protected int colunasNoSpriteSheet;
        protected int tempoDeCadaQuadro;
        protected long tempoEntreQuadros;
}

