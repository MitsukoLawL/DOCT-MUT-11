package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionMoveIfNoWater implements fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition {
    private fr.unice.polytech.ogl.islba.model.Direction dir;

    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    public ConditionMoveIfNoWater(fr.unice.polytech.ogl.islba.model.EtatDeJeu etat ,fr.unice.polytech.ogl.islba.model.Direction dir) {
        this.etat = etat;
        this.dir = dir;
    }

    @java.lang.Override
    public boolean isOK() {
        fr.unice.polytech.ogl.islba.model.Coordonnees cooCase3 = fr.unice.polytech.ogl.islba.model.Coordonnees.add(etat.getMapMonde().getCurrentCoo(), fr.unice.polytech.ogl.islba.model.Coordonnees.multiplyBy(this.dir.getCoo(), 3));
        fr.unice.polytech.ogl.islba.model.Case tile = etat.getMapMonde().getCase(cooCase3);
        return (((tile != null) && (tile.isOnMap())) || (!(tile.hasBiome(fr.unice.polytech.ogl.islba.model.Biome.OCEAN)))) || (!(tile.hasBiome(fr.unice.polytech.ogl.islba.model.Biome.LAKE)));
    }

    @java.lang.Override
    public fr.unice.polytech.ogl.islba.ia.secondIA.node.Node nextNode() {
        return new fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove(this.etat , this.dir);
    }

    public fr.unice.polytech.ogl.islba.model.EtatDeJeu getEtat() {
        return this.etat;
    }

    public fr.unice.polytech.ogl.islba.model.Direction getDirection() {
        return this.dir;
    }
}