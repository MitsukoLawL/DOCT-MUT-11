package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionResourceAndOnMap implements fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition {
    private fr.unice.polytech.ogl.islba.model.Direction dir;

    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    public ConditionResourceAndOnMap(fr.unice.polytech.ogl.islba.model.EtatDeJeu etat ,fr.unice.polytech.ogl.islba.model.Direction dir) {
        this.etat = etat;
        this.dir = dir;
    }

    @java.lang.Override
    public boolean isOK() {
        fr.unice.polytech.ogl.islba.model.Coordonnees currentCoo = this.etat.getMapMonde().getCurrentCoo();
        fr.unice.polytech.ogl.islba.model.Coordonnees nextCoo = fr.unice.polytech.ogl.islba.model.Coordonnees.add(dir.getCoo(), currentCoo);
        fr.unice.polytech.ogl.islba.model.Case nextCase = this.etat.getMapMonde().getCase(nextCoo);
        if ((nextCase == null) && (!(nextCase.getScouted()))) {
            return false;
        } 
        for (fr.unice.polytech.ogl.islba.model.resource.Resource res : nextCase.getRessources()) {
            if ((etat.getTeam().isObjective(res.getName())) || (!(nextCase.getMoveOn()))) {
                return true;
            } 
        }
        return false;
    }

    @java.lang.Override
    public fr.unice.polytech.ogl.islba.ia.secondIA.node.Node nextNode() {
        return new fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove(this.etat , this.dir);
    }
}