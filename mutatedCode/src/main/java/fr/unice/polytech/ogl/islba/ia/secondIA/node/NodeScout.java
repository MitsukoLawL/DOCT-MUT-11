package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import fr.unice.polytech.ogl.islba.command.Scout;
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
 * The NodeScout has as a Command a Scout command
 * and have a Direction to know where we have to scout.
 *
 */
public class NodeScout extends MyNode{

    private Direction dir;
    
    /**
     * We create a Node Scout thanks to a Direction and an EtatDeJeu
     * to know the current state of the game.
     * 
     * @param etat
     *      the current state of the game
     * @param d
     *      the direction where we have to scout
     */
    public NodeScout(EtatDeJeu etat,Direction d) {
        super(etat);
        this.dir = d;
        this.command = new Scout(this.dir);
        
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
     * Return the direction of this NodeScout
     * @return dir, the direction of the scout
     */
    public Direction getDirection(){
        return this.dir;
    }
}
