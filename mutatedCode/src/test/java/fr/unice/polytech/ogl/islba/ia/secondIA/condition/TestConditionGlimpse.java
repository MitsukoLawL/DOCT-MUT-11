package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeGlimpse;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Direction;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestConditionGlimpse {

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
        
        Condition conditionGlimpse = new ConditionGlimpse(etat, Direction.NORTH);
        assertTrue(conditionGlimpse.isOK());
        
        Case caseNorth = new Case();
        etat.getMapMonde().addCaseMap(0, 1, caseNorth);
        assertTrue(conditionGlimpse.isOK());
        
        caseNorth.setNumberCaseGlimpse(2);
        assertFalse(conditionGlimpse.isOK());
        
        assertTrue(conditionGlimpse.nextNode() instanceof NodeGlimpse);
    }

}
