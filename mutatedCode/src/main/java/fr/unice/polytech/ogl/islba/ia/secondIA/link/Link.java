package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.condition.Condition;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeStop;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * The link between two Node. It has a condition to go to a Node if it respects
 * the condition.
 */
public class Link {
    // the list of conditions to go to a special state
    protected List<Condition> conditions;

    //the next Node
    private Node node;
    
    // the state of game we need
    private EtatDeJeu etat;

    /**
     * Constructor of a Link We need the List of Condition
     * 
     * @param conditions
     * @param node
     */
    public Link(EtatDeJeu etat) {
        this.conditions = new ArrayList<Condition>();
        
        this.etat = etat;
    }

    /**
     * Return the next node in function of the current state of the game and the
     * conditions of this node.
     * 
     * @return true if one of the conditions is respected, false otherwise
     */
    public Boolean isNextNode() {
        boolean respectCondition = false;
        for (Condition c : this.conditions) {
            if (c.isOK()) {
                this.node = c.nextNode();
                respectCondition = true;
                break;
            }
        }
        return respectCondition;
    }
    
    /**
     * Return the next node in function of the current state of the game and the
     * conditions of this node.
     * @return the next Node
     */
    public Node nextNode(){
        if(this.node==null)
            this.node = new NodeStop(this.etat);
        return this.node;
    }
    
    /**
     * Getter for the EtatDeJeu we have
     * @return etat, the current state of the game
     */
    public EtatDeJeu getEtat(){
        return this.etat;
    }
}
