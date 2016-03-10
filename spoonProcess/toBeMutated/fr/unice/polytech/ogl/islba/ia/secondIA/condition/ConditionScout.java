package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeScout;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class ConditionScout implements Condition{

    private EtatDeJeu etat;
    private Direction dir;
    
    public ConditionScout(EtatDeJeu e,Direction d) {
        this.etat=e;
        this.dir = d;
    }
    
    /**
     * It's ok if the Case in this direction is null or if it hasn't been scout yet.
     */
    @Override
    public boolean isOK() {
        Case nextCase = etat.getMapMonde().getCase(Coordonnees.add(dir.getCoo(), etat.getMapMonde().getCurrentCoo()));
        //if the Case hasn't already been scouted
        return (nextCase == null || !nextCase.getScouted());
    }

    /**
     * The next node is a ScoutNode
     */
    @Override
    public Node nextNode() {
        return new NodeScout(this.etat,this.dir);
    }

}
