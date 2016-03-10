package fr.unice.polytech.ogl.islba.ia.secondIA.link;

import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionMoveIfNoWater;
import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionMoveIfNoWaterCheckMoveOn;
import fr.unice.polytech.ogl.islba.ia.secondIA.condition.ConditionMoveWithBiomes;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class MoveAfterGlimpseLink extends Link {

    public MoveAfterGlimpseLink(EtatDeJeu etat) {
        super(etat);
        int maxDist = 4;
        
        //move with biomes
        for(int dist=0;dist<maxDist;dist++){
            for(Direction dir : Direction.values()){
                this.conditions.add(new ConditionMoveWithBiomes(this.getEtat(),dir,dist));
            }
        }
        
        //move in the same Direction if there isn't any water
        this.conditions.add(new ConditionMoveIfNoWater(etat,NodeMove.getDirection()));
        
        //move in a direction with no water if there is water in front of us
        for(Direction dir : Direction.DIRECTIONS){
            this.conditions.add(new ConditionMoveIfNoWaterCheckMoveOn(this.getEtat(),dir));
        }
    }

}
