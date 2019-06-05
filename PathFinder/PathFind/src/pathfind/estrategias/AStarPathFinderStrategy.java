package pathfind.estrategias;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)

import pathfind.estruturas.AbstractPathPoint;

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//existem v�rios algoritmos para calcular a heir�stica do A*, por isso vale a pena encapsular essa parte do algoritmo em uma classe separada
//Para implementar outra heur�stica basta derivar desta classe abstrata e implementar o seu �nico m�todo de um jeito diferente.
//O arquivo 'AStarDistanceCalculators.h' tem duas varia��es de heur�sticas
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public class AStarPathFinderStrategy extends DepthFirstPathFinderStrategy {

    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public AStarPathFinderStrategy(HeuristicCalculator heuristicCalculator) {
        this.heuristicCalculator = heuristicCalculator;
    }

    @Override
    public String getStrategyName() {
        return "A* (" + heuristicCalculator.getHeuristicName() + ")";
    }

    
    
    public void setHeuristicCalculator(HeuristicCalculator newCalculator) {
        if (newCalculator != this.heuristicCalculator) {
            this.heuristicCalculator = newCalculator;
        }
    }
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    protected HeuristicCalculator heuristicCalculator;
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    protected float getTotalPointCost(AbstractPathPoint currentPoint) {
        return getInfosToPoint(currentPoint).cost + getCostToEndPoint(currentPoint);
    }

    protected float getCostToTargetPoint(AbstractPathPoint referencePoint, AbstractPathPoint targetPoint) {
        float dx = Math.abs(referencePoint.getX() - targetPoint.getX());
        float dy = Math.abs(referencePoint.getY() - targetPoint.getY());
        if ((dx + dy) == 2) {
            return getInfosToPoint(referencePoint).cost + 14;
        }
        if ((dx + dy) == 1) {
            return getInfosToPoint(referencePoint).cost + 10;
        }
        return 0;
        /*
        -2 -1  2
        -1  0  1
        -2  1  2
         */
    }

    protected float getCostToEndPoint(AbstractPathPoint currentPoint) {
        return this.heuristicCalculator.getHeuristicCostTo(currentPoint, currentEndPoint);
    }

    @Override
    protected void insertPointInOpenSet(AbstractPathPoint newPoint) {
        int i = 0;
        for (AbstractPathPoint currentPoint : openSet) {
            if (getTotalPointCost(newPoint) <= getTotalPointCost(currentPoint)) {
                openSet.add(i, newPoint);
                getInfosToPoint(newPoint).isInOpenSet = true;
                return;
            }
            i++;
        }

        super.insertPointInOpenSet(newPoint);
    }

    protected void putAdjacentPointsIntoOpenSet(AbstractPathPoint currentPoint) {
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}