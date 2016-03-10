package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeMove;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;
import fr.unice.polytech.ogl.islba.model.OutOfMapCase;

public class TestConditionMoveIfNothingToDo {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        Condition move = new ConditionMoveIfNothingToDo(etat);
        
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        NodeMove.setDirection(Direction.EAST);
        Case caseEast = new Case();
        caseEast.setScouted(true);
        etat.getMapMonde().addCaseMap(1,0,caseEast);
        
        assertTrue(move.isOK());
        
        move.nextNode();
        assertEquals(Direction.SOUTH,NodeMove.getDirection());
        
        NodeMove.setDirection(Direction.EAST);
        caseEast = new OutOfMapCase();
        caseEast.setScouted(true);
        etat.getMapMonde().addCaseMap(1,0,caseEast);
        move.nextNode();
        assertEquals(Direction.WEST,NodeMove.getDirection());
        
        NodeMove.setDirection(Direction.EAST);
        Case caseSouth = new Case();
        caseSouth.setScouted(true);
        etat.getMapMonde().addCaseMap(0,-1,caseSouth);
        move.nextNode();
        assertEquals(Direction.WEST,NodeMove.getDirection());
        
        NodeMove.setDirection(Direction.EAST);
        caseSouth = new OutOfMapCase();
        etat.getMapMonde().addCaseMap(0,-1,caseSouth);
        move.nextNode();
        assertEquals(Direction.WEST,NodeMove.getDirection());
        
    }

}
