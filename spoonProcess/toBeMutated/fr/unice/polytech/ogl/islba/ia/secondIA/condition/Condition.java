package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;

/**
 * Created by Michael on 18/05/2015.
 */
public interface Condition {
    
    public boolean isOK();
    
    public Node nextNode();
}
