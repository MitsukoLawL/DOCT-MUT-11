package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeStop;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestConditionStop {
    
    @Test
    public void testConditionStopWithNoObjective() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        Condition stop = new ConditionStop(etat);
        assertTrue(stop.isOK());
    }

    @Test
    public void testConditionStopWithObjective(){
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        Condition stop = new ConditionStop(etat);
        
        assertFalse(stop.isOK());
        
        etat.getTeam().exploitResource("Resource", 200);
        assertTrue(stop.isOK());
    }
    
    @Test
    public void testConditionStopWithPA(){
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("Resource", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);

        //we land
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(10,0);
        
        Condition stop = new ConditionStop(etat);
        
        assertFalse(stop.isOK());
        
        etat.reducePA(90);
        
        assertTrue(stop.isOK());
    }
    
    @Test
    public void testNextNode(){
        Map<String,Integer> objective = new HashMap<String, Integer>();
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        Condition stop = new ConditionStop(etat);
        
        assertTrue(stop.nextNode() instanceof NodeStop);
    }
}
