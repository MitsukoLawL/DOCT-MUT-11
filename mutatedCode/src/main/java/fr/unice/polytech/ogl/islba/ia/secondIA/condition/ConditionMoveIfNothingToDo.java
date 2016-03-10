package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionMoveIfNothingToDo implements fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition {
    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    public ConditionMoveIfNothingToDo(fr.unice.polytech.ogl.islba.model.EtatDeJeu etat) {
        this.etat = etat;
    }

    @java.lang.Override
    public boolean isOK() {
        return true;
    }

    @java.lang.Override
    public fr.unice.polytech.ogl.islba.ia.secondIA.node.Node nextNode() {
        fr.unice.polytech.ogl.islba.model.Direction dirMove = fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove.getDirection();
        fr.unice.polytech.ogl.islba.model.Case next = etat.getMapMonde().getCase(fr.unice.polytech.ogl.islba.model.Coordonnees.add(dirMove.getCoo(), etat.getMapMonde().getCurrentCoo()));
        dirMove = dirMove.getNewDirection();
        if ((next == null) && (!(next.isOnMap()))) {
            dirMove = dirMove.getNewDirection();
            next = etat.getMapMonde().getCase(fr.unice.polytech.ogl.islba.model.Coordonnees.add(dirMove.getCoo(), etat.getMapMonde().getCurrentCoo()));
        } 
        return new fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove(this.etat , dirMove);
    }
}