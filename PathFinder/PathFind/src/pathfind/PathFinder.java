package pathfind;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)

import pathfind.estruturas.AbstractPathPoint;
import pathfind.estrategias.PathFinderStrategy;


public class PathFinder{
    

        public PathFinder(PathFinderStrategy findingStrategy){
            this.findingStrategy = findingStrategy;
        }

        public Path getPath(AbstractPathPoint startPoint, AbstractPathPoint endPoint){
            if(findingStrategy != null){
                return findingStrategy.getComputedPath(startPoint, endPoint);
            }
            return null;
        }

        public void setPathFindingStrategy( PathFinderStrategy newStrategy){
            findingStrategy = newStrategy;
        }

        private PathFinderStrategy findingStrategy;
}