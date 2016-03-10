package fr.unice.polytech.ogl.islba.ia.secondIA;

import fr.unice.polytech.ogl.islba.command.Command;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeStart;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * Real automaton for the bot
 */
public class NodeIA {
    
    private Node currentNode;

    private EtatDeJeu etat;

    public NodeIA(EtatDeJeu etat){
        this.etat = etat;
        this.currentNode = new NodeStart(this.etat);
    }
    
    /**
     * Take the next decision and return the command associated.
     * Change of node too.
     * @param etat
     *          the current state of the game
     * @return the command we have to execute
     */
    public Command takeDecision(EtatDeJeu etat){
        this.etat = etat;
        this.currentNode = this.currentNode.nextNode();
        return currentNode.nodeCommand();
    }
}
