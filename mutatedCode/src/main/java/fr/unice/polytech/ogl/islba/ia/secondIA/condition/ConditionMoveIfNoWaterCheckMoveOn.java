package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The condition is the same than the ConditionMoveIfNoWater
 * but with the difference here is that we check if we have already walk
 * on the tile we are checking
 *
 */
public class ConditionMoveIfNoWaterCheckMoveOn extends ConditionMoveIfNoWater{

    public ConditionMoveIfNoWaterCheckMoveOn(EtatDeJeu etat, Direction dir) {
        super(etat, dir);
    }
    
    @Override
    public boolean isOK(){
        Coordonnees cooCase3 = Coordonnees.add(this.getEtat().getMapMonde().getCurrentCoo(), (Coordonnees.multiplyBy(this.getDirection().getCoo(), 3)));
        Case tile = this.getEtat().getMapMonde().getCase(cooCase3);
        return super.isOK() && !tile.getMoveOn();
    }

}
