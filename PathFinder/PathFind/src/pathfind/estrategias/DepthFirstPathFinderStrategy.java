package pathfind.estrategias;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pathfind.Path;
import pathfind.estruturas.AbstractPathPoint;

public class DepthFirstPathFinderStrategy implements PathFinderStrategy {

    @Override
    public Path getComputedPath(AbstractPathPoint startPoint, AbstractPathPoint endPoint) {
        resetDataMemory();
        currentStartPoint = startPoint;
        currentEndPoint = endPoint;
        insertPointInOpenSet(startPoint);
        AbstractPathPoint currentPoint = null;
        numberOfIterarionsWhenFindingLastPath = 0;
        while (!openSet.isEmpty()) {
            currentPoint = openSet.get(0);
            openSet.remove(0);
            if (currentPoint == endPoint) {
                break;//se chegou no n� alvo antes de visitar todos os nos de open set
            }
            markPointAsVisited(currentPoint);
            putAdjacentPointsIntoOpenSet(currentPoint);
            ++numberOfIterarionsWhenFindingLastPath;
        }
        return buildFinalPathWalkingBackIntoPointParents(endPoint);
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //conjunto dos n�s (lugares do mapa)  que ainda n�o foram visitados pelo algoritmo e que s�o poss�veis candidatos para futuras visitas
    protected List<AbstractPathPoint> openSet = new ArrayList<AbstractPathPoint>();
    //um hashmap com as informa��es sobre cada ponto do mapa, se o ponto j� foi visitado, se est�na lista de n�s n�o visitados
    protected Map<AbstractPathPoint, PathPointInfos> pathPointsInfos = new HashMap<AbstractPathPoint, PathPointInfos>();
    protected AbstractPathPoint currentStartPoint;
    protected AbstractPathPoint currentEndPoint;
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void markPointAsInOpenSet(AbstractPathPoint point) {
        getInfosToPoint(point).isInOpenSet = true;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void markPointAsVisited(AbstractPathPoint point) {
        getInfosToPoint(point).visited = true;
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private boolean pointIsVisited(AbstractPathPoint point) {
        return getInfosToPoint(point).visited;
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void resetDataMemory() {
        openSet.clear();
        pathPointsInfos.clear();
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    protected void insertPointInOpenSet(AbstractPathPoint newPoint) {
        openSet.add(newPoint);
        getInfosToPoint(newPoint).isInOpenSet = true;
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void putAdjacentPointsIntoOpenSet(AbstractPathPoint currentPoint) {
        AbstractPathPoint[] adjacentPointsArray = currentPoint.getAdjacentPointsArray();
        //final int arraySize = currentPoint.getNumberOfAdjacentPoints();
        PathPointInfos adjacentPointInfos = null;
        AbstractPathPoint adjacentPoint = null;
        for (int i = 0; i < adjacentPointsArray.length; i++) {
            //System.out.println(i);
            adjacentPoint = adjacentPointsArray[i];
            adjacentPointInfos = getInfosToPoint(adjacentPoint);
            if (!adjacentPointInfos.isInOpenSet && !adjacentPointInfos.visited && adjacentPoint.isWalkable()) {
                insertPointInOpenSet(adjacentPoint);
                adjacentPointInfos.parent = currentPoint;
            }
        }
    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public Path buildFinalPathWalkingBackIntoPointParents(AbstractPathPoint endPoint) {
        Path finalPath = new Path(numberOfIterarionsWhenFindingLastPath);
        AbstractPathPoint currentPoint = endPoint;
        while (currentPoint != null) {
            finalPath.addPoint(currentPoint);
            currentPoint = getInfosToPoint(currentPoint).parent;
        }
        return finalPath;
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    protected PathPointInfos getInfosToPoint(AbstractPathPoint point) {
        PathPointInfos infos = pathPointsInfos.get(point);
        if (infos != null) {
            return infos;
        }

        //caso não tenha encontrado as informações no mapa
        infos = createPathPointInfos();//factory method
        pathPointsInfos.put(point, infos);
        return infos;
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public PathPointInfos createPathPointInfos() {
        return new PathPointInfos();
    }
//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    private boolean useBufferedPaths;
    private int numberOfIterarionsWhenFindingLastPath;

    @Override
    public String getStrategyName() {
        return "Depth First";
    }

//+++++++++++++
//uma estrutura para armazenar as informa��es de um ponto do mapa em um hashmap
    public class PathPointInfos {

        public AbstractPathPoint parent;
        public boolean visited;
        public boolean isInOpenSet;
        public float cost;

        PathPointInfos() {
            this.parent = null;
            this.visited = false;
            this.isInOpenSet = false;
            this.cost = 0;
        }
    }
}
