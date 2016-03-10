package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeScout;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestConditionScout {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        //we land
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        Condition scout = new ConditionScout(etat, Direction.NORTH);
        
        assertTrue(scout.isOK());
        
        Case caseNorth = new Case();
        caseNorth.setScouted(false);
        etat.getMapMonde().addCaseMap(0, 1, caseNorth);
        
        assertTrue(scout.isOK());
        
        caseNorth.setScouted(true);
        
        assertFalse(scout.isOK());
        
        assertTrue(scout.nextNode() instanceof NodeScout);
        NodeScout nextNode = (NodeScout) scout.nextNode();
        
        assertEquals(Direction.NORTH, nextNode.getDirection());
        
    }

}
