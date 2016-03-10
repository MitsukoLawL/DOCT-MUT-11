package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import java.util.Map;

import fr.unice.polytech.ogl.islba.command.Transform;
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
 * The node which correponds to the Transform Command
 * We reach it if we have a transformation possible
 *
 */
public class NodeTransform extends MyNode{

    /**
     * This node is set by the current state of the game
     * and with a map with the resources we need and the number
     * of them we need
     * @param etat
     *          the current state of the game
     * @param resourcesForTransformation
     *          the map with the name of the resource we need as key, and its number as value
     */
    public NodeTransform(EtatDeJeu etat, Map<String, Integer> resourcesForTransformation) {
        super(etat);
        this.command = new Transform(resourcesForTransformation);
        
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

}
