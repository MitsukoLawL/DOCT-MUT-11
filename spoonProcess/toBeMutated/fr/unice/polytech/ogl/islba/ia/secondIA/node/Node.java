package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import fr.unice.polytech.ogl.islba.command.Command;

/**
 * A node of the automaton
 */
public interface Node {

    /**
     * Return the next Node
     * @return a Node which is the next one
     */
    public Node nextNode();
    
    /**
     * Return the Command associated to the current Node
     * @return the Command of this Node
     */
    public Command nodeCommand();
}
