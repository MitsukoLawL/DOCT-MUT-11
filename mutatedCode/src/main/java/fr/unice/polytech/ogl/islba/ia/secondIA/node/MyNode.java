package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import java.util.ArrayList;
import java.util.List;

import fr.unice.polytech.ogl.islba.command.Command;
import fr.unice.polytech.ogl.islba.command.Stop;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.Link;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.StopLink;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * 
 * Our implementation of Node with a List of Link
 * 
 */
public class MyNode implements Node{
    protected Command command;
    protected List<Link> links;
    private EtatDeJeu etat;

    /**
     * Initialize MyNode with an EtatDeJeu given in parameter
     * @param etat
     *          the state of the game
     */
    public MyNode(EtatDeJeu etat){
        this.etat = etat;
        this.command = new Stop();
        this.links = new ArrayList<Link>();
        this.links.add(new StopLink(etat));    
    }

    /**
     * Return the next node given the links
     * if we verify the condition of a link,
     * we get the Node associated to this condition and we return it
     * @return the next node
     */
    @Override
    public Node nextNode() {
        //in case of we haven't any links good or if there isn't any links, we go in the Stop node
        Node nextNode = new NodeStop(this.etat);
        for(Link l : this.links){
            if(l.isNextNode()){
                nextNode = l.nextNode();
                break;
            }
        }
        return nextNode;
    }
    
    /**
     * Return the Command of the Node
     * @return the command of this node
     */
    @Override
    public Command nodeCommand(){
        return this.command;
    }
    
    /**
     * Return the current EtatDeJeu
     * @return the EtatDeJeu
     */
    public EtatDeJeu getEtat(){
        return this.etat;
    }
}
