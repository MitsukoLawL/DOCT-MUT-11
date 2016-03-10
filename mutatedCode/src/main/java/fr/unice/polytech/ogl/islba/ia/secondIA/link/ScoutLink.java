package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionScout;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * 
 *The Scout Link.
 *
 */
public class ScoutLink extends Link{

    private Direction dir;
    
    public ScoutLink(EtatDeJeu etat, Direction dir) {
        super(etat);
        this.dir = dir;
        this.conditions.add(new ConditionScout(this.getEtat(),this.dir));
    }

}
