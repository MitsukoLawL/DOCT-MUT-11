package fr.unice.polytech.ogl.islba.ia.secondIA.condition;


import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.resource.Resource;

public class ConditionResourceAndOnMap implements Condition {

    private Direction dir;
    private EtatDeJeu etat;
    
    public ConditionResourceAndOnMap(EtatDeJeu etat, Direction dir){
        this.etat = etat;
        this.dir = dir;
    }
    
    /**
     * We Move if the next tile has a good resource for us
     * and if we haven't walk over it.
     */
    @Override
    public boolean isOK() {
        Coordonnees currentCoo = this.etat.getMapMonde().getCurrentCoo();
        Coordonnees nextCoo = Coordonnees.add(dir.getCoo(), currentCoo);
        Case nextCase = this.etat.getMapMonde().getCase(nextCoo);
        if(nextCase == null || !nextCase.getScouted()){
            return false;
        }
        for(Resource res : nextCase.getRessources()){
            if(etat.getTeam().isObjective(res.getName()) && !nextCase.getMoveOn()){
                return true;
            }
        }
        return false;
    }

    @Override
    public Node nextNode() {
        return new NodeMove(this.etat,this.dir);
    }

}
