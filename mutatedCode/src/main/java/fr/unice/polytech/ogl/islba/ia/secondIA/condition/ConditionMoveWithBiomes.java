package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.Node;
import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove;
import fr.unice.polytech.ogl.islba.model.Biome;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Coordonnees;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

/**
 * We move on a specific Case
 *
 */
public class ConditionMoveWithBiomes implements Condition{

    private int dist;
    private Direction dir;
    private EtatDeJeu etat;
    
    /**
     * We need to know the current state of the game,
     * the direction and the distance of the tile we want to check if it's interesting to go on it
     * because of its biomes.
     * @param etat
     *          the current state of the game
     * @param dist
     *          the distance of the tile
     * @param dir
     *          the direction of this tile
     */
    public ConditionMoveWithBiomes(EtatDeJeu etat,Direction dir, int dist){
        this.dist = dist;
        this.dir = dir;
        this.etat = etat;
    }
    
    /**
     * We go on this tile if one of its biomes
     * can have a resource which is an objective
     * @return true if there is a good biome, false otherwise
     */
    @Override
    public boolean isOK() {
        Coordonnees nextCoo = Coordonnees.multiplyBy(dir.getCoo(), dist);
        nextCoo = Coordonnees.add(nextCoo, this.etat.getMapMonde().getCurrentCoo());
        Case tile = etat.getMapMonde().getCase(nextCoo);
        for(Biome biome : Biome.values()){
            //if the next case has this biome
            if(tile!=null && tile.hasBiome(biome) && !tile.getMoveOn()){
                //we check each resource of the biome
                for(String resource : biome.getResources()){
                    //and see if it's an objective
                    if(etat.getTeam().isObjective(resource)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Next node is a NodeMove
     * @return a NodeMove with the direction passed in parameter of this condition
     */
    @Override
    public Node nextNode() {
        return new NodeMove(this.etat,this.dir);
    }

}
