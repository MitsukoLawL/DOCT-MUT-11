package fr.unice.polytech.ogl.islba.ia.secondIA.condition;


import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeGlimpse;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * Condition to glimpse.
 *
 */
public class ConditionGlimpse implements Condition{

    private Direction dir;
    private EtatDeJeu etat;
    
    /**
     * We need the Direction where we possibly want to glimpse
     * and the current state of the game to decide if we glimpse or not.
     * @param etat
     *          the current state of the game
     * @param dir
     *          the Direction where we want to check
     */
    public ConditionGlimpse(EtatDeJeu etat, Direction dir){
        this.etat = etat;
        this.dir = dir;
    }
    
    /**
     * We glimpse if the next tile hasn't been glimpse yet
     */
    @Override
    public boolean isOK() {
        Case nextCase = etat.getMapMonde().getCase(Coordonnees.add(dir.getCoo(), etat.getMapMonde().getCurrentCoo()));
        return nextCase==null || nextCase.getNumberCaseGlimpse()<0;
    }

    /**
     * The next node is a glimpse node in this direction
     * and with a range of 4.
     */
    @Override
    public Node nextNode() {
        return new NodeGlimpse(this.etat,this.dir,4);
    }

}
