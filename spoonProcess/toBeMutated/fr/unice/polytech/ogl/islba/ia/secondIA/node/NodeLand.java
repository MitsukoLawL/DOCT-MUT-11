package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import fr.unice.polytech.ogl.islba.command.Land;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.ExploitLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.GlimpseLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.MoveAfterGlimpseLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.MoveWhenNothingLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.MoveWhenResourceLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.ScoutLink;
import fr.unice.polytech.ogl.islba.ia.secondIA.link.TransformLink;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class NodeLand extends MyNode {

    public NodeLand(EtatDeJeu etat, int nbPersonToLand, String creekId) {
        super(etat);
        this.command = new Land(nbPersonToLand, creekId);
        
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
