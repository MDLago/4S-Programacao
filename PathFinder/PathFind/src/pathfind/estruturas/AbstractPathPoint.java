package pathfind.estruturas;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)
public abstract class AbstractPathPoint {

    public AbstractPathPoint() {
        x = y = z = 0;
    }
    
    public void setAdjacentPoints(AbstractPathPoint adjacentPointsArray[]) {
        this.adjacentPointsArray = adjacentPointsArray;
    }

    public abstract boolean isWalkable();


    public float getZ() {
        return z;
    }//a princ�pio s� usei x e y, mas o Z est� a� pra implementa��es futuras :)

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public AbstractPathPoint[] getAdjacentPointsArray() {
        return this.adjacentPointsArray;
    }


    protected float x, y, z;
    private AbstractPathPoint[] adjacentPointsArray;
}