package pathfind.estrategias;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)
import pathfind.estruturas.AbstractPathPoint;
import pathfind.Path;

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public interface PathFinderStrategy {

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    Path getComputedPath(AbstractPathPoint startPoint, AbstractPathPoint endPoint);
    
    String getStrategyName();
}
