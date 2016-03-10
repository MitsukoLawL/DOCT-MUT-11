package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionGlimpse;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The Link between a Node and a Glimpse Node
 *
 */
public class GlimpseLink extends Link {

    /**
     * The link to reach a Glimpse only need the current state of the game
     * This link has 4 conditions : one for each directions.
     * @param etat
     *          the current state of the game
     */
    public GlimpseLink(EtatDeJeu etat) {
        super(etat);
        for(Direction d : Direction.values()){
            this.conditions.add(new ConditionGlimpse(this.getEtat(),d));
        }
    }
}
