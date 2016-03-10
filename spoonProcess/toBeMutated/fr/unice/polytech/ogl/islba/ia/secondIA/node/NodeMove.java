package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import fr.unice.polytech.ogl.islba.command.Move;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.ExploitLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.GlimpseLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.MoveAfterGlimpseLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.MoveWhenNothingLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.MoveWhenResourceLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.ScoutLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.TransformLink;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * The Node Move.
 * Its Command is a move command in the Direction we have passed in parameter.
 *
 */
public class NodeMove extends MyNode {
    //the Direction where we have to move
    private static Direction dir = Direction.EAST;
    
    /**
     * The Node Move needs two things : 
     *          -the Direction where we want to move
     *          -the current state of the game
     * @param etat
     * @param dir
     */
    public NodeMove(EtatDeJeu etat, Direction d) {
        super(etat);
        setDirection(d);
        this.command = new Move(getDirection());
        
        this.links.add(new TransformLink(this.getEtat()));
        this.links.add(new ExploitLink(this.getEtat()));
        
        for(Direction direction : Direction.values()){
            this.links.add(new ScoutLink(this.getEtat(), direction));
            this.links.add(new MoveWhenResourceLink(this.getEtat(), direction));
        }
        
        this.links.add(new GlimpseLink(this.getEtat()));
        this.links.add(new MoveAfterGlimpseLink(this.getEtat()));
        
        this.links.add(new MoveWhenNothingLink(this.getEtat()));
    }

    /**
     * Set the Direction of the Node
     * @param d
     *      the Direction where we will go
     */
    public static void setDirection(Direction d){
        dir = d;
    }
    
    /**
     * Get the Direction of the Node
     * @return dir, the Direction of the Node
     */
    public static Direction getDirection(){
        return dir;
    }
    
}
