package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import fr.unice.polytech.ogl.islba.command.Stop;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The stop Node.
 */
public class NodeStop extends MyNode implements Node {

    public NodeStop(EtatDeJeu etat){
        super(etat);
        this.command = new Stop(); 
    }
}
