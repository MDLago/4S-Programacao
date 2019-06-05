package pathfind.estrategias;

//Desenvolvido por Elieser A. de Jesus (elieser@univali.br)

import pathfind.estruturas.AbstractPathPoint;



//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
public class EuclidianHeiristic implements HeuristicCalculator{


    @Override
    public float getHeuristicCostTo(AbstractPathPoint currentPoint, AbstractPathPoint goalPoint) {
        double dx = Math.abs(goalPoint.getX() - currentPoint.getX());
        double dy = Math.abs(goalPoint.getY() - currentPoint.getY());
        double dz = Math.abs(goalPoint.getZ() - currentPoint.getZ());
        return (float)Math.sqrt( dx*dx + dy*dy + dz*dz);
    }

    @Override
    public String getHeuristicName() {
        return "Euclidian";
    }
};

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

