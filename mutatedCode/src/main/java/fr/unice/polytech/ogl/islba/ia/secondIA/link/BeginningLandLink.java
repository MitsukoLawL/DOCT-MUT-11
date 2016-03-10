package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeLand;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The BeginningLandLink.
 * It's a link to reach the NodeLand.
 * @author user
 *
 */
public class BeginningLandLink extends Link {

    public BeginningLandLink(EtatDeJeu etat) {
        super(etat);
    }

    /**
     * When it's the first land we have to do,
     * we have to do it so it return true in all case
     * @return true because if it's a link, we have to land
     */
    @Override
    public Boolean isNextNode() {
        return true;
    }
    
    /**
     * Return a NodeLand
     * @return a NodeLand
     */
    public Node nextNode(){
        //we sayy that at the beginning we only want to land one men
        int nbPersonToLand = 1;
        return new NodeLand(this.getEtat(), nbPersonToLand, this.getEtat().getCreekID());
    }
    
}
