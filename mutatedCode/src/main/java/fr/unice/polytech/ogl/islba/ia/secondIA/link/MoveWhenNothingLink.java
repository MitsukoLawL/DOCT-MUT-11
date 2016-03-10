package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionMoveIfNothingToDo;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The link to reach a NodeMove if there is nothing else to do
 *
 */
public class MoveWhenNothingLink extends Link{

    /**
     * Need the current state of the game
     * @param etat
     *          the current state of the game
     */
    public MoveWhenNothingLink(EtatDeJeu etat) {
        super(etat);
        this.conditions.add(new ConditionMoveIfNothingToDo(this.getEtat()));
    }

}
