package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionStop;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * 
 * A stop link has just the stop condition
 */
public class StopLink extends Link {

    /**
     * We add the Stop condition
     * @param etat
     */
    public StopLink(EtatDeJeu etat) {
        super(etat);
        this.conditions.add(new ConditionStop(etat));
    }

}
