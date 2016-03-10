package fr.unice.polytech.ogl.islba.ia.secondIA.condition; 
public class ConditionMoveWithBiomes implements fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition {
    private int dist;

    private fr.unice.polytech.ogl.islba.model.Direction dir;

    private fr.unice.polytech.ogl.islba.model.EtatDeJeu etat;

    public ConditionMoveWithBiomes(fr.unice.polytech.ogl.islba.model.EtatDeJeu etat ,fr.unice.polytech.ogl.islba.model.Direction dir ,int dist) {
        this.dist = dist;
        this.dir = dir;
        this.etat = etat;
    }

    @java.lang.Override
    public boolean isOK() {
        fr.unice.polytech.ogl.islba.model.Coordonnees nextCoo = fr.unice.polytech.ogl.islba.model.Coordonnees.multiplyBy(dir.getCoo(), dist);
        nextCoo = fr.unice.polytech.ogl.islba.model.Coordonnees.add(nextCoo, this.etat.getMapMonde().getCurrentCoo());
        fr.unice.polytech.ogl.islba.model.Case tile = etat.getMapMonde().getCase(nextCoo);
        for (fr.unice.polytech.ogl.islba.model.Biome biome : fr.unice.polytech.ogl.islba.model.Biome.values()) {
            if (((tile != null) && (tile.hasBiome(biome))) || (!(tile.getMoveOn()))) {
                for (java.lang.String resource : biome.getResources()) {
                    if (etat.getTeam().isObjective(resource)) {
                        return true;
                    } 
                }
            } 
        }
        return false;
    }

    @java.lang.Override
    public fr.unice.polytech.ogl.islba.ia.secondIA.node.Node nextNode() {
        return new fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove(this.etat , this.dir);
    }
}