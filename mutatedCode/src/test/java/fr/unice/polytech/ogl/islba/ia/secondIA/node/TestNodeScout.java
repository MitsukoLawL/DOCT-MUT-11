package fr.unice.polytech.ogl.islba.ia.secondIA.node;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.command.Scout;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestNodeScout {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        Node scout = new NodeScout(etat, Direction.NORTH);
        
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        Case caseNorth = new Case();
        caseNorth.setScouted(true);
        etat.getMapMonde().addCaseMap(0,1,caseNorth);
        
        assertTrue(scout.nextNode() instanceof NodeScout);
        NodeScout nextNode = (NodeScout) scout.nextNode();
        assertEquals(Direction.SOUTH, nextNode.getDirection());
        
        assertTrue(scout.nodeCommand() instanceof Scout);
    }

}
