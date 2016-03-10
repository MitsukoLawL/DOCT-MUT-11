package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove;
import fr.unice.polytech.ogl.islba.model.Biome;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * Condition to move in a direction where there isn't any water
 *
 */
public class ConditionMoveIfNoWater implements Condition{

    private Direction dir;
    private EtatDeJeu etat;
    
    /**
     * We need the current state of the game,
     * and the direction of the next tile to create a correct
     * NodeMove and state if we have to go in this Direction
     * @param etat
     * @param dir
     */
    public ConditionMoveIfNoWater(EtatDeJeu etat,Direction dir){
        this.etat = etat;
        this.dir = dir;
    }
    
    /**
     * It's ok if we haven't any water on the next tile
     * and if the tile is on the map
     * @return true if it's interesting to go on this tile, false otherwise
     */
    @Override
    public boolean isOK() {
        Coordonnees cooCase3 = Coordonnees.add(etat.getMapMonde().getCurrentCoo(), (Coordonnees.multiplyBy(this.dir.getCoo(), 3)));
        Case tile = etat.getMapMonde().getCase(cooCase3);
        return tile != null && tile.isOnMap() && !tile.hasBiome(Biome.OCEAN) 
                && !tile.hasBiome(Biome.LAKE);
    }

    /**
     * The node move has as a direction the direction
     * of the tile we analyzed
     * @return a nodeMove with the direction of the tile in parameter
     */
    @Override
    public Node nextNode() {
        return new NodeMove(this.etat,this.dir);
    }

    /**
     * Getter for the EtatDeJeu of this Condition
     * @return the current state of the game
     */
    public EtatDeJeu getEtat(){
        return this.etat;
    }
    
    /**
     * Getter for the direction of the check
     * @return the direction where the tile we want to check is
     */
    public Direction getDirection(){
        return this.dir;
    }
    
}
