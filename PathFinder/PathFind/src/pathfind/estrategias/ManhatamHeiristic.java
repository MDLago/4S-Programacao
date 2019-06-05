/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfind.estrategias;

import pathfind.estruturas.AbstractPathPoint;

public class ManhatamHeiristic implements HeuristicCalculator{



    @Override
    public  float getHeuristicCostTo(AbstractPathPoint currentPoint, AbstractPathPoint goalPoint) {
        float dx = Math.abs(goalPoint.getX() - currentPoint.getX());
        float dy = Math.abs(goalPoint.getY() - currentPoint.getY());
        float dz = Math.abs(goalPoint.getZ() - currentPoint.getZ());
        return (float)(dx + dy + dz);
    }

    @Override
    public String getHeuristicName() {
        return "Manhatam";
    }
}
