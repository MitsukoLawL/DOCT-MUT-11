package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionGlimpse implements fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition {
    private fr.unice.polytech.ogl.islba.model.Direction dir;

    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    public ConditionGlimpse(fr.unice.polytech.ogl.islba.model.EtatDeJeu etat ,fr.unice.polytech.ogl.islba.model.Direction dir) {
        this.etat = etat;
        this.dir = dir;
    }

    @java.lang.Override
    public boolean isOK() {
        fr.unice.polytech.ogl.islba.model.Case nextCase = etat.getMapMonde().getCase(fr.unice.polytech.ogl.islba.model.Coordonnees.add(dir.getCoo(), etat.getMapMonde().getCurrentCoo()));
        return (nextCase == null) && ((nextCase.getNumberCaseGlimpse()) < 0);
    }

    @java.lang.Override
    public fr.unice.polytech.ogl.islba.ia.secondIA.node.Node nextNode() {
        return new fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeGlimpse(this.etat , this.dir , 4);
    }
}