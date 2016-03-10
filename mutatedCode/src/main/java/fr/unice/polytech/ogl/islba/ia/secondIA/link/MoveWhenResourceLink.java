package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionResourceAndOnMap;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The link to reach a Move Node if we know there is a specific
 * resource on the Case in the Direction pass in parameter
 *
 */
public class MoveWhenResourceLink extends Link{

    //the Direction where we want to check if we can move
    private Direction dir;
    
    /**
     * Create a MoveWhenResourceLink link.
     * 
     * @param etat
     *      the current state of the game
     * @param dir
     *       the direction where we will possibly go
     */
    public MoveWhenResourceLink(EtatDeJeu etat, Direction dir) {
        super(etat);
        this.dir = dir;
        this.conditions.add(new ConditionResourceAndOnMap(this.getEtat(), this.dir));
    }

}
