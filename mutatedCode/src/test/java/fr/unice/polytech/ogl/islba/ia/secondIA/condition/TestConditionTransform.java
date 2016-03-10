package fr.unice.polytech.ogl.islba.ia.secondIA.condition;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import fr.unice.polytech.ogl.islba.ia.secondIA.node.NodeTransform;
import fr.unice.polytech.ogl.islba.model.Case;
import fr.unice.polytech.ogl.islba.model.Equipe;
import fr.unice.polytech.ogl.islba.model.EtatDeJeu;

public class TestConditionTransform {

    @Test
    public void test() {
        Map<String,Integer> objective = new HashMap<String, Integer>();
        objective.put("PLANK", 200);
        EtatDeJeu etat = new EtatDeJeu(100,"creek",100,objective);
        
        //we land
        Case caseLanded = new Case();
        caseLanded.setMoveOn(true);
        etat.getMapMonde().addCaseMap(0,0,caseLanded);
        etat.getMapMonde().setCurrentCoo(0, 0);
        
        Condition transform = new ConditionTransform(etat);
        assertFalse(transform.isOK());
        assertNull(transform.nextNode());
        
        Equipe team = etat.getTeam();
        team.exploitResource("WOOD", 100);
        
        assertTrue(transform.isOK());
        assertTrue(transform.nextNode() instanceof NodeTransform);
        
        team.exploitResource("PLANK", 200);
        assertFalse(transform.isOK());
        assertNull(transform.nextNode());
    }
}
