/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pathfind.estrategias;

import pathfind.estruturas.AbstractPathPoint;

public interface HeuristicCalculator{
    
      float getHeuristicCostTo(AbstractPathPoint currentNode, AbstractPathPoint goalNode);
      String getHeuristicName();
};