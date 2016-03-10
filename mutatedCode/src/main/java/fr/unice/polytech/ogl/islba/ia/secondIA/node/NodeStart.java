package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import fr.unice.polytech.ogl.islba.ia.secondIA.link.BeginningLandLink;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The Beginning Node of the automaton
 */
public class NodeStart extends MyNode{

    /**
     * If we haven't enough PA or if we have no objective we stop after this
     * Otherwise, we land. These are the only Links with other node that we have.
     * @param etat
     *      the current State of the game
     */
    public NodeStart(EtatDeJeu etat){
        super(etat);
        this.links.add(new BeginningLandLink(etat));
    }
}
