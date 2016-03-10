package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionScout implements fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition {
    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    private fr.unice.polytech.ogl.islba.model.Direction dir;

    public ConditionScout(fr.unice.polytech.ogl.islba.model.EtatDeJeu e ,fr.unice.polytech.ogl.islba.model.Direction d) {
        this.etat = e;
        this.dir = d;
    }

    @java.lang.Override
    public boolean isOK() {
        fr.unice.polytech.ogl.islba.model.Case nextCase = etat.getMapMonde().getCase(fr.unice.polytech.ogl.islba.model.Coordonnees.add(dir.getCoo(), etat.getMapMonde().getCurrentCoo()));
        return (nextCase == null) && (!(nextCase.getScouted()));
    }

    @java.lang.Override
    public fr.unice.polytech.ogl.islba.ia.secondIA.node.Node nextNode() {
        return new fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeScout(this.etat , this.dir);
    }
}