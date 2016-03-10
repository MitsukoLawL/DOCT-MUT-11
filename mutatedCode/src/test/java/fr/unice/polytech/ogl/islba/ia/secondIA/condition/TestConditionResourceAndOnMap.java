package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.resource.Resource;

public class TestConditionResourceAndOnMap {

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
        NodeMove.setDirection(Direction.EAST);
        
        Condition move = new ConditionResourceAndOnMap(etat,Direction.NORTH);

        assertFalse(move.isOK());
        
        Case caseNorth = new Case();
        etat.getMapMonde().addCaseMap(0, 1, caseNorth);
        assertFalse(move.isOK());
        
        caseNorth.setScouted(true);
        assertFalse(move.isOK());
        
        caseNorth.setMoveOn(true);
        assertFalse(move.isOK());
        
        caseNorth.addRessource(new Resource("Resource"));
        assertFalse(move.isOK());
        
        caseNorth.setMoveOn(false);
        assertTrue(move.isOK());
    
        assertTrue(move.nextNode() instanceof NodeMove);
    }

}
