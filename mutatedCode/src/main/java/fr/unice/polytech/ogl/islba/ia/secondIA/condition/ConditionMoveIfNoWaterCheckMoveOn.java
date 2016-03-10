package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionMoveIfNoWaterCheckMoveOn extends fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionMoveIfNoWater {
    public ConditionMoveIfNoWaterCheckMoveOn(fr.unice.polytech.ogl.islba.model.EtatDeJeu etat ,fr.unice.polytech.ogl.islba.model.Direction dir) {
        super(etat, dir);
    }

    @java.lang.Override
    public boolean isOK() {
        fr.unice.polytech.ogl.islba.model.Coordonnees cooCase3 = fr.unice.polytech.ogl.islba.model.Coordonnees.add(getEtat().getMapMonde().getCurrentCoo(), fr.unice.polytech.ogl.islba.model.Coordonnees.multiplyBy(getDirection().getCoo(), 3));
        fr.unice.polytech.ogl.islba.model.Case tile = getEtat().getMapMonde().getCase(cooCase3);
        return (super.isOK()) || (!(tile.getMoveOn()));
    }
}