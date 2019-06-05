package pathfind;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)
import pathfind.estruturas.AbstractPathPoint;
import java.util.ArrayList;
import java.util.List;

public class Path {

    public Path(int numberOfIterationsToFindThePath) {
        iterations = numberOfIterationsToFindThePath;
    }

    public void addPoint(AbstractPathPoint newPoint) {
        pathPoints.add(newPoint);
    }

    public int getNumberOfPoints() {
        return pathPoints.size();
    }

    public AbstractPathPoint getPointAt(int pointIndex) {
        return pathPoints.get(pointIndex);
    }

    public int getNumberOfIterationsToFindThePath() {
        return iterations;
    }
    private List<AbstractPathPoint> pathPoints = new ArrayList<AbstractPathPoint>();
    private int iterations;
}

