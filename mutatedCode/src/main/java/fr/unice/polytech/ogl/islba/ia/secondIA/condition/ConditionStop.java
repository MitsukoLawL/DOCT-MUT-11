package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionStop implements fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition {
    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    public ConditionStop(fr.unice.polytech.ogl.islba.model.EtatDeJeu etat) {
        this.etat = etat;
    }

    @java.lang.Override
    public boolean isOK() {
        return ((etat.getPA()) < (paToStop())) && (etat.getTeam().noMoreObjectives());
    }

    private int paToStop() {
        int paToStop = 15;
        if ((etat.getPAMax()) == (etat.getPA())) {
            return 0;
        } 
        if ((etat.getMapMonde().getCurrentCoo()) != null) {
            paToStop += (java.lang.Math.abs(etat.getMapMonde().getCurrentCoo().getX())) + ((java.lang.Math.abs(etat.getMapMonde().getCurrentCoo().getY())) * 3);
        } 
        return paToStop;
    }

    @java.lang.Override
    public fr.unice.polytech.ogl.islba.ia.secondIA.node.Node nextNode() {
        return new fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeStop(etat);
    }
}