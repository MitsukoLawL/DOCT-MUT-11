package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * If there is nothing else to do, we move in the same direction
 * than previously
 *
 */
public class ConditionMoveIfNothingToDo implements Condition{

    private EtatDeJeu etat;
    
    /**
     * 
     * @param etat
     */
    public ConditionMoveIfNothingToDo(EtatDeJeu etat){
        this.etat = etat;
    }
    
    /**
     * Always true, it's the last thing we can do
     * @return true
     */
    @Override
    public boolean isOK() {
        return true;
    }

    /**
     * If we haven't anything to do, that's because there is water all around us,
     * so we go right but if the next tile is out of map, we turn back
     * @return a NodeMove in a correct Direction
     */
    @Override
    public Node nextNode() {
        Direction dirMove = NodeMove.getDirection();
        Case next = etat.getMapMonde().getCase(Coordonnees.add(dirMove.getCoo(), etat.getMapMonde().getCurrentCoo()));

        dirMove= dirMove.getNewDirection();
        //if the next Case is out of bound, we turn right
        //if this one is out of bound too, we turn back
        if(next==null || !next.isOnMap()){
            dirMove= dirMove.getNewDirection();
            next = etat.getMapMonde().getCase(Coordonnees.add(dirMove.getCoo(), etat.getMapMonde().getCurrentCoo()));
        }
        return new NodeMove(this.etat, dirMove);
    }

}
